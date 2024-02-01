import { Component } from '@angular/core';

import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { WorkshopService } from '../../service/workshop.service';
import { AuthenticationService } from '../../service/authentication.service';

@Component({
  selector: 'app-owner-candidate-dashboard',
  templateUrl: './owner-candidate-dashboard.component.html',
  styleUrl: './owner-candidate-dashboard.component.css'
})
export class OwnerCandidateDashboardComponent {
  workshopForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private workshopService: WorkshopService,
    private toasterService: ToastrService,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.createWorkshopForm();
  }
  createWorkshopForm() {
    this.workshopForm = this.formBuilder.group({
      name: ['', Validators.required],
      street: [''],
      buildingNumber: ['', Validators.required],
      apartmentNumber: [''],
      city: ['', [Validators.required]],
      zipCode: ['', [Validators.required]]
    })
  }

  submit() {
    if (this.workshopForm.valid) {
      const workshopModel = Object.assign({}, this.workshopForm.value);
      this.workshopService.createWorkshop(workshopModel).subscribe(
        (response) => {
          this.toasterService.success(response.message, 'Workshop creation successful - you will be redirected to login page, please login again and start using workshop management system');
          this.authenticationService.logout();
        },
        (responseError) => {
          if (responseError.status === 409) {
            this.toasterService.error('Workshop already exists');
          } else {
            this.toasterService.error('An unexpected error occurred.', 'Workshop creation not successful');
          }
        }
      );
    } else {
      this.toasterService.error('Workshop data is not correct');
    }
  }

}
