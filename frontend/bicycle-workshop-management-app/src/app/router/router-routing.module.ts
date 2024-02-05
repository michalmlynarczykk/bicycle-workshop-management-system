import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from '../components/auth/registration/registration.component';
import { LoginComponent } from '../components/auth/login/login.component';
import { AuthGuard } from '../guards/auth.guard';
import { RoleGuard } from '../guards/role.guard';
import { UserRole } from '../model/roles.enum';
import { OwnerCandidateDashboardComponent } from '../components/owner-candidate-dashboard/owner-candidate-dashboard.component';
import { AssignedWorkshopComponent } from '../components/assigned-workshop/assigned-workshop.component';
import { MechanicCandidateDashboardComponent } from '../components/mechanic-candidate-dashboard/mechanic-candidate-dashboard.component';
import { OwnerDashboardComponent } from '../components/owner-dashboard/owner-dashboard.component';
import { OrdersDashboarComponent } from '../components/order/orders-dashboar/orders-dashboar.component';
import { CreateOrderComponent } from '../components/order/create-order/create-order.component';
import { HomeComponent } from '../components/home/home.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { 
    path: 'owner-candidate-dashboard', 
    component: OwnerCandidateDashboardComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.OWNER_CANDIDATE], label: 'Create workshop'}
  },
  { 
    path: 'workshops/join-requests', 
    component: OwnerDashboardComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.OWNER], label: 'Workshop join requests'}
  },
  { 
    path: 'mechanic-candidate-dashboard', 
    component: MechanicCandidateDashboardComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.MECHANIC_CANDIDATE], label: 'Join Workshop'}
  },
  { 
    path: 'workshops/assigned', 
    component: AssignedWorkshopComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.OWNER, UserRole.MECHANIC], label: 'My workshop'}
  },
  { 
    path: 'orders', 
    component: OrdersDashboarComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.OWNER, UserRole.MECHANIC], label: 'Orders'}
  },
  { 
    path: 'orders/create', 
    component: CreateOrderComponent, 
    canActivate: [AuthGuard, RoleGuard],
    data: { expectedRole: [UserRole.OWNER, UserRole.MECHANIC], label: 'Add order'}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RouterRoutingModule { }
