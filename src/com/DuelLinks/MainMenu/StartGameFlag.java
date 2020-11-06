package com.DuelLinks.MainMenu;

/**
 * Clase usada para la comunicación entre usuarios.
 * Es la que se convierte en un json para ser transmitido como un string
 *
 * @Author Jose Retana
 */

public class StartGameFlag {

    private String ip;

    private int socket;

    /**
     *
     * @param ip
     * @param socket
     * @Author Jose Retana
     */

    public StartGameFlag(String ip, int socket) {
        this.ip = ip;
        this.socket = socket;
    }

    public StartGameFlag(){
        super();
    }

    /**
     *
     * @return ip
     * @Author Jose Retana
     */

    public String getIp() {
        return ip;
    }

    /**
     *
     * @param ip
     * @Author Jose Retana
     *
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *
     * @return socket
     * @Author Jose Retana
     *
     */
    public int getSocket() {
        return socket;
    }

    /**
     *
     * @param socket
     * @Author Jose Retana
     *
     */
    public void setSocket(int socket) {
        this.socket = socket;
    }

}
