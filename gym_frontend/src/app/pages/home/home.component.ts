import { ChangeDetectionStrategy, Component } from '@angular/core';
import { HeaderComponent } from "../../shared/header/header.component";
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';


@Component({
  selector: 'app-home',
  imports: [HeaderComponent, CardModule, ButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HomeComponent {

}
