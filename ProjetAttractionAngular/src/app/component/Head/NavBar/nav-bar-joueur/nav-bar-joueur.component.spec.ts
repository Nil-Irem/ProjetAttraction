import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarJoueurComponent } from './nav-bar-joueur.component';

describe('NavBarJoueurComponent', () => {
  let component: NavBarJoueurComponent;
  let fixture: ComponentFixture<NavBarJoueurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavBarJoueurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavBarJoueurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
