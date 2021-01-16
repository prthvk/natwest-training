import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HeaderservService } from '../services/headerserv.service';

@Component({
  selector: 'app-dialogbox',
  templateUrl: './dialogbox.component.html',
  styleUrls: ['./dialogbox.component.css']
})
export class DialogboxComponent implements OnInit {

  public dlimit: any;
  public debitLimit = new FormControl();
  public dbl: FormGroup;

  constructor(public dialogRef: MatDialogRef<DialogboxComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private fb: FormBuilder, private headserv: HeaderservService) {
  }

  ngOnInit():void {
    this.dbl = this.fb.group({
      debitLimit: [this.data.debitLimit, [Validators.pattern('[0-9]+'), Validators.minLength(1), Validators.maxLength(12)]],
    })
  }

  save(): void {
    //add the functionality to call the function to set the debit amount limit

    if (this.dbl.get('debitLimit').valid) {
      this.dialogRef.close(this.debitLimit)
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  get DebitLimit() {
    return this.dbl.get('debitLimit');
  }
}
