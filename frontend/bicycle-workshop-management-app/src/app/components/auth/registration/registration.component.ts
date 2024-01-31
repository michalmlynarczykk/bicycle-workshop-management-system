import { Component } from '@angular/core';
import { AuthenticationService } from '../../../service/authentication.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  registerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService,
    private toasterService: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.createRegistrationForm();
  }
  createRegistrationForm() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', [Validators.required]],
      workshopPosition: ['', [Validators.required]]
    })
  }
  register() {
    if (this.registerForm.valid) {
      const registerModel = Object.assign({}, this.registerForm.value);
      this.authenticationService.register(registerModel).subscribe(
        (response) => {
          this.toasterService.success(response.message, 'Registration successful');
          this.router.navigate(['/login']);
        },
        (responseError) => {
          if (responseError.status === 409) {
            this.toasterService.error('Email is already taken', 'Registration not successful');
          } else {
            this.toasterService.error('An unexpected error occurred.', 'Registration not successful');
          }
        }
      );
    } else {
      this.toasterService.error('Registration data is not correct');
    }
  }
}
