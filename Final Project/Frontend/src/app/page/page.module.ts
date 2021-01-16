import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { LoginComponent } from './login/login.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatCardModule } from '@angular/material/card';
import { FooterComponent } from './footer/footer.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { AppRoutingModule } from '../app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatDialogModule} from '@angular/material/dialog';
import { DialogboxComponent } from './dialogbox/dialogbox.component';

@NgModule({
  declarations: [HeaderComponent, LoginComponent, FooterComponent, DashboardComponent, DialogboxComponent],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatMenuModule,
    BrowserAnimationsModule,
    MatIconModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule, 
    ReactiveFormsModule,
    MatNativeDateModule,
    MatExpansionModule,
    MatCardModule,
    MatDatepickerModule,
    MatDialogModule
  ],
  exports:[HeaderComponent,LoginComponent,FooterComponent,DashboardComponent]
})
export class PageModule { }
