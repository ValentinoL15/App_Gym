export interface User {
    user_id: string,
    username: string,
    email: string,
    dni: string,
    age: number,
    ejercicios: Array<Ejercicio>,
    planes: Array<Plan>
}

export interface Ejercicio {
    ejercicio_id?: string,
    name: string,
    description: string,
    category: string,
    usuario?: User
}

export interface Rutina {
    rutina_id: string,
    ejercicio: Ejercicio,
    repeticiones: number,
    series: number,
    peso: number,
    fase: string,
    plan: Plan
}

export interface Plan {
    plan_id: string,
    usuario: User,
    desde: Date,
    hasta: Date,
    finalidad: string,
    rutinas: Array<Rutina>
    seguimientos: Array<Seguimiento>
}

export interface Seguimiento {
    seguimiento_id: string,
    fecha: Date,
    description: string,
    plan: Plan
}

export interface FormRegister {
    username: string,
    password: string,
    dni: string,
    age: string,
    email: string
}

export interface LoginForm {
    username: string,
    password: string
}