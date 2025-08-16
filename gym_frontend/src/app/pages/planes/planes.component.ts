import { ChangeDetectionStrategy, Component } from '@angular/core';
import { HeaderComponent } from '../../shared/header/header.component';

@Component({
  selector: 'app-planes',
  imports: [HeaderComponent],
  templateUrl: './planes.component.html',
  styleUrl: './planes.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PlanesComponent {

}
