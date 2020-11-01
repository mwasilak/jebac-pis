import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchesEditComponent } from './matches-edit.component';

describe('MatchesEditComponent', () => {
  let component: MatchesEditComponent;
  let fixture: ComponentFixture<MatchesEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatchesEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchesEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
