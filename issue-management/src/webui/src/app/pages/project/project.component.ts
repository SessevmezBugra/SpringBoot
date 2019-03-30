import { Component, OnInit, TemplateRef } from '@angular/core';
import { ProjectService } from 'src/app/services/shared/project.service';
import { Page } from 'src/app/common/page';
import { Project } from 'src/app/common/project.model';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';


@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

  page = new Page();
  modalRef: BsModalRef;
  cols = [
    { prop: 'id', name: 'No' },
    { prop: 'projectName', name: 'Project Name', sortable: false },
    { prop: 'projectCode', name: 'Project Code', sortable: false }
  ];
  rows = [];

  constructor(private projectService: ProjectService, private modalService: BsModalService) { }

  ngOnInit() {
    this.setPage({ offset: 0 });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.projectService.getAll(this.page).subscribe(pagedData => {
      this.page.page = pagedData.page;
      this.page.size = pagedData.size;
      this.page.totalElements = pagedData.totalElements;
      this.page.totalPage = pagedData.totalPage;
      this.rows = pagedData.content;
    });
  }

}
