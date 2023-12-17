package com.bezkoder.springjwt.payloadTest.responseTest;

public class MessageResponseTest {
  private String message;

  public MessageResponseTest(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
