import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private router: Router){
    }
    
intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let jwt: string = localStorage.getItem('jwt');
    if (!jwt && req.url != environment.apiHost+"user/login")
        this.router.navigate([environment.loginPage]);
    if(jwt == null)
        return next.handle(req);
    const expiringEpoch = (JSON.parse(atob(jwt.split('.')[1]))).exp;
    const currentEpoch = (new Date).getTime();
    if(currentEpoch < expiringEpoch){
        localStorage.removeItem('jwt');
        this.router.navigate([environment.loginPage]);    
    }
    
    return next.handle(req.clone({
                    setHeaders: {
                    'Authorization': `Bearer ${jwt}`
                    }
                })
    );
  }
}