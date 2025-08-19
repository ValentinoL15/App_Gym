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
    { path: "profile", loadComponent: () => import('./pages/profile/profile.component').then(m => m.ProfileComponent) },
    { path: "ejercicios", loadComponent:() => import('./pages/ejercicios/ejercicios.component').then(m => m.EjerciciosComponent)},
    { path: "planes", loadComponent:() => import('./pages/planes/planes.component').then(m => m.PlanesComponent) },
    { path: "myPlan/:id", loadComponent:() => import('./pages/my-plan/my-plan.component').then(m => m.MyPlanComponent) }
];
