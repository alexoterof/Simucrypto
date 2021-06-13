import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { BuyOrder } from 'src/app/model/buy-order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private urlEndPoint: string = environment.apiHost + 'order';
  constructor(private http: HttpClient) { }

  refill(ammount: number){
    return this.http.post(`${this.urlEndPoint}/refill/${ammount}`, null);
  }

  buy(order: BuyOrder){
    console.log("order");
    console.log(order);
    return this.http.post(`${this.urlEndPoint}/place`, order);
  }
}
