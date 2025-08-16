import { ChangeDetectionStrategy, Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';
import { Dialog } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { EjercicioFormComponent } from '../../features/ejercicio-form/ejercicio-form.component';

@Component({
  selector: 'app-ejercicios',
  imports: [HeaderComponent, ButtonModule, Dialog,InputTextModule,EjercicioFormComponent],
  templateUrl: './ejercicios.component.html',
  styleUrl: './ejercicios.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EjerciciosComponent {

  visible: boolean = false;

    showDialog() {
        this.visible = true;
    }

}
