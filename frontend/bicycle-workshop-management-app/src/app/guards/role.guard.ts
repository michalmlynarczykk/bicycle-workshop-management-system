import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AuthenticationService } from '../service/authentication.service';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(
    private authService: AuthenticationService,
    private authGuard: AuthGuard,
    private router: Router,
    private toastr: ToastrService
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authGuard.canActivate(route, state)) {
      const expectedRoles = route.data['expectedRole'];

      if (!Array.isArray(expectedRoles)) {
        console.error('Expected roles should be an array');
        return false;
      }

      const hasRequiredRole = expectedRoles.some(role => this.authService.roles().includes(role));

      if (hasRequiredRole) {
        return true;
      } else {
        this.toastr.error("You don't have the required role");
        this.router.navigate(['/login']);
        return false;
      }
    } else {
      this.toastr.error('Please log in');
      this.router.navigate(['/login']);
      return false;
    }
  }
}