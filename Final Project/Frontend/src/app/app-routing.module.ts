import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './page/login/login.component';
import { DashboardComponent } from './page/dashboard/dashboard.component';
import { GuardGuard } from './guard.guard'

const routes: Routes = [
  {
    path:"login",
    component: LoginComponent
  },
  {
    path:"dashboard",
    component:DashboardComponent,
    canActivate:[GuardGuard]
  },
  {
    path: "",
    redirectTo: "/login",
    pathMatch: 'full'
  },
  {
    path: "**",
    component: LoginComponent
  }
];

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
