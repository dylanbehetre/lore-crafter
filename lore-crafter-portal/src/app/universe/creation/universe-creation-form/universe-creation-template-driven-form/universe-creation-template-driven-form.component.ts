import {Component, model, ModelSignal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CreatingUniverse} from '../creating-universe.model';

@Component({
  selector: 'lcp-universe-creation-template-driven-form',
  imports: [FormsModule],
  templateUrl: './universe-creation-template-driven-form.component.html',
  styleUrl: './universe-creation-template-driven-form.component.css'
})
export class UniverseCreationTemplateDrivenFormComponent {

  creatingUniverseModelSignal: ModelSignal<CreatingUniverse> = model.required<CreatingUniverse>();

  onSubmit(): void {

  }

}
