import { TestBed } from '@angular/core/testing';

import { GestionFiltreService } from './gestion-filtre.service';

describe('GestionFiltreService', () => {
  let service: GestionFiltreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionFiltreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
