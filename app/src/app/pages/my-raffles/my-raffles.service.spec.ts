import { TestBed } from '@angular/core/testing';

import { MyRafflesService } from './my-raffles.service';

describe('MyRafflesService', () => {
  let service: MyRafflesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyRafflesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
