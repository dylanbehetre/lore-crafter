import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UniverseCreationTemplateDrivenFormComponent} from './universe-creation-template-driven-form.component';

describe('UniverseCreationTemplateDrivenFormComponent', () => {
  let component: UniverseCreationTemplateDrivenFormComponent;
  let fixture: ComponentFixture<UniverseCreationTemplateDrivenFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UniverseCreationTemplateDrivenFormComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(UniverseCreationTemplateDrivenFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
