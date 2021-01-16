import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthserviceService } from '../services/authservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = new FormControl();
  password = new FormControl();
  public submitMessage: string;
  public user: FormGroup;
  errFlag: Boolean = false;

  constructor(private fb: FormBuilder, private authservice: AuthserviceService, private router: Router) {
  }

  ngOnInit() {
    // if (this.authservice.isAuthenticated()) {
    //   alert("You are already logged in")
    //   this.router.navigate(['/dashborad'])
    // }
    this.user = this.fb.group({
      username: ['', [Validators.required, Validators.pattern('[a-zA-Z]+'), Validators.minLength(3), Validators.maxLength(30)]],
      password: ['', Validators.required]
    })
  }

  loginSubmit() {
    console.log(this.user.value);
    let token = this.authservice.authuser(this.user.value)
    if (token) {
      this.authservice.storeToken(token)
      this.router.navigate(["/dashboard"])
      // location.reload(true)
    } else {
      alert("Invalid Credentials");
      this.router.navigate(["/login"])
    }
  }

  get username1() {
    return this.user.get('username');
  }

  get password1() {
    return this.user.get('password');
  }
}

