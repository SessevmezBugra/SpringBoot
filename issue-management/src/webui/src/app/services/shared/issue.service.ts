import { Injectable } from '@angular/core';
import { ApiService } from '../api.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable()
export class IssueService{  

    private ISSUE_PATH = "/issue"
    constructor(private apiService: ApiService){

    }

    getByIdWithDetails(id) {
        return this.apiService.get(this.ISSUE_PATH+'/detail/'+id).pipe(map(
            res =>{
                if(res){
                    return res;
                }else{
                    console.log(res);
                    return null;
                }
            }
        ));
    }

    getAllIssueStatuses() {
        return this.apiService.get(this.ISSUE_PATH+'/statuses').pipe(map(
            res =>{
                if(res){
                    return res;
                }else{
                    console.log(res);
                    return null;
                }
            }
        ));
    }

    getAll(page) : Observable<any>{
        return this.apiService.get(this.ISSUE_PATH+'/pagination',page).pipe(map(
            res =>{
                if(res){
                    return res;
                }else{
                    console.log(res);
                    return null;
                }
            }
        ));
    }

    getById(id) : Observable<any>{
        return this.apiService.get(this.ISSUE_PATH,id).pipe(map(
            res =>{
                if(res){
                    return res;
                }else{
                    console.log(res);
                    return null;
                }
            }
        ));
    }

    delete(id) : Observable<any>{
        return this.apiService.delete(this.ISSUE_PATH,id).pipe(map(
            res =>{
                if(res){
                    return res;
                }else{
                    console.log(res);
                    return null;
                }
            }
        ));
    }

    updateIssue(issue): Observable<any>{
        return this.apiService.put(this.ISSUE_PATH,issue).pipe(map(
            res =>{
                if(res){
                    return res;
                }else{
                    return null;
                }
            }
        ));
    }

    createIssue(issue): Observable<any>{
        return this.apiService.post(this.ISSUE_PATH,issue).pipe(map(
            res =>{
                if(res){
                    return res;
                }else{
                    return null;
                }
            }
        ));
    }
}