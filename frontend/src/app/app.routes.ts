import { Routes } from '@angular/router';
import {
  PageNotFoundErrorPageComponent
} from './components/util/page-not-found-error-page/page-not-found-error-page.component';
import {CustomerList} from './components/customer-list/customer-list';
import {MovieList} from './components/movie-list/movie-list';

export const APP_ROUTES: Routes = [
  {
    path: '',
    children: [
      {path: '', redirectTo: 'customers', pathMatch: 'full'},
      {path: 'customers', component: CustomerList},
      {path: 'movies', component: MovieList},
      {path: 'not-found', component: PageNotFoundErrorPageComponent},
      {path: '**', redirectTo: 'not-found', pathMatch: 'full'},
    ],
  },
];

