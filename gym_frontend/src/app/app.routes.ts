import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    { path: "login", component: LoginComponent },
    { path: "register", component: RegisterComponent },
    { path: "home", loadComponent:() => import('./pages/home/home.component').then(m => m.HomeComponent) },
    { path: "ejercicios", loadComponent:() => import('./pages/ejercicios/ejercicios.component').then(m => m.EjerciciosComponent)},
    { path: "planes", loadComponent:() => import('./pages/planes/planes.component').then(m => m.PlanesComponent) }
];
