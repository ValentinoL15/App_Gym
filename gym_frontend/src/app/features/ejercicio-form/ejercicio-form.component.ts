import { ChangeDetectionStrategy, Component, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { FormBuilder, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { MultiSelectModule } from 'primeng/multiselect';
import { Ejercicio } from '../../interfaces';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ejercicio-form',
  imports: [FormsModule, InputTextModule, MultiSelectModule, ReactiveFormsModule],
  templateUrl: './ejercicio-form.component.html',
  styleUrl: './ejercicio-form.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EjercicioFormComponent implements OnInit,OnChanges{

  @Input() ejercicioToEdit: Ejercicio | null = null;
  @Output() formSubmit = new EventEmitter<any>();
  form!: FormGroup

  constructor(private fb: FormBuilder) {
    
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [this.ejercicioToEdit?.name || '', Validators.required],
      description: [this.ejercicioToEdit?.description || '', Validators.required],
      category: [this.ejercicioToEdit?.category || '', Validators.required]
    })
  }

 ngOnChanges(changes: SimpleChanges): void {
  if (changes['ejercicioToEdit']) {
    if (this.ejercicioToEdit) {
      // Llenar formulario con los datos del ejercicio
      this.patchForm();
    } else {
      // Si es null, resetear formulario
      this.form?.reset();
    }
  }
}

  private patchForm() {
    if (this.ejercicioToEdit) {
      this.form.patchValue({
        name: this.ejercicioToEdit.name,
        description: this.ejercicioToEdit.description,
        category: this.ejercicioToEdit.category
      })
    }
  }

  submit() {
    
      const ejercicioData = this.form.value
      console.log(this.form)

      if(this.ejercicioToEdit) {
        this.formSubmit.emit({ ...ejercicioData, ejercicio_id: this.ejercicioToEdit.ejercicio_id })
      } else {
        this.formSubmit.emit(ejercicioData)
      }

    }
  

}
