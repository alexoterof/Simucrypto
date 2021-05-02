import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LandingComponent } from './pages/landing/landing.component';
import { CoinDetailsComponent } from './pages/coin-details/coin-details.component';

import { ListboxModule } from 'primeng/listbox';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';
import { LoginPageComponent } from './pages/login/login-page/login-page.component';
import { RegisterPageComponent } from './pages/login/register-page/register-page.component';
import { AuthInterceptor } from './services/auth/auth-interceptor';
import { ToastModule } from 'primeng/toast';
import { BuyCoinComponent } from './pages/buy-coin/buy-coin/buy-coin.component';
import { WalletComponent } from './pages/wallet/wallet/wallet.component';
import { TableModule } from 'primeng/table';


@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    ListboxModule,
    FormsModule,
    HttpClientModule,
    ButtonModule,
    ToastModule,
    BrowserAnimationsModule,
    TableModule,
  ],
  declarations: [
    AppComponent,
    LandingComponent,
    CoinDetailsComponent,
    LoginPageComponent,
    RegisterPageComponent,
    BuyCoinComponent,
    WalletComponent,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, 
      useClass: AuthInterceptor, 
      multi: true }
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
