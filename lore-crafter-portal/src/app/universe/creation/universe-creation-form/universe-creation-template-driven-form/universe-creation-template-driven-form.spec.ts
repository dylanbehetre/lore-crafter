import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UniverseCreationTemplateDrivenForm} from './universe-creation-template-driven-form';

describe('UniverseCreationTemplateDrivenForm', () => {
  let component: UniverseCreationTemplateDrivenForm;
  let fixture: ComponentFixture<UniverseCreationTemplateDrivenForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UniverseCreationTemplateDrivenForm]
    })
      .compileComponents();

    fixture = TestBed.createComponent(UniverseCreationTemplateDrivenForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
