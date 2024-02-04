import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ExtendedOrder } from '../../../model/order';


@Component({
  selector: 'app-user-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {
  extendedOrder: ExtendedOrder;

  constructor(
    private dialogRef: MatDialogRef<OrderDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any) { 
    }

  ngOnInit(): void {
    this.extendedOrder = this.data;
  }
}
