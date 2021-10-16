import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl('', [
      Validators.minLength(5),
      Validators.required
    ]),
    password: new FormControl('', [
      Validators.minLength(8),
      Validators.required
    ]),
    firstName: new FormControl('', [
      Validators.minLength(2),
      Validators.required
    ]),
    lastName: new FormControl('', [
      Validators.minLength(2),
      Validators.required
    ])
  });

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  sendForm(form) {
    if (form.valid) {
      this.authService.register(form.value).subscribe(
        data => {
          this.router.navigateByUrl('/login').then();
        }
      );
    }
  }

}
