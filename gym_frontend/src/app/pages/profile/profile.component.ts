import { ChangeDetectionStrategy, Component, inject, OnInit } from '@angular/core';
import { HeaderComponent } from "../../shared/header/header.component";
import { AuthService } from '../../core/services/auth.service';
import { User } from '../../interfaces';

@Component({
  selector: 'app-profile',
  imports: [HeaderComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProfileComponent implements OnInit{
  authService = inject(AuthService)
  user: User | null = null;

  ngOnInit(): void {
    this.getUser()
  }

  getUser() {
    this.authService.getUser().subscribe({
      next: (res : any) => {
        this.user = res
        console.log(this.user)
      }, 
      error: err => {
        console.log(err)
      }
    })
  }

}
