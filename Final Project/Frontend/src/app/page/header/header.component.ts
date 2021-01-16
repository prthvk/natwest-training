import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DialogboxComponent } from '../dialogbox/dialogbox.component';
import { AuthserviceService } from '../services/authservice.service';
import { HeaderservService } from '../services/headerserv.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public display: boolean;
  public blockedStatus;
  public currentDebitLimit;
  public newDebitLimit; // the debit limit set using the dialog box is stored in this variable

  constructor(private authserv: AuthserviceService, private router: Router, private dialog: MatDialog, private headserv: HeaderservService) {
    this.authserv.data.subscribe(
      value => {
        this.display = value;
        // Add code to fetch blocked status and debit limits
        // this.fetchDataTransactions.data.subscribe(
        //   value => {
        //     this.blockedStatus = value;
        //     this.currentDebitLimit = value;
        //   });
      });
  }

  ngOnInit() {
    // this.blockedStatus=true;
    return this.headserv.getDebitLimit().subscribe(
      data => {
        this.currentDebitLimit = data[""]; // add respective properties to get the current limit
        this.blockedStatus = data[""];  // add respective properties to get the blocked status
      },
      error => {
        console.log(error.message);
      })
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogboxComponent, {
      width: '350px',
      height: '250px',
      data: { debitLimit: this.currentDebitLimit }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.newDebitLimit = result;
      console.log(this.newDebitLimit);
    });
  }

  blockCard() {
    // put call to update blocked status as blocked
    //if success the alert
    this.headserv.setBlockedStatus(false).subscribe(
      data => {
        this.blockedStatus = true;
      },
      error => {
        alert(error.message);
      }
    )
    alert("The card has been blocked!");

    // else
    // alert("Error message")
  }

  unblockCard() {
    // put call to update blocked status as unblocked
    //if success the alert

    this.headserv.setBlockedStatus(true).subscribe(
      data => {
        this.blockedStatus = false;
      },
      error => {
        alert(error.message);
      }
    )
    alert("The card has been unblocked!");

    // else
    // alert("Error message")
  }

  logout() {
    if (this.authserv.isAuthenticated()) {
      console.log("The token is", this.authserv.getToken());
      this.authserv.deleteToken()
      alert("You are now logged out")
      this.router.navigate(['/login'])
    } else {
      alert("You have not logged in")
    }
  }
}
