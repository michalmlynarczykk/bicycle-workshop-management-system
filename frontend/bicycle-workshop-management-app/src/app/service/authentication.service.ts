import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserDetails } from '../model/user-details';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private authenticationBaseUrl = `${environment.apiBaseUrl}/authentication/external/v1`;

  constructor(
    private http: HttpClient,
    private router: Router,
    private jwtHelper: JwtHelperService) {

  }

  public register(registerRequest: any): Observable<any> {
    return this.http.post<any>(`${this.authenticationBaseUrl}/auth/register`, registerRequest);
  }

  public login(loginRequest: any): Observable<any> {
    return this.http.post<any>(`${this.authenticationBaseUrl}/auth/login`, loginRequest);
  }

  public getUserDetails(userId: string): Observable<UserDetails> {
    return this.http.get<any>(`${this.authenticationBaseUrl}/users/${userId}`);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  isAuthenticated() {
    if (localStorage.getItem("token")) {
      return true;
    }
    else {
      return false
    }
  }

  roles(): string[] {
    if (this.isAuthenticated()) {
      return this.getDecodedToken().roles;
    }
    return []
  }

  getDecodedToken() {
    const token = localStorage.getItem('token');
    return token ? this.jwtHelper.decodeToken(token) : null;
  }

}