import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

import { AuthService } from "../../services/auth.service";
import { FormControl, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup= new FormGroup({
    username: new FormControl('', [
      Validators.minLength(2)
    ]),
    password: new FormControl('', [
      Validators.minLength(2)
    ])
  });

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  sendForm(form) {
    if (form.valid) {
      this.authService.authenticate(form.value).subscribe(
        data => {
          this.router.navigateByUrl('/');
        }
      );
    }
  }
}
