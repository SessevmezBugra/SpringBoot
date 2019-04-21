import { Component, OnInit, TemplateRef } from '@angular/core';
import { IssueService } from 'src/app/services/shared/issue.service';
import { Page } from 'src/app/common/page';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ProjectService } from 'src/app/services/shared/project.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.scss']
})
export class IssueComponent implements OnInit {
  modalRef: BsModalRef;
  rows = [];
  page = new Page();
  projectOptions = [];

  issueForm: FormGroup;

  constructor(private issueService: IssueService,
              private projectService: ProjectService,
              private modalService: BsModalService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.issueForm = this.formBuilder.group({
      project: this.formBuilder.group({
        id: [null, [Validators.required]],
      }),
      description: [null, [Validators.required]]
    });
    this.loadProject();
    this.setPage({offset:0});
  }

  private loadProject(){
    this.projectService.getAllProjects().subscribe(response=> {
      this.projectOptions=response;
    });
  };

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

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  saveIssue(){
    this.issueService.createIssue(this.issueForm.value).subscribe(response =>{
      this.setPage({offset:0});
      this.closeAndResetModal();
    });
  }

  get f(){
    return this.issueForm.controls;
  }

  closeAndResetModal() {
    this.issueForm.reset();
    this.modalRef.hide();
  }
}
