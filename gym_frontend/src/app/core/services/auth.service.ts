import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { FormRegister, LoginForm, User } from '../../interfaces';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  API_URL = 'http://localhost:8080/api/auth'

  #http = inject(HttpClient)
  router = inject(Router)

  register(form: FormRegister): Observable<User> {
    return this.#http.post<User>(`${this.API_URL}/register`, form)
  }

  login(form: LoginForm): Observable<{username: string, message: string,jwt: string}>{
    return this.#http.post<{username: string, message: string,jwt: string}>(`${this.API_URL}/login`, form).pipe(
      tap(response => {
        localStorage.setItem('token', response.jwt)
      }),
    )
  }

  logout() {
    localStorage.removeItem('token')
    this.router.navigate(["/login"])
  }

  getToken() {
    return localStorage.getItem('token')
  }
}
