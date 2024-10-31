import { Injectable } from '@angular/core';
import { Storage, getDownloadURL, ref, uploadBytesResumable } from '@angular/fire/storage';
import { File } from 'buffer';

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
}
