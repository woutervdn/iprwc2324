import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  @Output('category') category: EventEmitter<string> = new EventEmitter<string>();

  setCategory(cat: string) {
    this.category.emit(cat);
  }

}
