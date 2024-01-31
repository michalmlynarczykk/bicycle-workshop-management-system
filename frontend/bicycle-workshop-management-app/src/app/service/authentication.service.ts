import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from '../../environments/environment';
import { LocalStorageService } from './local-storage.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private authenticationBaseUrl = `${environment.apiBaseUrl}/authentication/external/v1/auth`;
  private authenticationChangedSource = new Subject<boolean>()
  authenticationChanged = this.authenticationChangedSource.asObservable();

  constructor(
    private http: HttpClient,
    private router: Router,
    private jwtHelper: JwtHelperService) {

  }

  public register(registerRequest: any): Observable<any> {
    console.log(registerRequest)
    return this.http.post<any>(`${this.authenticationBaseUrl}/register`, registerRequest);
  }

  public login(loginRequest: any): Observable<any> {
    this.notifyAuthenticationChange(true);
    return this.http.post<any>(`${this.authenticationBaseUrl}/login`, loginRequest);
  }

  logout() {
    localStorage.clear();
    this.notifyAuthenticationChange(false);
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

  private notifyAuthenticationChange(isLoggedIn: boolean): void {
    this.authenticationChangedSource.next(isLoggedIn);
  }
}