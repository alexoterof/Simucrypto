import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { UserMinDto } from 'src/app/model/user/userRegisterDto';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
  providers: [MessageService]
})
export class LoginPageComponent implements OnInit {

  user: UserMinDto;
  constructor(private userService: UserService,
              private router: Router,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.user = new UserMinDto();
  }


  login(){
    console.log(this.user);
    this.userService.login(this.user).subscribe(
      (response) => {
        console.log("response");
        console.log(response);
        localStorage.setItem('jwt', response.jwt);
        this.router.navigate(['landing'])
      },
      (error) => {
        console.log(error);
        this.messageService.add({severity: "error", summary: 'No se pudo iniciar sesion', detail: 'Comprueba tu usuario o contraseña'})
      }
    )
  }
}
