import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SpinnerService } from '../services/spinner.service';
import { finalize } from 'rxjs';

export const spinnerInterceptor: HttpInterceptorFn = (req, next) => {

 const spinner = inject(SpinnerService) 

 spinner.show()

    return next(req).pipe(
    finalize(() => spinner.hide())
  );
};
