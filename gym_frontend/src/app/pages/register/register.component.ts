import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { User } from '../../interfaces';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RegisterComponent {
  router = inject(Router)
  fb = inject(FormBuilder)
  authService = inject(AuthService)
  toastr = inject(ToastrService)
  form: FormGroup

  constructor() {
    this.form = this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      dni: ['', Validators.required],
      age: ['', Validators.required]
    })
  }

  goLogin() {
    this.router.navigate(["/login"])
  }

  register(){
    const formulario = {
      username: this.form.value.username,
      password: this.form.value.password,
      email: this.form.value.email,
      dni: this.form.value.dni,
      age: this.form.value.age
    }
    this.authService.register(formulario).subscribe({
      next: (res : User) => {
        this.toastr.success('Usuario creado con éxito')
        this.router.navigate(['/login'])
      },
      error: err => {
        console.log(err)
        this.toastr.error(err.error.message)
      }
    })
  }
}
