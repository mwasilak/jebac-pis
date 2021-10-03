import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionsAddComponent } from './competitions-add.component';

describe('CompetitionAddComponent', () => {
  let component: CompetitionsAddComponent;
  let fixture: ComponentFixture<CompetitionsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompetitionsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetitionsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
