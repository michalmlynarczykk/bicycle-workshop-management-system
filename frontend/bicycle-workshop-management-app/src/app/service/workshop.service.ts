import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class WorkshopService {
    private authenticationBaseUrl = `${environment.apiBaseUrl}/workshop/external/v1/auth`;
}