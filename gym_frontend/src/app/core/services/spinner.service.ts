import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {
  private loading_ = signal(false)
  readonly loading = this.loading_.asReadonly()

  show(){
    this.loading_.set(true)
  }

  hide(){ 
    this.loading_.set(false)
  }
}
