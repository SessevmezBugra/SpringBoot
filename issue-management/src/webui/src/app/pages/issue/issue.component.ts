import { Component, OnInit } from '@angular/core';
import { IssueService } from 'src/app/services/shared/issue.service';
import { Page } from 'src/app/common/page';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.scss']
})
export class IssueComponent implements OnInit {

  rows = [];
  page = new Page();
  constructor(private issueService: IssueService) { }

  ngOnInit() {
    this.setPage({offset:0});
  }

  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.issueService.getAll(this.page).subscribe(pagedData => {
      this.page.page = pagedData.page;
      this.page.size = pagedData.size;
      this.page.totalElements = pagedData.totalElements;
      this.page.totalPage = pagedData.totalPage;
      this.rows = pagedData.content;
    });
  }

}
