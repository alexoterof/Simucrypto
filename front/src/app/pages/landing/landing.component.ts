import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { CoinDetails } from 'src/app/model/coin/coin-detail';
import { CoinMin } from 'src/app/model/coin/coin-min';
import { CoinService } from 'src/app/services/coin/coin.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
  providers: [MessageService]
})
export class LandingComponent implements OnInit {
  coins: CoinMin[];

  constructor(private cryptoService: CoinService,
              private userService: UserService,
              private router: Router,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.cryptoService.findAllPrices().subscribe(
      (response) => {
        this.coins = response;
      },
      (error) => {

      }
    )
  }

  coinClicked(coin: CoinMin){
    this.router.navigate([`/details/${coin.name}`])
  }

  logout(){
    localStorage.clear();
    window.location.reload();
  }

  delete(){
    this.userService.delete().subscribe(
      (response) => {
        this.messageService.add({severity: "success", summary: 'Deleted', detail: 'Your account was succesfully deleted.'})
        localStorage.clear();
        window.location.reload();
      },
      (error) => {
        console.log(error);
        this.messageService.add({severity: "error", summary: 'Invalid credentials', detail: 'User name and password do not match '})
      }
    )
  }
}
