import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-edit-customer-dialog',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './edit-customer-dialog.html',
  styleUrl: './edit-customer-dialog.scss',
})
export class EditCustomerDialog {

  // data = inject<CustomerDto>(MAT_DIALOG_DATA);
  dialogRef = inject(MatDialogRef<EditCustomerDialog>);

  // fb = inject(FormBuilder);
  //
  // form = this.fb.group({
  //   name: ['', Validators.required],
  //   content: ['', Validators.required]
  // });
  //
  // treeNode: TreeNode | undefined;
  //
  // constructor() {
  //   this.treeNode = this.data;
  //   if (this.treeNode) {
  //     this.form.patchValue({name: this.treeNode.name, content: this.treeNode.content});
  //   }
  // }
  //
  // submit() {
  //   this.treeNode = {
  //     ...this.treeNode,
  //     name: this.form.value.name!,
  //     content: this.form.value.content!
  //   }
  //   this.dialogRef.close(this.treeNode);
  // }
  //
  // close() {
  //   this.dialogRef.close(null);
  // }
}
