package com.DuelLinks.ComunicationMessages;

public class CurseOfAnubisMessage extends Message{



    String imageAnubis;
    String anubisCard;


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
