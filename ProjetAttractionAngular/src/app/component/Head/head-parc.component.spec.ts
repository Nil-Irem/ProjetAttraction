import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeadParcComponent } from './head-parc.component';

describe('HeadParcComponent', () => {
  let component: HeadParcComponent;
  let fixture: ComponentFixture<HeadParcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeadParcComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeadParcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
