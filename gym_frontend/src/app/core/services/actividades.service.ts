import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ejercicio } from '../../interfaces';

@Injectable({
  providedIn: 'root'
})
export class ActividadesService {

  API_URL = 'http://localhost:8080/api'
  #http = inject(HttpClient)

  /*********************************************EJERCICIOS***********************************************/
  
getEjercicios(): Observable<Ejercicio[]> {
  return this.#http.get<Ejercicio[]>(`${this.API_URL}/ejercicios`)
}

  createEjercicio(form: Ejercicio): Observable<Ejercicio> {
    return this.#http.post<Ejercicio>(`${this.API_URL}/ejercicios/crear-ejercicio`, form)
  }
  
}
