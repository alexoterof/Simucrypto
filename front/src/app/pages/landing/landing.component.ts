import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CoinDetails } from 'src/app/model/coin-detail';
import { CoinMin } from 'src/app/model/coin-min';
import { CoinService } from 'src/app/services/coin/coin.service';

@Component({
  selector: 'landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {
  coins: CoinMin[];
  selectedCoin: CoinMin;

  constructor(private cryptoService: CoinService,
              private router: Router) { }

  ngOnInit(): void {
    this.cryptoService.findAllPrices().subscribe(
      (response) => {
        this.coins = response;
      },
      (error) => {

      }
    )
  }

  coinClicked(){
    this.router.navigate([`/details/${this.selectedCoin.name}`])
  }

}
