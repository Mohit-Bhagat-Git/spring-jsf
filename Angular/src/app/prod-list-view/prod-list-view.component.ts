import { Component, OnInit } from '@angular/core';
import {DataService} from '../services/data.service';

@Component({
  selector: 'app-prod-list-view',
  templateUrl: './prod-list-view.component.html',
  styleUrls: ['./prod-list-view.component.css']
})
export class ProdListViewComponent implements OnInit {

  addToCart: String = 'Add to Cart';

  constructor(private dataService: DataService) {
  console.log('calling data service');
   }

  ngOnInit() {
    this.dataService.getPosts().subscribe((posts) => {
      console.log(posts);
    });
  }

  addItem() {
    console.log('clicked');
    this.addToCart = 'Added To Cart';
  }

}
