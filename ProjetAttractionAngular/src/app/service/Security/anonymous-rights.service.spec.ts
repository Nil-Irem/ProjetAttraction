import { TestBed } from '@angular/core/testing';

import { AnonymousRightsService } from './anonymous-rights.service';

describe('AnonymousRightsService', () => {
  let service: AnonymousRightsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnonymousRightsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
