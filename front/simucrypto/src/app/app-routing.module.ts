import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './pages/landing/landing.component';
import { CoinDetailsComponent } from './pages/coin-details/coin-details.component';
import { LoginPageComponent } from './pages/login/login-page/login-page.component';
import { RegisterPageComponent } from './pages/login/register-page/register-page.component';
import { BuyCoinComponent } from './pages/buy-coin/buy-coin/buy-coin.component';
import { WalletComponent } from './pages/wallet/wallet/wallet.component';


const routes: Routes = [
  { path: '', redirectTo: '/landing', pathMatch: 'full' },
  { path: 'landing', component: LandingComponent },
  { path: 'details/:id', component: CoinDetailsComponent },
  { path: 'login', component: LoginPageComponent},
  { path: 'register', component: RegisterPageComponent},
  { path: 'buy', component: BuyCoinComponent},
  { path: 'wallet', component: WalletComponent }
];

@NgModule({
  declarations: [],
  exports: [RouterModule],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
  ]
})
export class AppRoutingModule { }
