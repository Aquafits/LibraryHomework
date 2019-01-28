package com.aquafits.library.utils.message;

import io.goeasy.GoEasy;

public class MessageSender {
    private static GoEasy goEasy = new GoEasy("http://localhost:8080","BC-38746b95fd874b53a567a4c1c9c8aa14");

    public void send(Message message){
        goEasy.publish(message.getChannel(), message.getContent());
    }

}
