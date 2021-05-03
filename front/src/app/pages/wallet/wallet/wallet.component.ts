import { Component, OnInit } from '@angular/core';
import { WalletMin } from 'src/app/model/wallet-min';
import { OrderService } from 'src/app/services/order/order.service';
import { WalletService } from 'src/app/services/wallet/wallet.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.scss']
})
export class WalletComponent implements OnInit {
  wallets: WalletMin[];

  constructor(private walletService: WalletService,
              private orderService: OrderService) { }

  ngOnInit(): void {
    this.fetchWallets();
  }

  fetchWallets(){
    this.walletService.findWallets().subscribe(
      (response) => {
        this.wallets = response;
        console.log(this.wallets);
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
