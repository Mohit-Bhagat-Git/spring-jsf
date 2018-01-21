import { DataService } from '../services/data.service';
import {Injectable} from '@angular/core';
import {Product} from './product';

@Injectable()
export class ProductService {

  
  constructor(private dataService: DataService) {}

  getProducts() {

    return this.dataService.getProducts();
}

}
