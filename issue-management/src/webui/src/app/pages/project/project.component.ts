import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ProjectService } from 'src/app/services/shared/project.service';
import { Page } from 'src/app/common/page';
import { Project } from 'src/app/common/project.model';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ConfirmationComponent } from 'src/app/shared/confirmation/confirmation.component';
import { UserService } from 'src/app/services/shared/user.service';


@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {

  page = new Page();
  modalRef: BsModalRef;
  projectForm: FormGroup;
  @ViewChild ('tplProjectDeleteCell') tplProjectDeleteCell: TemplateRef<any>;
  cols = [];
  rows = [];
  managerOptions =[];

  constructor(
    private projectService: ProjectService, 
    private modalService: BsModalService, 
    private formBuilder: FormBuilder,
    private userService: UserService
    ) { }

  ngOnInit() {
    this.cols = [
      { prop: 'id', name: 'No' },
      { prop: 'projectName', name: 'Project Name', sortable: false },
      { prop: 'projectCode', name: 'Project Code', sortable: false },
      { prop: 'manager.nameSurname', name: 'Project Manager', sortable: false },
      { prop: 'id', name: 'Actions', cellTemplate: this.tplProjectDeleteCell, sortable: false }
    ];
    this.setPage({ offset: 0 });
    this.projectForm = this.formBuilder.group({
      'projectName' : [null,[Validators.required, Validators.minLength(4)]],
      'projectCode' : [null,[Validators.required, Validators.minLength(2)]],
      'manager'     : this.formBuilder.group({
        'id' : [null,[Validators.required]]
      })
    });

    this.userService.getAll().subscribe(res => {
      this.managerOptions = res;
      console.log(res);
    });
  }

  get f(){ 
    debugger;
    return this.projectForm.controls}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  closeAndResetModal() {
    this.projectForm.reset();
    this.modalRef.hide();
  }

  saveProject() {
    debugger;
    if (!this.projectForm.valid)
      return;
    this.projectService.createProject(this.projectForm.value).subscribe(
      response => {
        this.setPage({offset: 0});
        this.closeAndResetModal();
      }
    )
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

  showProjectDeleteConfirmation(value){
    const modal = this.modalService.show(ConfirmationComponent);
    (<ConfirmationComponent>modal.content).showConfirmation(
      'Delete Confirmation',
      'Are you sure for delete Project'
    );
    (<ConfirmationComponent>modal.content).onClose.subscribe(result=>{
      if(result === true){
        this.projectService.delete(value).subscribe(response=>{
          if(response){
            this.setPage({offset:0});
          }
        });
        console.log("Yes");
      }else{
        console.log("No");
      }
    });
  }

}
