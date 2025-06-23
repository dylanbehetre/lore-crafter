import {Routes} from '@angular/router';
import {UniverseCreationForm} from './universe/creation/universe-creation-form/universe-creation-form';

export const routes: Routes = [
  {
    path: '',
    component: UniverseCreationForm,
  },
  {
    path: 'universe',
    component: UniverseCreationForm,
  }
];
