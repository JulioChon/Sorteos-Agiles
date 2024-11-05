import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyRaffleComponent } from './modify-raffle.component';

describe('ModifyRaffleComponent', () => {
  let component: ModifyRaffleComponent;
  let fixture: ComponentFixture<ModifyRaffleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifyRaffleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifyRaffleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
