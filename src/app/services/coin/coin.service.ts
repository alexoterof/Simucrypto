import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { CoinMin } from 'src/app/model/coin/coin-min';
import { CoinDetails } from 'src/app/model/coin/coin-detail';
import { HistoricData } from 'src/app/model/historic-data';

@Injectable({
  providedIn: 'root'
})
export class CoinService {
  private urlEndPoint: string = environment.apiHost + 'coin';
  constructor(private http: HttpClient) { }

  findAllPrices() {
    return this.http.get<CoinMin[]>(`${this.urlEndPoint}/all`)  
  }

  findDetail(coinname: string) {
    return this.http.get<CoinDetails>(`${this.urlEndPoint}/detail/${coinname}`)  
  }

  findHistoric(coinname: string){
    return this.http.get<HistoricData>(`${this.urlEndPoint}/historic/${coinname}`)
  }
}
