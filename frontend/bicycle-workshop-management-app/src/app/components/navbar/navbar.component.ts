import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../service/authentication.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  userRoles: string[] = [];

  constructor(private authService: AuthenticationService) {}

  ngOnInit() {
    this.userRoles = this.authService.roles();
  }

  logout() {
    this.authService.logout();
  }
}