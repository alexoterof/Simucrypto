import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'new-community',
  templateUrl: './new-community.component.html',
  styleUrls: ['./new-community.component.scss']
})
export class NewCommunityComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  create(){
    console.log("create");
  }

}
