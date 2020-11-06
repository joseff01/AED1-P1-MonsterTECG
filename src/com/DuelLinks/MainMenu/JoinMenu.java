package com.DuelLinks.MainMenu;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase designada para la creación y almacenamiento de datos de los sockets
 *
 * @Author Jose Retana
 */


public class JoinMenu{

    /**
     * @Author Jose Retana
     */

    public JoinMenu(){

        setListenSocket();

    }

    /**
     * @Author Jose Retana
     */

    private void setListenSocket(){
        try{
            ListenSocket = new ServerSocket(ListenSocketNum);
        } catch (IOException e) {
            ListenSocketNum++;
            setListenSocket();
        }
    }

    private ServerSocket ListenSocket;

    /**
     * @return ListenSocket
     */

    public ServerSocket getListenSocket() {
        return ListenSocket;
    }

    private int ListenSocketNum = 10000;

    /**
     *
     * @return ListenSocketNum
     */
    public int getListenSocketNum() {
        return ListenSocketNum;
    }




}
