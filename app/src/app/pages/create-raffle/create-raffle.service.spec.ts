import { TestBed } from '@angular/core/testing';

import { CreateRaffleService } from './create-raffle.service';

describe('CreateRaffleService', () => {
  let service: CreateRaffleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateRaffleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
