package com.example.twothreads;

import javafx.collections.ObservableList;

import java.util.Date;

public class MyMessage extends Thread{
    int k;
    String name;
    ObservableList<String> messages;

    public MyMessage(int k, String name) {
        this.k = k;
        this.name = name;
    }

    public MyMessage(int k, String name, ObservableList<String> messages) {
        this.k = k;
        this.name = name;
        this.messages = messages;
    }

    public void work() throws InterruptedException {
        while(k >0)
        {
            messages.add(name+":   "+ "Это моё " + k +" сообщение." + "       " + new Date());
            k--;
            sleep(100 + (int) (Math.random() * 500));
        }
        messages.add(name+" завершил отправку сообщений.");
    }

    @Override
    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
