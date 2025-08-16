import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { FloatLabel } from 'primeng/floatlabel';
import { MultiSelectModule } from 'primeng/multiselect';

@Component({
  selector: 'app-ejercicio-form',
  imports: [FormsModule, InputTextModule,MultiSelectModule],
  templateUrl: './ejercicio-form.component.html',
  styleUrl: './ejercicio-form.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EjercicioFormComponent implements OnInit{

  cities!: any[];

  ngOnInit(): void {
       this.cities = [
            { name: 'New York', code: 'NY' },
            { name: 'Rome', code: 'RM' },
            { name: 'London', code: 'LDN' },
            { name: 'Istanbul', code: 'IST' },
            { name: 'Paris', code: 'PRS' },
        ];
  }

}
