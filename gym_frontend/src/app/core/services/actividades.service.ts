import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ejercicio, Plan } from '../../interfaces';
import { FORMERR } from 'dns';

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

getEjercicio(id: string): Observable<Ejercicio> {
  return this.#http.get<Ejercicio>(`${this.API_URL}/ejercicios/${id}`)
}

  createEjercicio(form: Ejercicio): Observable<Ejercicio> {
    return this.#http.post<Ejercicio>(`${this.API_URL}/ejercicios/crear-ejercicio`, form)
  }

  updateEjercicio(id: string,form: Ejercicio): Observable<Ejercicio>{ 
    return this.#http.put<Ejercicio>(`${this.API_URL}/ejercicios/editar-ejercicio/${id}`, form)
  }

  /**********************************************PLANES********************************************/ 

  getPlanes(): Observable<Plan[]> {
    return this.#http.get<Plan[]>(`${this.API_URL}/planes`)
  }

  getPlan(id: string): Observable<Plan> {
    return this.#http.get<Plan>(`${this.API_URL}/planes/${id}`)
  }

  createPlan(form: Plan): Observable<any> {
    return this.#http.post<any>(`${this.API_URL}/planes/crear-plan`, form)
  }
  
  updatePlan(id: string, form: Plan): Observable<any> {
    return this.#http.put<any>(`${this.API_URL}/planes/edit-plan/${id}`, form)
  }

  
}
