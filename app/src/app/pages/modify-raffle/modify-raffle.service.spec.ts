import { TestBed } from '@angular/core/testing';

import { ModifyRaffleService } from './modify-raffle.service';

describe('ModifyRaffleService', () => {
  let service: ModifyRaffleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModifyRaffleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
