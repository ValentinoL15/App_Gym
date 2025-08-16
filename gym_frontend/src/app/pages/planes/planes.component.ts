import { ChangeDetectionStrategy, Component, inject, OnInit } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { ActividadesService } from '../../core/services/actividades.service';
import { ToastrService } from 'ngx-toastr';
import { Plan } from '../../interfaces';

@Component({
  selector: 'app-planes',
  imports: [HeaderComponent],
  templateUrl: './planes.component.html',
  styleUrl: './planes.component.scss',
  changeDetection: ChangeDetectionStrategy.Default
})
export class PlanesComponent implements OnInit{

  actividadService = inject(ActividadesService)
  toast = inject(ToastrService)

  planes: Plan[] = [];

  ngOnInit(): void {
      this.getPlanes()
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

}
