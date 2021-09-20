import { TestBed } from '@angular/core/testing';

import { PartieChosenRightsService } from './partie-chosen-rights.service';

describe('PartieChosenRightsService', () => {
  let service: PartieChosenRightsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PartieChosenRightsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
