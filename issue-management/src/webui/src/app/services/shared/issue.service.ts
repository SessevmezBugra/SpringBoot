import { Injectable } from '@angular/core';
import { ApiService } from '../api.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


@Injectable()
export class IssueService{

    private ISSUE_PATH = "/issue"
    constructor(private apiService: ApiService){

    }

    getAll() : Observable<any>{
        return this.apiService.get(this.ISSUE_PATH).pipe(map(
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

    createProject(project) : Observable<any>{
        return this.apiService.post(this.ISSUE_PATH,project).pipe(map(
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
}