import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UniverseCreationForm} from './universe-creation-form';

describe('UniverseCreationForm', () => {
  let component: UniverseCreationForm;
  let fixture: ComponentFixture<UniverseCreationForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UniverseCreationForm]
    })
      .compileComponents();

    fixture = TestBed.createComponent(UniverseCreationForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
