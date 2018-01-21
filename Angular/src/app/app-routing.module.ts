import { RouterModule, PreloadAllModules } from '@angular/router';
import { ProductComponent } from './product/product.component';
import { NgModule } from '@angular/core';
@NgModule({
  imports: [
    RouterModule.forRoot([
      {path: 'products', component: ProductComponent}
    ])
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule { }
