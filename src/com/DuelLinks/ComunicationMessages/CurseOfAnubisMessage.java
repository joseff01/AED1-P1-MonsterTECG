package com.DuelLinks.ComunicationMessages;

/**
 * Mensaje de la carta trampa Curse of Anubis,el cual se convierte en un json para se transmitido
 * @author Mariana Navarro
 */
public class CurseOfAnubisMessage extends Message{



    String imageAnubis;
    String anubisCard;

    /**
     *
     * @param anubisName
     * @param imageAnubis
     */
    public CurseOfAnubisMessage(String anubisName,String imageAnubis) {

        this.imageAnubis = imageAnubis;
        this.anubisCard = anubisName;

    }

    public CurseOfAnubisMessage(){}

    public String getImageAnubis() {
        return imageAnubis;
    }

    public void setImageAnubis(String imageAnubis) {
        this.imageAnubis = imageAnubis;
    }

    public String getAnubisCard() {
        return anubisCard;
    }

    public void setAnubisCard(String anubisCard) {
        this.anubisCard = anubisCard;
    }


}
