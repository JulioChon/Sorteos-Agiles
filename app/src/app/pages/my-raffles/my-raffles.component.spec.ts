import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyRafflesComponent } from './my-raffles.component';

describe('MyRafflesComponent', () => {
  let component: MyRafflesComponent;
  let fixture: ComponentFixture<MyRafflesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyRafflesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyRafflesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
