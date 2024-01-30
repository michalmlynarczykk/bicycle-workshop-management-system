import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private authenticationBaseUrl = `${environment.apiBaseUrl}/authentication/external/v1/auth`;

  constructor(private http: HttpClient) { }

  public register(registerRequest: any): Observable<any> {
    console.log(registerRequest)
    return this.http.post<any>(`${this.authenticationBaseUrl}/register`, registerRequest);
  }

  public login(loginRequest: any): Observable<any> {
    return this.http.post<any>(`${this.authenticationBaseUrl}/login`, loginRequest);
  }
}
