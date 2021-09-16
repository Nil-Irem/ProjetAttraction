import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoixParcComponent } from './choix-parc.component';

describe('ChoixParcComponent', () => {
  let component: ChoixParcComponent;
  let fixture: ComponentFixture<ChoixParcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoixParcComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoixParcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
