import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { RegistrationComponent } from './components/auth/registration/registration.component';
import { RouterRoutingModule } from './router/router-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/auth/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MechanicCandidateDashboardComponent } from './components/mechanic-candidate-dashboard/mechanic-candidate-dashboard.component';
import { OwnerCandidateDashboardComponent } from './components/owner-candidate-dashboard/owner-candidate-dashboard.component';
import { TokenInterceptor } from './token.interceptor';
import { AssignedWorkshopComponent } from './components/assigned-workshop/assigned-workshop.component';


export function tokenGetter() {
  return localStorage.getItem('token');
}

@NgModule({
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    NavbarComponent,
    MechanicCandidateDashboardComponent,
    OwnerCandidateDashboardComponent,
    AssignedWorkshopComponent
  ],
  imports: [
    NgbModule,
    BrowserAnimationsModule,
    HttpClientModule,
    BrowserModule,
    ReactiveFormsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-bottom-right',
    }),
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
      },
    }),
    RouterRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
