import { TestBed } from '@angular/core/testing';

import { GestionAchatService } from './gestion-achat.service';

describe('GestionAchatService', () => {
  let service: GestionAchatService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionAchatService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
