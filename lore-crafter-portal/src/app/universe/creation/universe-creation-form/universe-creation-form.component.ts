import {Component, OnInit} from '@angular/core';
import {
  UniverseCreationTemplateDrivenFormComponent
} from './universe-creation-template-driven-form/universe-creation-template-driven-form.component';
import {CreatingUniverse} from './creating-universe.model';

@Component({
  selector: 'lcp-universe-creation-form',
  imports: [
    UniverseCreationTemplateDrivenFormComponent
  ],
  templateUrl: './universe-creation-form.component.html',
  styleUrl: './universe-creation-form.component.css'
})
export class UniverseCreationFormComponent implements OnInit {

  protected creatingUniverse!: CreatingUniverse;

  ngOnInit(): void {
    this.creatingUniverse = new CreatingUniverse()
  }
}
