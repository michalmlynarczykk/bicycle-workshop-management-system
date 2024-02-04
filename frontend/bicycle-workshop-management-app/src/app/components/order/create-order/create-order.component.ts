import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ExtendedOrder, Part, Service } from '../../../model/order';
import { OrderService } from '../../../service/order.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-details',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  orderForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private orderService: OrderService,
    private toasterService: ToastrService,
    private router: Router) { }

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm(): void {
    this.orderForm = this.fb.group({
      client: this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]]
      }),
      bike: this.fb.group({
        brand: ['', Validators.required],
        model: ['', Validators.required],
        color: ['', Validators.required],
        frameNumber: ['', Validators.required],
        productionYear: [0, Validators.required],
        description: ['', Validators.required]
      }),
      services: this.fb.array([
        this.createServiceFormGroup()
      ]),
      parts: this.fb.array([
        this.createPartFormGroup()
      ])
    });
  }

  private createServiceFormGroup(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, Validators.required],
      quantity: [0, Validators.required]
    });
  }

  private createPartFormGroup(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, Validators.required],
      quantity: [0, Validators.required]
    });
  }

  addService(): void {
    const servicesArray = this.orderForm.get('services') as FormArray;
    servicesArray.push(this.createServiceFormGroup());
  }

  addPart(): void {
    const partsArray = this.orderForm.get('parts') as FormArray;
    partsArray.push(this.createPartFormGroup());
  }

  get clientControls(): { [key: string]: AbstractControl } {
    return (this.orderForm.get('client') as FormGroup).controls;
  }

  get bikeControls(): { [key: string]: AbstractControl } {
    return (this.orderForm.get('bike') as FormGroup).controls;
  }

  get servicesArray(): AbstractControl[] {
    return (this.orderForm.get('services') as FormArray).controls;
  }

  get partsArray(): AbstractControl[] {
    return (this.orderForm.get('parts') as FormArray).controls;
  }

  onSubmit(): void {
    if (this.orderForm.valid) {
      const formData = this.orderForm.value;
      this.orderService.createOrder(formData).subscribe(
        (response) => {
          this.toasterService.success(response.message, 'Order created successfuly');
          this.router.navigate(['orders']);
        },
        (responseError) => {
          if (responseError.status === 400) {
            this.toasterService.error('Form data is not correct');
          } else {
            this.toasterService.error('An unexpected error occurred.', 'Order creation not successful');
          }
        }
      );
    } else {
      this.toasterService.error('Order data is not correct');
    }
  }
}
