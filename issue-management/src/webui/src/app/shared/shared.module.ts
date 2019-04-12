import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';
import { ModalModule } from 'ngx-bootstrap';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { CommonModule } from '@angular/common';

@NgModule({
  imports:[
    ModalModule.forRoot(),
    FormsModule,
    CommonModule
  ],
  declarations: [
    ConfirmationComponent
  ],
  entryComponents:[
    ConfirmationComponent
  ],
  exports: [
    TranslateModule,
    ModalModule,
    ReactiveFormsModule,
    ConfirmationComponent
  ]
})
export class SharedModule { }
