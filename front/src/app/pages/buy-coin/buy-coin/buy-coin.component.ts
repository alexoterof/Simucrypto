import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BuyOrder } from 'src/app/model/buy-order';
import { CoinDetails } from 'src/app/model/coin/coin-detail';
import { WalletMin } from 'src/app/model/wallet-min';
import { OrderService } from 'src/app/services/order/order.service';
import { WalletService } from 'src/app/services/wallet/wallet.service';

@Component({
  selector: 'app-buy-coin',
  templateUrl: './buy-coin.component.html',
  styleUrls: ['./buy-coin.component.scss']
})
export class BuyCoinComponent implements OnInit {
  buyOrder: BuyOrder;
  coin: CoinDetails;
  totalPrice: number;
  paymentMethods: WalletMin[];
  paymentMethod: WalletMin;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private walletService: WalletService,
              private orderService: OrderService) { }

  ngOnInit(): void {
    this.totalPrice = 0;
    this.buyOrder = new BuyOrder();
    this.coin = new CoinDetails();
    this.route.queryParams.subscribe(params => { 
      this.coin = JSON.parse(params['data']); 
      this.buyOrder.coinname = this.coin.id;
    });
    this.walletService.findWallets().subscribe(
      (response) => {
        this.paymentMethods = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  updateTotal(){
    this.totalPrice = <number><any>(this.coin.current_price * this.buyOrder.ammount).toFixed(2);
  }

  buy(){
    this.buyOrder.paymentMethod = this.paymentMethod.coinname;
    this.orderService.buy(this.buyOrder).subscribe(
      (response) => {
        this.router.navigate(['wallet']);
      }, 
      (error) => {
        console.log(error);
      }
    );
  }
}
