import { Injectable } from '@angular/core';
import { Storage, getDownloadURL, ref, uploadBytesResumable, deleteObject } from '@angular/fire/storage';

@Injectable({
  providedIn: 'root'
})
export class FirestorageService {

  pathImages = 'sorteos/images';

  constructor(private readonly storage: Storage) {}

  async uploadImage(file: File): Promise<string> {
    const storageRef = ref(this.storage, `${this.pathImages}/${file.name}`);
    const result = await uploadBytesResumable(storageRef, file);
    const url = await getDownloadURL(result.ref);
    return url;
  }

  async updateImage(file: File, url: string): Promise<string> {
    try {
      await this.removeImage(url);
    } catch (error) {
      console.error('Error removing image', error);
    }
    return this.uploadImage(file);
  }

  async removeImage(url: string): Promise<void> {
    const imageRef = ref(this.storage, url);
    await deleteObject(imageRef);
  }
}
