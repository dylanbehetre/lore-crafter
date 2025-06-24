import {Routes} from '@angular/router';
import {
  UniverseCreationFormComponent
} from './universe/creation/universe-creation-form/universe-creation-form.component';

export const routes: Routes = [
  {
    path: '',
    component: UniverseCreationFormComponent,
  },
  {
    path: 'universe',
    component: UniverseCreationFormComponent,
  }
];
