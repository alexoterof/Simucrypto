import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Community } from 'src/app/model/community';
import { CommunityService } from 'src/app/services/community/community.service';

@Component({
  selector: 'app-my-communities',
  templateUrl: './my-communities.component.html',
  styleUrls: ['./my-communities.component.scss']
})
export class MyCommunitiesComponent implements OnInit {

  communities: Community[];

  constructor(private communityService: CommunityService,
              private router: Router) { }

  ngOnInit(): void {
    this.communityService.findMine().subscribe(
      (response) => {
        this.communities = response;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  joinRoom(community: Community){
    this.router.navigate([`communities/room/${community.id}`])
  }
}
