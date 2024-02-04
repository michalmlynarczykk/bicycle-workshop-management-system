import { Component, Inject, Input, OnInit } from '@angular/core';
import { UserDetails } from '../../model/user-details';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  userDetails: UserDetails;

  constructor(
    private dialogRef: MatDialogRef<UserDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any) { 
    }

  ngOnInit(): void {
    this.userDetails = this.data;
  }
}
