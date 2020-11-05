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

    }
}
