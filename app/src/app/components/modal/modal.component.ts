import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AlertService } from '../../shared/services/alert.service';
import { ModalOptions } from './modal.types';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.scss',
})
export class ModalComponent implements OnInit {
  showModal: boolean = false;
  shake: boolean = false;
  modalOptions: ModalOptions;

  constructor(private readonly alertService: AlertService) {}

  ngOnInit() {
    this.suscribeToModal();
  }

  async suscribeToModal() {
    this.alertService.modal.subscribe({
      next: (modalOptions: ModalOptions) => {
        this.modalOptions = modalOptions;
        this.openModal();
      },
    });
  }

  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
    this.modalOptions.onCancel();
  }

  confirm() {
    this.showModal = false;
    this.modalOptions.onConfirm();
  }
}
