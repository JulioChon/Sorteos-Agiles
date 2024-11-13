import { TestBed } from '@angular/core/testing';

import { PayReminderConfigurationService } from './pay-reminder-configuration.service';

describe('PayReminderConfigurationService', () => {
  let service: PayReminderConfigurationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PayReminderConfigurationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
