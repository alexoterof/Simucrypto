package com.alexoterof.simucrypto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
  private String text;
  
  private Long codCommunity;
  
  private Long codUser;
  
}