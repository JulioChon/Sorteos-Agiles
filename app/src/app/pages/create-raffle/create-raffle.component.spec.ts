import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRaffleComponent } from './create-raffle.component';

describe('CreateRaffleComponent', () => {
  let component: CreateRaffleComponent;
  let fixture: ComponentFixture<CreateRaffleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateRaffleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateRaffleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
