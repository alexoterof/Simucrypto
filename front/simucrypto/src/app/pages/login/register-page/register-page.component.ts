import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserMinDto } from 'src/app/model/user/userRegisterDto';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {
  user: UserMinDto;


  constructor(private userService: UserService,
              private router: Router){}

  ngOnInit(): void {
    this.user = new UserMinDto();
  }

  register(){
    this.userService.register(this.user).subscribe(
      (response) => {
        this.router.navigate(['login'])
      },
      (error) => {
        console.log(error);
      }
    )
  }

}
