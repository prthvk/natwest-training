import { TestBed } from '@angular/core/testing';

import { HeaderservService } from './headerserv.service';

describe('HeaderservService', () => {
  let service: HeaderservService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HeaderservService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
