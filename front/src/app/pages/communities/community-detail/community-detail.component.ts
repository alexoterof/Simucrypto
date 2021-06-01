import { Component, NgZone, OnInit } from '@angular/core';
import { ChatService } from 'src/app/services/community/chat.service';


@Component({
  selector: 'app-community-detail',
  templateUrl: './community-detail.component.html',
  styleUrls: ['./community-detail.component.scss']
})
export class CommunityDetailComponent implements OnInit {

  title = 'client';
  message = '';
  messages: any[];
  socket: WebSocket;

  input;

  constructor(public chatService: ChatService) {
  }
  
  ngOnInit(){}

  sendMessage() {
    if (this.input) {
      this.chatService.sendMessage(this.input);
      this.input = '';
    }
  }

}
