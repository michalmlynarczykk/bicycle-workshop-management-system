import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private authenticationBaseUrl = `${environment.apiBaseUrl}/authentication/external/v1/auth`;

  constructor(
    private http: HttpClient,
    private router: Router,
    private jwtHelper: JwtHelperService) {

  }

  public register(registerRequest: any): Observable<any> {
    return this.http.post<any>(`${this.authenticationBaseUrl}/register`, registerRequest);
  }

  public login(loginRequest: any): Observable<any> {
    return this.http.post<any>(`${this.authenticationBaseUrl}/login`, loginRequest);
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