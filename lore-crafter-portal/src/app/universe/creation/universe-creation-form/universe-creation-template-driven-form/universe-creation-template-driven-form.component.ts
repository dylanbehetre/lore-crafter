import {Component, inject, model, ModelSignal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CreatingUniverse} from '../creating-universe.model';
import {UniverseService} from '../../../universe.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'lcp-universe-creation-template-driven-form',
  imports: [FormsModule],
  templateUrl: './universe-creation-template-driven-form.component.html',
  styleUrl: './universe-creation-template-driven-form.component.css'
})
export class UniverseCreationTemplateDrivenFormComponent {

  creatingUniverseModelSignal: ModelSignal<CreatingUniverse> = model.required<CreatingUniverse>();

  protected readonly universeService: UniverseService = inject(UniverseService);
  protected readonly snackBar: MatSnackBar = inject(MatSnackBar);

  protected onSubmit(): void {
    this.universeService.create(this.creatingUniverseModelSignal())
      .subscribe((id: number) => {
        this.snackBar.open(`L'univers a été créé avec l'id ${id}`)
      });
  }

}
