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
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EjerciciosComponent implements OnInit{

  actividadService = inject(ActividadesService)
  toast = inject(ToastrService)
  cdr = inject(ChangeDetectorRef)

  ejercicioSeleccionado: Ejercicio | null = null;
  ejercicios: Ejercicio[] = [];
  visible: boolean = false;

  ngOnInit(): void {
    this.getEjercicios()
  }

  showDialog() {
    this.visible = true;
  }

  getEjercicios() {
    this.actividadService.getEjercicios().subscribe({
      next: (res: Ejercicio[]) => {
        this.ejercicios = res
      },
      error: err => {
        this.toast.error(err.error.message)
        console.log(err)
      }
    })
  }

  guardarEjercicio(data: any) {
    console.log(data)
    this.actividadService.createEjercicio(data).subscribe({
      next: (res : Ejercicio) => {
        this.toast.success("Ejercicio creado con éxito")
        this.visible = false
        this.cdr.detectChanges()
      },
      error: err => {
        this.toast.error(err.error.message)
        console.log(err)
      }
    })
  }

}
