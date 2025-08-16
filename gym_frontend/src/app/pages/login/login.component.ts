import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent {
  router = inject(Router)
  fb = inject(FormBuilder)
  authService = inject(AuthService)
  form: FormGroup
  toastr = inject(ToastrService)

  constructor() {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  goRegister(){
    this.router.navigate(['/register'])
  }

  login() {
    const formulario = {
      username: this.form.value.username,
      password: this.form.value.password
    }
    console.log(formulario)
    console.log(localStorage.getItem('token'))
    this.authService.login(formulario).subscribe({
      next: (response: any) => {
        this.toastr.success(response.message)
        this.router.navigate(["/home"])
      },
      error: err => {
        
        console.log("Mi error:",err)
      }
    })
  }
}
