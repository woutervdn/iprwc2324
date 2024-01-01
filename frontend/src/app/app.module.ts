import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';
import { NavComponent } from './nav/nav.component';
import {HttpClientModule} from "@angular/common/http";
import {provideRouter, RouterOutlet, Routes} from "@angular/router";
import { MainComponent } from './main/main.component';
import { AdminComponent } from './admin/admin.component';
import {FormsModule} from "@angular/forms";

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
    AdminComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterOutlet,
    FormsModule,
  ],
  providers: [provideRouter(routes)],
  bootstrap: [AppComponent]
})
export class AppModule { }
