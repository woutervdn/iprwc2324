import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';
import { NavComponent } from './nav/nav.component';
import {HttpClientModule} from "@angular/common/http";
import {provideRouter, RouterLinkActive, RouterOutlet, Routes} from "@angular/router";
import { MainComponent } from './main/main.component';
import { AdminComponent } from './admin/admin.component';
import {FormsModule} from "@angular/forms";
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import {MatIconModule} from "@angular/material/icon";

const routes: Routes = [
  {path: '', component: MainComponent},
  {path: 'admin', component: AdminComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    NavComponent,
    MainComponent,
    AdminComponent,
    CartComponent,
    OrderComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterOutlet,
    FormsModule,
    RouterLinkActive,
    MatIconModule,
  ],
  providers: [provideRouter(routes)],
  bootstrap: [AppComponent]
})
export class AppModule { }
