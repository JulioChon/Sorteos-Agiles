import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaffleGalleryComponent } from './raffle-gallery.component';

describe('RaffleGalleryComponent', () => {
  let component: RaffleGalleryComponent;
  let fixture: ComponentFixture<RaffleGalleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RaffleGalleryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RaffleGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
