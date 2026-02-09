import {Component, input} from '@angular/core';
import {CustomerDto} from '@workflex-api-models/customerDto';

@Component({
  selector: 'app-customer-list',
  imports: [],
  templateUrl: './customer-list.html',
  styleUrl: './customer-list.scss',
})
export class CustomerList {

  customerList = input<CustomerDto[]>();
}
