import {Component} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  imports: [ReactiveFormsModule, RouterModule],
  styleUrl: './app.scss'
})
export class App {
  // treeStore = inject(TreeStore);
  // treeNodeService = inject(TreeNodeControllerService);
  //
  // dialog = inject(MatDialog);
  // destroyRef = inject(DestroyRef);
  //
  // selectedNode = this.treeStore.selectedNode;
  // rootNode = this.treeStore.rootNode;
  //
  // @ViewChild('searchInput', { static: true }) input!: ElementRef<HTMLInputElement>;
  //
  // ngOnInit() {
  //   this.treeNodeService.getAllNodes().subscribe(nodes => {
  //     this.treeStore.setNodes(nodes);
  //   });
  // }
  //
  // ngAfterViewInit() {
  //   fromEvent(this.input.nativeElement, 'input')
  //     .pipe(
  //       map((event: Event) => (event.target as HTMLInputElement).value),
  //       debounceTime(1000),          // wait 1s after user stops typing
  //       distinctUntilChanged(),
  //       filter(value => !!value),
  //       takeUntilDestroyed(this.destroyRef)
  //     )
  //     .subscribe(value => {
  //       this.search(value);
  //     });
  // }
  //
  // addTreeNode() {
  //   this.dialog.open(EditCustomerDialog, {
  //     width: '832px'
  //   }).afterClosed().pipe(
  //     filter(node => !!node),
  //     switchMap(node => this.treeNodeService.create({
  //       ...node,
  //       parentId: this.selectedNode()!.id!
  //     }))
  //   ).subscribe(node => this.treeStore.addNode(node));
  // }
  //
  // editTreeNode() {
  //   this.dialog.open(EditCustomerDialog, {
  //     width: '832px',
  //     data: this.selectedNode()
  //   }).afterClosed().pipe(
  //     filter(node => !!node),
  //     switchMap(node => this.treeNodeService.create(node))
  //   ).subscribe(node => this.treeStore.updateNode(node));
  // }
  //
  // @Confirm({ question: "Are you sure you want to delete this node?"})
  // deleteTreeNode() {
  //   this.treeNodeService.deleteNode(this.selectedNode()!.id!).subscribe(() => this.treeStore.deleteNode(this.selectedNode()!));
  // }
  //
  // search(query: string) {
  //   this.treeNodeService.search(query).subscribe(nodes => this.treeStore.filterNodes(nodes));
  // }
  //
  // deleteSearchInput() {
  //   this.treeStore.setFilteredNodes([]);
  //   this.treeStore.setHalfhiglighted([]);
  //   this.input.nativeElement.value = '';
  // }
}
