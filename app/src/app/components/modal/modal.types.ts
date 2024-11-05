export interface ModalOptions {
    type: ModalType;
    title: string;
    message: string;
    onConfirm: Function;
    onCancel: Function;
}

export enum ModalType {
    CONFIRM = 'confirm',
    INFO = 'info',
}