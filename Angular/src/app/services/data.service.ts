import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class DataService {

  constructor(public http: Http) {
    console.log('data service connected');
  }

  getPosts() {
    return this.http.get('https://jsonplaceholder.typicode.com/users').map(res => res.json());
  }
  
  getProducts(){
    return this.http.get('http://192.168.1.6:1112/product/readAll').map(res => res.json());
  }

}
