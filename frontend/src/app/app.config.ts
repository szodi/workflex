import {
  ApplicationConfig,
  inject,
  provideBrowserGlobalErrorListeners,
  provideEnvironmentInitializer
} from '@angular/core';
import {provideRouter, withComponentInputBinding} from '@angular/router';

import {APP_ROUTES} from './app.routes';
import {provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import {DialogService} from './services/dialog.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(APP_ROUTES, withComponentInputBinding()),
    provideHttpClient(withInterceptorsFromDi()),
    provideEnvironmentInitializer(() => inject(DialogService)),
  ]
};
