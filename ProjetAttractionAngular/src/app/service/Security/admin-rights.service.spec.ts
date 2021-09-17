import { TestBed } from '@angular/core/testing';

import { AdminRightsService } from './admin-rights.service';

describe('AdminRightsService', () => {
  let service: AdminRightsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminRightsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
