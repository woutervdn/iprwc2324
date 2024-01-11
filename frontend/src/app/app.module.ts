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
    CartComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        RouterOutlet,
        FormsModule,
        RouterLinkActive,
    ],
  providers: [provideRouter(routes)],
  bootstrap: [AppComponent]
})
export class AppModule { }