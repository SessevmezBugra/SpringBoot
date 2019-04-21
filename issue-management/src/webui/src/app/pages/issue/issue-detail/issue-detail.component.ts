import { Component, OnInit, ViewChild,TemplateRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IssueService } from 'src/app/services/shared/issue.service';
import { UserService } from 'src/app/services/shared/user.service';
import { ProjectService } from 'src/app/services/shared/project.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-issue-detail',
  templateUrl: './issue-detail.component.html',
  styleUrls: ['./issue-detail.component.scss']
})
export class IssueDetailComponent implements OnInit {

  @ViewChild('tplDateCell') tplDateCell: TemplateRef<any>;
  id;
  private sub: any;

  issueDetailForm: FormGroup;

  datatable_rows=[];
  columns=[];

  issueStatusOptions=[];
  assigneeOptions=[];
  projectOptions=[];

  constructor(
    private route: ActivatedRoute, 
    private issueService: IssueService,
    private userService: UserService,
    private projectService: ProjectService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.loadIssueDetails();
    }); 

    this.columns = [
      {prop:'id', name:'No', maxWidth: 30},
      {prop:'description', name:'Description'},
      {prop:'date', name:'Issue Date', cellTemplate: this.tplDateCell},
      {prop:'issueStatus', name:'Issue Status'},
      {prop:'assignee.nameSurname', name:'Assignee'},
      {prop:'issue.project.projectName', name:'Project Name'},
    ];

    this.loadProject();
    this.loadAssignee();
    this.loadIssueStatuses();
  }

  private loadIssueStatuses(){
    this.issueService.getAllIssueStatuses().subscribe(response=> {
      this.issueStatusOptions=response;
    });
  };
  
  private loadAssignee(){
    this.userService.getAll().subscribe(response=> {
      this.assigneeOptions=response;
    });
  };
  
  private loadProject(){
    this.projectService.getAllProjects().subscribe(response=> {
      this.projectOptions=response;
    });
  };

  private loadIssueDetails(){
    debugger;
    this.issueService.getByIdWithDetails(this.id).subscribe(response=> {
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }

  createIssueDetailFormGroup(response){
    return this.formBuilder.group({
      id: response['id'],
      description: response['description'],
      details: response['details'],
      date: this.fromJsonDate(response['date']),
      issueStatus: response['issueStatus'],
      assignee: this.formBuilder.group({
        id: response['assignee'] ? (response['assignee']['id'] ? response['assignee']['id'] : '') : ''
      }),
      project: this.formBuilder.group({
        id: response['project']['id'],
        manager: this.formBuilder.group({
          nameSurname: response['project']['manager'] ? (response['project']['manager']['nameSurname'] ? response['project']['manager']['nameSurname'] : '') : ''
        })
      })
    });
  }

  saveIssue(){
    debugger;
    this.issueService.updateIssue(this.issueDetailForm.value).subscribe(response=> {
      this.loadIssueDetails();
    });
  }

  fromJsonDate(jDate): string {
    const date: Date = new Date(jDate);
    return date.toISOString().substring(0,10); 
  }

}
