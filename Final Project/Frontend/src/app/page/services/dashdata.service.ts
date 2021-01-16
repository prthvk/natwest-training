import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DashdataService {

  constructor(private httpClient: HttpClient) { }

  getPosts() {
    return this.httpClient.get("https://developers.zomato.com/api/v2.1/search?entity_id=4&entity_type=city&sort=rating&order=desc",{
      headers: new HttpHeaders().set('user-key', "ae6967526646bd6de0576e8425a91293")});
  }
}
