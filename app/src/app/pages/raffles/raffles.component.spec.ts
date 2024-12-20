import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RafflesComponent } from './raffles.component';

describe('RafflesComponent', () => {
  let component: RafflesComponent;
  let fixture: ComponentFixture<RafflesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RafflesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RafflesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
