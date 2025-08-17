import { ChangeDetectionStrategy, Component, EventEmitter, inject, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { MultiSelectModule } from 'primeng/multiselect';
import { Ejercicio, Rutina } from '../../interfaces';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActividadesService } from '../../core/services/actividades.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-rutina-form',
  imports: [MultiSelectModule, ReactiveFormsModule, CommonModule,FormsModule],
  templateUrl: './rutina-form.component.html',
  styleUrl: './rutina-form.component.scss',
  changeDetection: ChangeDetectionStrategy.Default
})
export class RutinaFormComponent implements OnInit,OnChanges{

  actividadesService = inject(ActividadesService)

  @Input() rutinaToEdit: Rutina | null = null
  @Input() ejercicios: Ejercicio[] = [];
  @Output() formSubmit = new EventEmitter<any>();

  form!: FormGroup

  constructor(private fb: FormBuilder) {

  }

  ngOnInit(): void {
      this.form = this.fb.group({
        ejercicio_id: [this.rutinaToEdit?.ejercicio.ejercicio_id || '', Validators.required],
        repeticiones: [this.rutinaToEdit?.repeticiones || '', Validators.required],
        series: [this.rutinaToEdit?.series || '', Validators.required],
        peso: [this.rutinaToEdit?.peso || '', Validators.required],
        fase: [this.rutinaToEdit?.fase || '', Validators.required]
      })
  }

   ngOnChanges(changes: SimpleChanges): void {
  if (changes['rutinaToEdit']) {
    if (this.rutinaToEdit) {
      // Llenar formulario con los datos del ejercicio
      this.patchForm();
    } else {
      // Si es null, resetear formulario
      this.form?.reset();
    }
  }
}

   private patchForm() {
    if (this.rutinaToEdit) {
      this.form.patchValue({
        ejercicio_id: this.rutinaToEdit.ejercicio.ejercicio_id,
        repeticiones: this.rutinaToEdit.repeticiones,
        series: this.rutinaToEdit.series,
        peso: this.rutinaToEdit.peso,
        fase: this.rutinaToEdit.fase
      })
    }
  }

    submit() {
    
      const rutinaData = this.form.value
      console.log(rutinaData)

      if(this.rutinaToEdit) {
        this.formSubmit.emit({ ...rutinaData, rutina_id: this.rutinaToEdit.rutina_id })
        this.form.reset()
      } else {
        this.formSubmit.emit(rutinaData)
        this.form.reset()
      }

    }


}
