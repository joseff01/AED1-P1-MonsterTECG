package com.DuelLinks.CardGameplay;

import javax.swing.*;

public class SpellCard extends Card{

    public SpellCard(JsonSpellCard jsonSpellCard){

        super(new ImageIcon(jsonSpellCard.getSmallImage()));
        this.setDisabledIcon(new ImageIcon(jsonSpellCard.getSmallImage()));
        this.setManaRequirement(jsonSpellCard.getManaRequirement());
        this.setSmallImage(new ImageIcon(jsonSpellCard.getSmallImage()));
        this.setLargeImage(new ImageIcon(jsonSpellCard.getLargeImage()));
        this.setLargeImageString(jsonSpellCard.getLargeImage());
        this.setCardNameString(jsonSpellCard.getName());


    }

}