package com.DuelLinks.CardGameplay;

import javax.swing.*;

public class TrapCard extends Card{

    public TrapCard(JsonTrapCard jsonTrapCard){

        super(new ImageIcon(jsonTrapCard.getSmallImage()));
        this.setDisabledIcon(new ImageIcon(jsonTrapCard.getSmallImage()));
        this.setManaRequirement(jsonTrapCard.getManaRequirement());
        this.setSmallImage(new ImageIcon(jsonTrapCard.getSmallImage()));
        this.setLargeImage(new ImageIcon(jsonTrapCard.getLargeImage()));
        this.setLargeImageString(jsonTrapCard.getLargeImage());
        this.setCardNameString(jsonTrapCard.getName());
        this.setId(Integer.parseInt(jsonTrapCard.getId()));

    }

    public TrapCard(ImageIcon smallImage,ImageIcon largeImage, String largeImageString, String cardName, int manaRequirement, int id) {
        super(smallImage);
        this.setDisabledIcon(smallImage);
        this.setManaRequirement(manaRequirement);
        this.setSmallImage(smallImage);
        this.setLargeImage(largeImage);
        this.setLargeImageString(largeImageString);
        this.setCardNameString(cardName);
        this.setId(id);
    }

    public TrapCard createCopyCard(){
        return new TrapCard(this.getSmallImage(),this.getLargeImage(),this.getLargeImageString(),this.getCardName(),this.getManaRequirement(),this.getId());
    }
}
