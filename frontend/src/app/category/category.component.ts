import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Product} from "../../models/product";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {

  @Input() category: any = {id: 0, name: ''};

  @Input() route: String = '';

  @Output('deleteCategory') deleteCategory: EventEmitter<number> = new EventEmitter<number>();

  @Output('editCategory') editCategory: EventEmitter<number> = new EventEmitter<number>();

  edit() {
    this.editCategory.emit(this.category.id);
  }

  delete() {
    this.deleteCategory.emit(this.category.id);
  }

}
