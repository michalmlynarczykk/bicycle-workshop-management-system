import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { WorkshopService } from '../../service/workshop.service';
import { Workshop } from '../../model/workshop';
import { AuthenticationService } from '../../service/authentication.service';
import { UserRole } from '../../model/roles.enum';

@Component({
  selector: 'app-assigned-workshop',
  templateUrl: './assigned-workshop.component.html',
  styleUrl: './assigned-workshop.component.css'
})
export class AssignedWorkshopComponent {
  assignedWorkshop: Workshop;
  isWorkshopOwner: boolean;

  constructor(
    private workshopService: WorkshopService,
    private toasterService: ToastrService,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.getAssignedWorkshop();
    this.isWorkshopOwner = this.authenticationService.roles().includes(UserRole.OWNER);
  }

  getAssignedWorkshop() {
    this.workshopService.getAssignedWorkshop().subscribe(
      (response) => {
        this.assignedWorkshop = response;
      },
      (responseError) => {
        this.toasterService.error('An unexpected error occurred.');
      }
    )
  }
}
