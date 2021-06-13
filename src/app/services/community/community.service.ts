import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Community } from 'src/app/model/community';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {
  private urlEndPoint: string = environment.apiHost + 'community';
  constructor(private http: HttpClient) { }

  findAll(): Observable<Community[]>{
    return this.http.get<Community[]>(`${this.urlEndPoint}/all`);
  }
  
  findMine(): Observable<Community[]>{
    return this.http.get<Community[]>(`${this.urlEndPoint}/mine`);
  }

  create(dto: any){
    return this.http.post<any>(`${this.urlEndPoint}/create`, dto);  
  }

  join(id: number){
    return this.http.get(`${this.urlEndPoint}/join/${id}`)
  }
}
