import { ChangeDetectionStrategy, Component, inject, OnInit } from '@angular/core';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';
import { Menubar } from 'primeng/menubar';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-header',
  imports: [Menubar,ButtonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderComponent implements OnInit{

  router = inject(Router)
  authService = inject(AuthService)

  items: MenuItem[] | undefined;

  ngOnInit() {
        this.items = [
            {
                label: 'Home',
                icon: 'pi pi-home',
                command: () => {
                  this.router.navigate(["/home"])
                }
            },
            {
                label: 'Ejercicios',
                icon: 'pi pi-star',
                command: () => {
                  this.router.navigate(["/ejercicios"])
                }
            },
            {
                label: 'Planes',
                icon: 'pi pi-check',
                command: () => {
                  this.router.navigate(["/planes"])
                }
            },
            {
                label: 'Perfil',
                icon: 'pi pi-user'
            }
        ]
  }

  logout(){
    this.authService.logout()
  }

}
