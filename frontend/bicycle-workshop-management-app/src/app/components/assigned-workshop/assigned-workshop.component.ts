import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { WorkshopService } from '../../service/workshop.service';
import { Workshop } from '../../model/workshop';

@Component({
  selector: 'app-assigned-workshop',
  templateUrl: './assigned-workshop.component.html',
  styleUrl: './assigned-workshop.component.css'
})
export class AssignedWorkshopComponent {
  assignedWorkshop: Workshop;

  constructor(
    private workshopService: WorkshopService,
    private toasterService: ToastrService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAssignedWorkshop();
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
