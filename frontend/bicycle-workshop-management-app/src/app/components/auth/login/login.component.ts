import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../service/authentication.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { UserRole } from '../../../model/roles.enum';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private toasterService: ToastrService,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) { }

  ngOnInit(): void {
    this.createLoginForm();
  }
  createLoginForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', [Validators.required]]
    })
  }
  login() {
    if (this.loginForm.valid) {
      localStorage.clear();
      const loginModel = Object.assign({}, this.loginForm.value);
      this.authenticationService.login(loginModel).subscribe(
        (response) => {
          let token = response.token;
          localStorage.setItem('token', token)
          this.toasterService.success(response.message, 'Login successful');
          let decodedToken = this.jwtHelper.decodeToken(token);
          this.redirectBasedOnRole(decodedToken.roles);
        },
        (responseError) => {
          this.toasterService.error('Login not successful, please try again')
        }
      );
    } else {
      this.toasterService.error('Login data is not correct');
    }
  }

  private redirectBasedOnRole(userRoles: string[]): void {
    if (userRoles.includes(UserRole.MECHANIC)) {
      this.router.navigate(['orders']);
    } else if (userRoles.includes(UserRole.OWNER)) {
      this.router.navigate(['workshops/assigned']);
    } else if (userRoles.includes(UserRole.MECHANIC_CANDIDATE)) {
      this.router.navigate(['mechanic-candidate-dashboard']);
    } else if (userRoles.includes(UserRole.OWNER_CANDIDATE)) {
      this.router.navigate(['owner-candidate-dashboard']);
    } else {
      this.router.navigate(['/home']);
    }
  }
}
