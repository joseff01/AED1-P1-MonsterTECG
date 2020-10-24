package com.DuelLinks.MainMenu;

import java.io.IOException;
import java.net.ServerSocket;

public class JoinMenu {

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
