import {Component, EventEmitter, Output} from '@angular/core';
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  @Output('category') category: EventEmitter<string> = new EventEmitter<string>();

  constructor(
    private authService: AuthService
  ) {}

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  isAdmin() {
    return this.authService.isAdmin();
  }

  logout() {
    this.authService.logout();
  }

  setCategory(cat: string) {
    this.category.emit(cat);
  }

}
