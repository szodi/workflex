import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-page-not-found-error-page',
  templateUrl: './page-not-found-error-page.component.html',
  styleUrls: ['./page-not-found-error-page.component.scss']
})
export class PageNotFoundErrorPageComponent {
  constructor(private _router: Router) {}
}
