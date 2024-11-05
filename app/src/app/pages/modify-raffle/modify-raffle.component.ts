import { CommonModule, Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Params, Raffle } from './modify-raffle.types';
import { ModifyRaffleService } from './modify-raffle.service';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { SpinnerComponent } from '../../components/spinner/spinner.component';
import { SelectImageComponent } from '../../components/select-image/select-image.component';

@Component({
  selector: 'app-modify-raffle',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HeaderComponent,
    FooterComponent,
    SpinnerComponent,
    SelectImageComponent
  ],
  templateUrl: './modify-raffle.component.html',
  styleUrl: './modify-raffle.component.scss',
})
export class ModifyRaffleComponent implements OnInit {
  
  private params: Params;
  raffle: Raffle;
  updateRaffleForm: FormGroup;
  updateImagePreview: string | ArrayBuffer | null = null;
  updateSelectedFile: File | null = null;
  showLoading: boolean = true;

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly modifyRaffleService: ModifyRaffleService,
    private readonly formBuilder: FormBuilder,
    private readonly location: Location
  ) {}

  async ngOnInit(): Promise<void> {
    this.params = await this.parseUrlParams();
    this.findRaffleById(this.params.id);
  }

  parseUrlParams(): Promise<Params> {
    return new Promise((resolve) => {
      this.activatedRoute.params.subscribe((params) => {
        const mappedParams: Params = { id: params['id'] };
        resolve(mappedParams);
      });
    });
  }

  findRaffleById(id: string): void {
    this.showLoading = true;
    this.modifyRaffleService.findRaffleById(id).subscribe({
      next: (raffle: Raffle) => {
        this.raffle = raffle;
        this.createUpdateRaffleForm(raffle);
      },
      error: (error) => {},
      complete: () => {
        this.showLoading = false;
      },
    });
  }

  createUpdateRaffleForm(raffle: Raffle): void {
    this.updateRaffleForm = this.formBuilder.group({
      title: [raffle.title, [Validators.required]],
      raffleImage: [raffle.raffleImage, [Validators.required]],
      ticketsMax: [raffle.maxRange, [Validators.required]],
      ticketsMin: [raffle.minRange, [Validators.required]],
      startDate: [raffle.startDate, [Validators.required]],
      endDate: [raffle.endDate, [Validators.required]],
      raffleDate: [raffle.raffleDate, [Validators.required]],
      status: [raffle.status, [Validators.required]],
    });
  }

  get title() {
    return this.updateRaffleForm.get('title');
  }

  get raffleImage() {
    return this.updateRaffleForm.get('raffleImage');
  }

  get ticketsMax() {
    return this.updateRaffleForm.get('ticketsMax');
  }

  get ticketsMin() {
    return this.updateRaffleForm.get('ticketsMin');
  }

  get startDate() {
    return this.updateRaffleForm.get('startDate');
  }

  get endDate() {
    return this.updateRaffleForm.get('endDate');
  }

  get raffleDate() {
    return this.updateRaffleForm.get('raffleDate');
  }

  get status() {
    return this.updateRaffleForm.get('status');
  }

  onFileSelected(file: File): void {
    this.updateSelectedFile = file;
    const reader = new FileReader();
    reader.onload = () => {
      this.updateImagePreview = reader.result;
    };
    reader.readAsDataURL(file);
  }

  onResetImage(): void {
    this.updateSelectedFile = null;
    this.updateImagePreview = null;
  }

  goBack(): void {
    this.location.back();
  }
}
