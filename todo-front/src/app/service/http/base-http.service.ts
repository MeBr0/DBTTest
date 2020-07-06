import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BaseHttpService {

  constructor(protected http: HttpClient) { }

  get(url: string, body: any): Promise<any> {
    return this.http.get(url).toPromise().then(res => res);
  }

  post(url: string, body: any): Promise<any> {
    return this.http.post(url, body).toPromise().then(res => res);
  }

  patch(url: string, body: any): Promise<any> {
    return this.http.patch(url, body).toPromise().then(res => res);
  }

  del(url: string, body: any): Promise<any> {
    return this.http.delete(url, body).toPromise().then(res => res);
  }
}
