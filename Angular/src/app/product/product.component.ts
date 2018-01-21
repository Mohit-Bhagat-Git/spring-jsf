import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { ProductService } from './product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  
  productsV: Product[];
  imageLocation = '../assets/Images';

  constructor(private prodService: ProductService) { }

  ngOnInit() {
    this.getProductFromService();
  }

  getProductFromService(){
    this.prodService.getProducts().subscribe(resProd=>this.productsV=resProd);
  }
  
}
