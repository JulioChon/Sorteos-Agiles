import { TestBed } from '@angular/core/testing';

import { RaffleTicketsService } from './raffle-tickets.service';

describe('RaffleTicketsService', () => {
  let service: RaffleTicketsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RaffleTicketsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
