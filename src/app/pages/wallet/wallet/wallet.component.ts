import { Component, OnInit } from '@angular/core';
import { BuyOrder } from 'src/app/model/buy-order';
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

  sell(sellingwallet: WalletMin){
    this.cryptoService.findAllPrices().subscribe(
      (coinsPrices) => {
        console.log(coinsPrices);
        console.log(sellingwallet);
        coinsPrices.forEach(coin => {
          if(coin.name != sellingwallet.coinname)
            return;
          let order = new BuyOrder();
          order.ammount = <number><any> (coin.price * sellingwallet.cash).toFixed(5);
          order.coinname = "eur";
          order.paymentMethod = sellingwallet.coinname;
          ;
          this.orderService.buy(order).subscribe(() => this.walletService.delete(sellingwallet.id).subscribe(() => this.fetchWallets()));
          
        })
        
      }, 
      (error) => {
        console.log(error);
      }
    )

  }

}
