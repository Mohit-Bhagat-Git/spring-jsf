import { BrowserModule } from '@angular/platform-browser'; // browser compatible
import { NgModule } from '@angular/core'; // container of all other modules
import {FormsModule} from '@angular/forms'; // for two way data binding, with this we can use ngModel.
import {HttpModule} from '@angular/http'; // to work with http requests


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProdListViewComponent } from './prod-list-view/prod-list-view.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';

import {DataService} from './services/data.service';
import {ProductService } from './product/product.service';


import { AppRoutingModule } from './/app-routing.module';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    ProdListViewComponent,
    ProductComponent,
    CartComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [DataService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
