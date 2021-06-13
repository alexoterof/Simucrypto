import { Injectable } from '@angular/core';
import { MessageDto } from 'src/app/model/message-dto';

@Injectable({
  providedIn: 'root'
})



export class ChatService {

  
  constructor() {
    this.initializeWebSocketConnection();
  }

  public stompClient;

  public msg: String[] = [];


  initializeWebSocketConnection() {
    const serverUrl = 'http://localhost:8080/socket';
    const ws = new SockJS(serverUrl);
    this.stompClient = Stomp.over(ws);
    const that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe('/message', (message) => {
        if (message.body) {
          that.msg.push(message.body);
        }
      });
    });
  }
  
  sendMessage(message) {
    this.stompClient.send('/app/send/message' , {}, message);
  }
}

// Declare SockJS and Stomp
declare var SockJS;
declare var Stomp;