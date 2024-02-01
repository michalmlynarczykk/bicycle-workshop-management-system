import { Component, OnInit } from '@angular/core';
import { WorkshopService } from '../../service/workshop.service';
import { WorkshopJoinRequest } from '../../model/workshop-join-request';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { AuthenticationService } from '../../service/authentication.service';

@Component({
  selector: 'app-owner-dashboard',
  templateUrl: './owner-dashboard.component.html',
  styleUrls: ['./owner-dashboard.component.css']
})
export class OwnerDashboardComponent implements OnInit {
  workshopJoinRequests: WorkshopJoinRequest[] = [];
  displayedColumns: string[] = ['id', 'createdAt', 'decidedAt', 'workshopId', 'userId', 'status'];
  constructor(
    private workshopService: WorkshopService,
    private toasterService: ToastrService,
    private dialog: MatDialog,
    private authenticationService: AuthenticationService ) {}

  ngOnInit(): void {
    this.getWorkshopJoinRequests();
  }

  getWorkshopJoinRequests() {
    this.workshopService.getWorkshopJoinRequests().subscribe(
      (response) => {
        this.workshopJoinRequests = response.workshopJoinRequests;
      },
      (error) => {
        this.toasterService.error('Error fetching workshop join requests');
      }
    );
  }

  viewUserDetails(userId: string): void {
    this.authenticationService.getUserDetails(userId).subscribe(
      (response) => {
        const dialogRef = this.dialog.open(UserDetailsComponent, {
          width: '500px',
          data: response
        });
      },
      (error) => {
        this.toasterService.error('Error fetching user details');
      }
    )
  }
}
