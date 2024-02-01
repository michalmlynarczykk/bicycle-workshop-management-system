import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { WorkshopService } from '../../service/workshop.service';

@Component({
  selector: 'app-mechanic-candidate-dashboard',
  templateUrl: './mechanic-candidate-dashboard.component.html',
  styleUrl: './mechanic-candidate-dashboard.component.css'
})
export class MechanicCandidateDashboardComponent {
  joinWorkshopForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private workshopService: WorkshopService,
    private toasterService: ToastrService
  ) { }

  ngOnInit(): void {
    this.createJoinWorkshopForm();
  }
  createJoinWorkshopForm() {
    this.joinWorkshopForm = this.formBuilder.group({
      workshopId: ['', Validators.required]
    })
  }

  submit() {
    if (this.joinWorkshopForm.valid) {
      const joinWorkshopModel = Object.assign({}, this.joinWorkshopForm.value);
      this.workshopService.joinWorkshop(joinWorkshopModel).subscribe(
        (response) => {
          this.toasterService.success(response.message, 'Your join request was successfully saved. Wait for workshop owner approval.');
        },
        (responseError) => {
          if (responseError.status === 404) {
            this.toasterService.error('Ups... workshop not found');
          } else if (responseError.status === 409) {
            this.toasterService.error('Workshop join requst already exists. Wait for owner approval');
          } else {
            this.toasterService.error('An unexpected error occurred.', 'Workshop join request not successful');
          }
        }
      );
    } else {
      this.toasterService.error('Workshop join request data is not correct');
    }
  }
}
