import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Community } from 'src/app/model/community';
import { CommunityService } from 'src/app/services/community/community.service';

@Component({
  selector: 'app-all-communities',
  templateUrl: './all-communities.component.html',
  styleUrls: ['./all-communities.component.scss']
})
export class AllCommunitiesComponent implements OnInit {
  communities: Community[];

  constructor(private communityService: CommunityService,
              private router: Router) { }

  ngOnInit(): void {
    this.communityService.findAll().subscribe(
      (response) => {
        this.communities = response;
      },
      (error) => {

      }
    )
  }

  joinCommunity(joinCommunity: Community){
    this.communityService.join(joinCommunity.id).subscribe(
      (response) => {
        this.router.navigateByUrl("communities/my");
      },
      (error) => {

      }
    )
  }
}
