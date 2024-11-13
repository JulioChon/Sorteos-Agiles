import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-select-image',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './select-image.component.html',
  styleUrls: ['./select-image.component.scss'],
})
export class SelectImageComponent {
  @Output() file: EventEmitter<File> = new EventEmitter<File>();
  @Input() formInputName: string = '';
  public errorMessage: string | null = null; // Mensaje de error
  @Input() label: string;

  constructor() {}

  onImageSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];

    if (file) {
      if (file.type.startsWith('image/')) {
        this.file.emit(file);
        this.errorMessage = null; // Limpiar mensaje de error
      } else {
        this.errorMessage =
          'Por favor, selecciona un archivo de imagen válido.';
      }
    } else {
      this.errorMessage = 'No se ha seleccionado ningún archivo.';
    }
  }
}
