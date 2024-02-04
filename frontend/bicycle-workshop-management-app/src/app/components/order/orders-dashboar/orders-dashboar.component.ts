import { Component } from '@angular/core';
import { OrderService } from '../../../service/order.service';
import { Order } from '../../../model/order';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { OrderDetailsComponent } from '../order-details/order-details.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-orders-dashboar',
  templateUrl: './orders-dashboar.component.html',
  styleUrl: './orders-dashboar.component.css'
})
export class OrdersDashboarComponent {

  orders: Order[];
  displayedColumns: string[] = ['id', 'createdAt', 'collectedAt', 'status', 'client', 'bike', 'actions'];

  constructor(
    private orderService: OrderService,
    private toasterService: ToastrService,
    private router: Router,
    private dialog: MatDialog
  ){}

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders() {
    this.orderService.getOrders().subscribe(
      (response) => {
        this.orders = response.orders;
      },
      (error) => {
        this.toasterService.error('Error fetching orders');
      }
    );
  }

  viewOrderDetails(orderId: string): void {
    this.orderService.getOrderDetails(orderId).subscribe(
      (response) => {
        const dialogRef = this.dialog.open(OrderDetailsComponent, {
          width: '700px',
          data: response
        });
      },
      (error) => {
        this.toasterService.error('Error fetching user details');
      }
    )
    
  }

  createNewOrder(): void {
    this.router.navigate(['orders/create']);
  }
}
