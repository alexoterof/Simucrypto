import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDetailDto } from 'src/app/model/user/userDetailDto';
import { UserMinDto } from 'src/app/model/user/userRegisterDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private urlEndPoint: string = environment.apiHost + 'user';
  constructor(private http: HttpClient) { }
  
  register(dto: UserMinDto){
    return this.http.post<UserMinDto>(`${this.urlEndPoint}/register`, dto);
  }

  login(dto: UserMinDto){
    return this.http.post<any>(`${this.urlEndPoint}/login`, dto);
  }

  detail(username: string){
    return this.http.get<UserDetailDto>(`${this.urlEndPoint}/detail/${username}`);
  }
  
  delete(){
    return this.http.get<any>(`${this.urlEndPoint}/delete`);
  }
}
