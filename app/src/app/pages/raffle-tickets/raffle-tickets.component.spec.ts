import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaffleTicketsComponent } from './raffle-tickets.component';

describe('RaffleTicketsComponent', () => {
  let component: RaffleTicketsComponent;
  let fixture: ComponentFixture<RaffleTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RaffleTicketsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RaffleTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
