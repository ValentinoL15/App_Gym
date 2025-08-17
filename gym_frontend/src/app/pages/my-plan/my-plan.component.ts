import { ChangeDetectionStrategy, Component, inject, OnInit } from '@angular/core';
import { HeaderComponent } from "../../shared/header/header.component";
import { Ejercicio, Plan, Rutina } from '../../interfaces';
import { ActividadesService } from '../../core/services/actividades.service';
import { ActivatedRoute } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { Dialog } from 'primeng/dialog';
import { RutinaFormComponent } from "../../features/rutina-form/rutina-form.component";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-my-plan',
  imports: [HeaderComponent, ButtonModule, Dialog, RutinaFormComponent],
  templateUrl: './my-plan.component.html',
  styleUrl: './my-plan.component.scss',
  changeDetection: ChangeDetectionStrategy.Default
})
export class MyPlanComponent implements OnInit{

  actividadService = inject(ActividadesService)
  route = inject(ActivatedRoute)
  toast = inject(ToastrService)
  id:any
  visible: boolean = false;
  plan: Plan | null = null;
  rutinaSeleccionada: Rutina | null = null;
  ejercicios: Ejercicio[] = [];
  rutinas: Rutina[] = [];

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id']
    })
    this.getEjercicios()
    this.getRutinas()
  }

  getEjercicios() {
    this.actividadService.getEjercicios().subscribe({
      next: (res : any) => {
        this.ejercicios = res
      },
      error: err => {
        console.log(err)
      }
    })
  }

  getPlan() {
    this.actividadService.getPlan(this.id).subscribe({
      next: (res : any) => {
        this.plan = res
      },
      error: err => {
        console.log(err)
      }
    })
  }

  getRutinas() {
    this.actividadService.getRutinas().subscribe({
      next: (res: any) => {
        this.rutinas = res
        console.log("ruti" , this.rutinas)
      },
      error: err => {
        console.log(err)
      }
    })
  }

      showDialog() {
      this.visible = true
    }

    guardarRutina(data : any) {
      this.actividadService.createRutina(this.id,data).subscribe({
        next: (res : any) => {
          this.toast.success("Rutina creada con éxito")
          this.visible = false
          this.getPlan()
          this.getRutinas()
          this.getEjercicios()
        },
        error: err => {
          console.log(err)
        }
      })
    }
    
}
