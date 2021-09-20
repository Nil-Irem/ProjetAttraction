import { TestBed } from '@angular/core/testing';

import { GestionElementService } from './gestion-element.service';

describe('GestionElementService', () => {
  let service: GestionElementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionElementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
