import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { WalletMin } from 'src/app/model/wallet-min';

@Injectable({
  providedIn: 'root'
})
export class WalletService {
  private urlEndPoint: string = environment.apiHost + 'wallet';

  constructor(private http: HttpClient) { }

  findWallets() {
    return this.http.get<WalletMin[]>(`${this.urlEndPoint}/mywallets`)  
  }

  delete(id: number){
    return this.http.get(`${this.urlEndPoint}/delete/${id}`)
  }
}
