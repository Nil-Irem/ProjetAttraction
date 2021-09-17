import { TestBed } from '@angular/core/testing';

import { JoueurRightsService } from './joueur-rights.service';

describe('JoueurRightsService', () => {
  let service: JoueurRightsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JoueurRightsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
