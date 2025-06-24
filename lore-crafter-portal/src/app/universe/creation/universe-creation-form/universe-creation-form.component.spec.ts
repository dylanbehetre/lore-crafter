import {ComponentFixture, TestBed} from '@angular/core/testing';

import {UniverseCreationFormComponent} from './universe-creation-form.component';

describe('UniverseCreationFormComponent', () => {
  let component: UniverseCreationFormComponent;
  let fixture: ComponentFixture<UniverseCreationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UniverseCreationFormComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(UniverseCreationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
