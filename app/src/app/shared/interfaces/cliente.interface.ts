export interface Cliente {
    nombre: string;
    correo: string;
    telefono: string;
    contrasenia: string;
    role?: 'ADMIN' | 'USER';
}