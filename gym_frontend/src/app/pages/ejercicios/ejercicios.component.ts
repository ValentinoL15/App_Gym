import { ChangeDetectionStrategy, ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { Dialog } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { EjercicioFormComponent } from '../../features/ejercicio-form/ejercicio-form.component';
import { Ejercicio } from '../../interfaces';
import { ActividadesService } from '../../core/services/actividades.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-ejercicios',
  imports: [HeaderComponent, ButtonModule, Dialog,InputTextModule,EjercicioFormComponent],
  templateUrl: './ejercicios.component.html',
  styleUrl: './ejercicios.component.scss',
  changeDetection: ChangeDetectionStrategy.Default
})
export class EjerciciosComponent implements OnInit{

  actividadService = inject(ActividadesService)
  toast = inject(ToastrService)
  cdr = inject(ChangeDetectorRef)
  id: string | null = null;

  ejercicioSeleccionado: Ejercicio | null = null;
  ejercicios: Ejercicio[] = [];
  visible: boolean = false;

  ngOnInit(): void {
    this.getEjercicios()
  }

  showDialog(id: string | null) {
  this.id = id;

  if (id) {
    // Si hay id, es editar: buscar ejercicio
    const ejercicio = this.ejercicios.find(e => e.ejercicio_id === id);
    if (ejercicio) {
      this.ejercicioSeleccionado = { ...ejercicio }; // clon para no mutar la lista
    }
  } else {
    // Si es crear, limpiar
    this.ejercicioSeleccionado = null;
  }

  this.visible = true;
}


  getEjercicios() {
    this.actividadService.getEjercicios().subscribe({
      next: (res: Ejercicio[]) => {
        this.ejercicios = res
        console.log(this.ejercicios)
      },
      error: err => {
      console.log(err);
      }
    })
  }

  deleteEjercicio(id:string) {
    this.actividadService.deleteEjercicio(id).subscribe({
      next: (res : any) => {
        this.toast.success(res.message)
        this.getEjercicios()
      },
      error: err => {
        this.toast.error(err.error.message,"Error")
      }
    })
  }

  guardarEjercicio(data: any) {

    if(this.id){
      this.actividadService.updateEjercicio(this.id,data).subscribe({
        next: (res : any) => {
          this.toast.success("Ejercicio creado con éxito")
          this.visible = false
          this.id = null
          this.getEjercicios()
        },
        error: err => {
          console.log(err)
        }
      })
    } else {
      this.actividadService.createEjercicio(data).subscribe({
      next: (res : Ejercicio) => {
        this.toast.success("Ejercicio creado con éxito")
        this.visible = false
        this.getEjercicios()
      },
      error: err => {
         const mensaje = err?.error?.message || err?.message || 'Error desconocido';
      this.toast.error(mensaje);
      console.log(err);
       
      }
    })
  }
    }

    

}
