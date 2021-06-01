import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Community } from 'src/app/model/community';
import { CommunityService } from 'src/app/services/community/community.service';

@Component({
  selector: 'new-community',
  templateUrl: './new-community.component.html',
  styleUrls: ['./new-community.component.scss'],
  providers: [MessageService]
})
export class NewCommunityComponent implements OnInit {
  community: Community;

  constructor(private communityService: CommunityService,
              private router: Router,
              private messageService: MessageService,) { }

  ngOnInit(): void {
    this.community = new Community();
  }

  create(){
    this.communityService.create(this.community).subscribe(
      (response) => {
        this.router.navigateByUrl('communities/my');
      },
      (error) => {
        console.log(error);
        this.messageService.add({severity: "error", summary: 'Invalid credentials', detail: 'User name and password do not match '})
      }
    )
  }
}


