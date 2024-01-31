import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from '../components/auth/registration/registration.component';
import { LoginComponent } from '../components/auth/login/login.component';
import { AuthGuard } from '../guards/auth.guard';
import { RoleGuard } from '../guards/role.guard';
import { UserRole } from '../model/roles.enum';
import { OwnerCandidateDashboardComponent } from '../components/owner-candidate-dashboard/owner-candidate-dashboard.component';
import { AssignedWorkshopComponent } from '../components/assigned-workshop/assigned-workshop.component';

export const routes: Routes = [
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  // { 
  //   path: 'owner-dashboard', 
  //   component: OwnerDashboardComponent, 
  //   canActivate: [AuthGuard, RoleGuard],
  //   data: { expectedRole: [UserRole.OWNER, UserRole.OWNER_CANDIDATE] }
  // },
  // { 
  //   path: 'mechanic-candidate-dashboard', 
  //   component: MechanicCandidateDashboardComponent, 
  //   canActivate: [AuthGuard, RoleGuard],
  //   data: { expectedRole: [UserRole.MECHANIC_CANDIDATE] }
  // },
  { 
    path: 'owner-candidate-dashboard', 
    component: OwnerCandidateDashboardComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.OWNER_CANDIDATE] }
  },
  { 
    path: 'workshops/assigned', 
    component: AssignedWorkshopComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.OWNER, UserRole.MECHANIC] }
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RouterRoutingModule { }
