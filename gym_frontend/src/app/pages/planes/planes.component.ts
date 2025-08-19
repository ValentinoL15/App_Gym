import { ChangeDetectionStrategy, Component, inject, OnInit } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { ActividadesService } from '../../core/services/actividades.service';
import { ToastrService } from 'ngx-toastr';
import { Plan } from '../../interfaces';
import { ButtonModule } from 'primeng/button';
import { Dialog } from 'primeng/dialog';
import { PlanFormComponent } from '../../features/plan-form/plan-form.component';
import { AccordionModule } from 'primeng/accordion';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-planes',
  imports: [HeaderComponent, ButtonModule,Dialog, PlanFormComponent,AccordionModule,DatePipe],
  templateUrl: './planes.component.html',
  styleUrl: './planes.component.scss',
  changeDetection: ChangeDetectionStrategy.Default
})
export class PlanesComponent implements OnInit{

  actividadService = inject(ActividadesService)
  toast = inject(ToastrService)
  router = inject(Router)
  visible: boolean = false;

  planes: Plan[] = [];

  ngOnInit(): void {
      this.getPlanes()
  }

    showDialog() {
      this.visible = true
    }

  getPlanes() {
    this.actividadService.getPlanes().subscribe({
      next: (res : Plan[]) => {
        this.planes = res
        console.log(this.planes)
      },
      error: err => {
        console.log(err)
      }
    })
  }

  guardarPlan(data:any) {
    this.actividadService.createPlan(data).subscribe({
      next: (res : any) => {
         this.toast.success("Plan creado con éxito")
        this.visible = false
        this.getPlanes()
      }, 
      error: err => {
        this.visible = false
        this.toast.error("Error al crear plan")
      }
    })
  }

  goPlan(id: string){
    this.router.navigate([`/myPlan/${id}`])
  }

}
