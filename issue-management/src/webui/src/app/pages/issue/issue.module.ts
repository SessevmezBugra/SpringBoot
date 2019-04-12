import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IssueComponent } from './issue.component';
import {IssueRoutingModule} from "./issue.routing.module";
import { IssueService } from 'src/app/services/shared/issue.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { IssueDetailComponent } from './issue-detail/issue-detail.component';

@NgModule({
  imports: [
    CommonModule,
    IssueRoutingModule,
    SharedModule,
    NgxDatatableModule
  ],
  providers:[IssueService],
  declarations: [IssueComponent, IssueDetailComponent]
})
export class IssueModule { }
