package com.DuelLinks.MainMenu;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JoinMenu{

    public JoinMenu(){

        setListenSocket();

    }

    private void setListenSocket(){
        try{
            ListenSocket = new ServerSocket(ListenSocketNum);
        } catch (IOException e) {
            ListenSocketNum++;
            setListenSocket();
        }
    }

    private ServerSocket ListenSocket;

    public ServerSocket getListenSocket() {
        return ListenSocket;
    }

    private int ListenSocketNum = 10000;

    public int getListenSocketNum() {
        return ListenSocketNum;
    }




}
