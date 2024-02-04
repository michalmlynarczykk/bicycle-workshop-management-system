import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    private orderBaseUrl = `${environment.apiBaseUrl}/order/external/v1/orders`;

    constructor(
        private http: HttpClient
    ) { }

    public createOrder(createOrderRequest: any): Observable<any> {
        return this.http.post<any>(`${this.orderBaseUrl}`, createOrderRequest);
    }

    public upadteOrder(updateOrderRequest: any, orderId: string): Observable<any> {
        return this.http.put<any>(`${this.orderBaseUrl}/${orderId}`, updateOrderRequest);
    }

    public getOrders(): Observable<any> {
        return this.http.get<any>(`${this.orderBaseUrl}`);
    }

    public getOrderDetails(orderId: string): Observable<any> {
        return this.http.get<any>(`${this.orderBaseUrl}/${orderId}`);
    }

}
