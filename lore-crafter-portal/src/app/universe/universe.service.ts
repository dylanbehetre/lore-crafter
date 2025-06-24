import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CreatingUniverse} from './creation/universe-creation-form/creating-universe.model';
import {map, Observable} from 'rxjs';
import {UniverseCreationCommand, UniverseCreationResult} from './api';

@Injectable({
  providedIn: 'root'
})
export class UniverseService {

  private readonly httpClient: HttpClient = inject(HttpClient);
  private readonly universeApiBaseUrl = 'http://localhost:3000';

  create(creatingUniverse: CreatingUniverse): Observable<number> {
    const creationCommand: UniverseCreationCommand = {
      name: creatingUniverse.name,
      description: creatingUniverse.description
    };

    return this.httpClient.post<UniverseCreationResult>(`${this.universeApiBaseUrl}/universes`, creationCommand)
      .pipe(map((universeCreationResult: UniverseCreationResult) => universeCreationResult.id));
  }
}
