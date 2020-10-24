package com.DuelLinks.MainMenu;

public class StartGameFlag {

    private String ip;

    private int socket;

    public StartGameFlag(String ip, int socket) {
        this.ip = ip;
        this.socket = socket;
    }

    public StartGameFlag(){
        super();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getSocket() {
        return socket;
    }

    public void setSocket(int socket) {
        this.socket = socket;
    }

}
