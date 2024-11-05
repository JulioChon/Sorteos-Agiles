import { EventEmitter, Injectable } from '@angular/core';
import { ModalOptions, ModalType } from '../../components/modal/modal.types';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  modal: EventEmitter<ModalOptions> = new EventEmitter();

  constructor() { }

  async openConfirmModal(message: string, title?: string): Promise<'confirm' | 'cancel'> {
    return new Promise((resolve) => {
      this.modal.emit({
        type: ModalType.CONFIRM,
        title: title,
        message,
        onConfirm: () => resolve('confirm'),
        onCancel: () => resolve('cancel'),
      });
    });
  }
}
