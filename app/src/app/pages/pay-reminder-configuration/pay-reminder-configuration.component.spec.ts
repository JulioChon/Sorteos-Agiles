import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayReminderConfigurationComponent } from './pay-reminder-configuration.component';

describe('PayReminderConfigurationComponent', () => {
  let component: PayReminderConfigurationComponent;
  let fixture: ComponentFixture<PayReminderConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PayReminderConfigurationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PayReminderConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
