import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthserviceService } from './authservice.service';

@Injectable({
  providedIn: 'root'
})

export class HeaderservService {

  constructor(private httpClient: HttpClient, private authserv: AuthserviceService) { }

  getDebitLimit() {
    return this.httpClient.get("", {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.authserv.getToken}`)
    });
  }

  //need more info on the api format to complete the put request
  setDebitLimit(limit:string) {
    return this.httpClient.put("", {
      params: new HttpParams().set("dailyLimit", limit)
    });
  }
  
 //need more info on the api format to complete the put request
  setBlockedStatus(status) {
    return this.httpClient.put("", {
      params: new HttpParams().set("isActivated", status)
    });
  }

}
