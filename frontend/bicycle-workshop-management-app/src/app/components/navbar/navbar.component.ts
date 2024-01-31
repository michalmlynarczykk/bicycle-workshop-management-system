import { Component } from '@angular/core';
import { AuthenticationService } from '../../service/authentication.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(
    private authService: AuthenticationService
  ) { }

  logout() {
    this.authService.logout();
  }
}