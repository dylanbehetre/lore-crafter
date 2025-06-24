import {Component} from '@angular/core';
import {
  UniverseCreationTemplateDrivenFormComponent
} from './universe-creation-template-driven-form/universe-creation-template-driven-form.component';
import {CreatingUniverse} from './creating-universe.model';
import {FormsModule} from '@angular/forms';
import {MatTabsModule} from '@angular/material/tabs';

@Component({
  selector: 'lcp-universe-creation-form',
  imports: [
    UniverseCreationTemplateDrivenFormComponent,
    MatTabsModule,
    FormsModule
  ],
  templateUrl: './universe-creation-form.component.html',
  styleUrl: './universe-creation-form.component.css'
})
export class UniverseCreationFormComponent {

  protected creatingUniverse: CreatingUniverse = new CreatingUniverse();
}
