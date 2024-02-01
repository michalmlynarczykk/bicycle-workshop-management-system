import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class WorkshopService {
    private workshopBaseUrl = `${environment.apiBaseUrl}/workshop/external/v1/workshops`;

    constructor(
        private http: HttpClient
    ) { }

    public createWorkshop(createWorkshopRequest: any): Observable<any> {
        return this.http.post<any>(`${this.workshopBaseUrl}`, createWorkshopRequest);
    }

    public getAssignedWorkshop(): Observable<any> {
        return this.http.get<any>(`${this.workshopBaseUrl}/assigned`);
    }

    public joinWorkshop(workshopJoinRequest: any): Observable<any> {
        return this.http.post<any>(`${this.workshopBaseUrl}/join-requests`, workshopJoinRequest);
    }

    public getWorkshopJoinRequests(): Observable<any> {
        return this.http.get<any>(`${this.workshopBaseUrl}/join-requests`);
    }

    public approveRejectJoinRequest(joinRequestId: string, approve: boolean) {

        const params = new HttpParams().set('approved', approve);
        const options = { params: params };

        return this.http.patch(`${this.workshopBaseUrl}/join-requests/${joinRequestId}`, null, options);
    }
}
