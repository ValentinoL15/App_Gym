import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Plan } from '../../interfaces';

@Component({
  selector: 'app-plan-form',
  imports: [ReactiveFormsModule],
  templateUrl: './plan-form.component.html',
  styleUrl: './plan-form.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PlanFormComponent implements OnInit,OnChanges{

  @Input() planToEdit: Plan | null = null;
  @Output() formPlanSubmit = new EventEmitter<any>();

  form!: FormGroup

  constructor(private fb: FormBuilder) {

  }

  ngOnInit(): void {
      this.form = this.fb.group({
        desde: [this.planToEdit?.desde || '', Validators.required],
        hasta: [this.planToEdit?.hasta || '', Validators.required],
        finalidad: [this.planToEdit?.finalidad || '', Validators.required]
      })
  }

  ngOnChanges(changes: SimpleChanges): void {
      if(changes['planToEdit']) {
        if(this.planToEdit) {
          this.patchForm()
        }
      } else {
        this.form?.reset()
      }

  }

   private patchForm() {
    if (this.planToEdit) {
      this.form.patchValue({
        desde: this.planToEdit.desde,
        hasta: this.planToEdit.hasta,
        finalidad: this.planToEdit.finalidad
      })
    }
  }

  submit(){
    const planData = this.form.value

    if(this.planToEdit){
      this.formPlanSubmit.emit({ ...planData, plan_id: this.planToEdit.plan_id })
    } else {
      this.formPlanSubmit.emit(planData)
    }
  }

}
