import { Routes } from '@angular/router';
import {
  PageNotFoundErrorPageComponent
} from './components/util/page-not-found-error-page/page-not-found-error-page.component';
import {MovieList} from './components/movie-list/movie-list';
import {inject} from '@angular/core';
import {CustomerControllerService} from '@workflex-api-services/customerController.service';
import {MovieControllerService} from '@workflex-api-services/movieController.service';

export const APP_ROUTES: Routes = [
  {
    path: '',
    children: [
      {path: '', redirectTo: 'customers', pathMatch: 'full'},
      {
        path: 'customers',
        title: 'Customers',
        loadComponent: () => import('./components/customer-list/customer-list').then(m => m.CustomerList),
        resolve: {
          customerList: () => inject(CustomerControllerService).getCustomerList()
        }
      },
      {
        path: 'movies',
        title: 'Movies',
        loadComponent: () => import('./components/movie-list/movie-list').then(m => m.MovieList),
        resolve: {
          movieList: () => inject(MovieControllerService).getMovieList()
        }
      },
      {path: 'movies', component: MovieList},
      {path: 'not-found', component: PageNotFoundErrorPageComponent},
      {path: '**', redirectTo: 'not-found', pathMatch: 'full'},
    ],
  },
];

