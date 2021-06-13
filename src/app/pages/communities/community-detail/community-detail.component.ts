import { Component, NgZone, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageDto } from 'src/app/model/message-dto';
import { ChatService } from 'src/app/services/community/chat.service';


@Component({
  selector: 'app-community-detail',
  templateUrl: './community-detail.component.html',
  styleUrls: ['./community-detail.component.scss']
})
export class CommunityDetailComponent implements OnInit {

  title = 'client';
  message = 'Send message';
  messages: any[];
  socket: WebSocket;

  input;

  constructor(public chatService: ChatService,
              private router: Router,) {
  }
  
  ngOnInit(){
    console.log("URL -> " + this.router.url.split("/")[3]);
  }

  sendMessage() {
    if (this.input) {
      let message: MessageDto = {text: this.input, codCommunity: <number><any>this.router.url.split("/")[3], codUser: JSON.parse(atob(localStorage.getItem('jwt').split('.')[1])).userId}
      console.log("Sending");
      console.log(message);
      this.chatService.sendMessage(JSON.stringify(message));
      this.input = 'Send message';
    }
  }

}
