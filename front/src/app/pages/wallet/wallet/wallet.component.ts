import { Component, OnInit } from '@angular/core';
import { WalletMin } from 'src/app/model/wallet-min';
import { CoinService } from 'src/app/services/coin/coin.service';
import { OrderService } from 'src/app/services/order/order.service';
import { WalletService } from 'src/app/services/wallet/wallet.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.scss']
})
export class WalletComponent implements OnInit {
  wallets: WalletMin[];
  totalAmmount: number;
  constructor(private walletService: WalletService,
              private orderService: OrderService,
              private cryptoService: CoinService,) {
                
    this.totalAmmount = 0;
  }

  ngOnInit(): void {
    this.fetchWallets();
  }

  fetchWallets(){
    this.walletService.findWallets().subscribe(
      (wallets) => {
        this.wallets = wallets;
        this.cryptoService.findAllPrices().subscribe(
          (coinsPrices) => {
            this.totalAmmount = 0;
            this.wallets.forEach(wallet => {
              if(wallet.coinname == "eur"){
                this.totalAmmount += wallet.cash;
              }
              coinsPrices.forEach(coinPrice => {
                if(wallet.coinname == coinPrice.name){
                  this.totalAmmount += coinPrice.price * wallet.cash;
                }
              })
            })
            this.totalAmmount = <number><any>this.totalAmmount.toFixed(2);
          }, 
          (error) => {
            console.log(error);
          }
        )
      },
      (error) => {
        console.log(error);
      }
    )
  }

  refill(){
    this.orderService.refill(1000).subscribe(
      (response) => {
        this.fetchWallets();
      },
      (error) => {
        console.log(error);
      }
    )
  }

}
