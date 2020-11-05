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
        this.setId(Integer.parseInt(jsonSpellCard.getId()));


    }

    public SpellCard(ImageIcon smallImage,ImageIcon largeImage, String largeImageString, String cardName, int manaRequirement, int id) {
        super(smallImage);
        this.setDisabledIcon(smallImage);
        this.setManaRequirement(manaRequirement);
        this.setSmallImage(smallImage);
        this.setLargeImage(largeImage);
        this.setLargeImageString(largeImageString);
        this.setCardNameString(cardName);
        this.setId(id);
    }

    public SpellCard createCopyCard(){
        return new SpellCard(this.getSmallImage(),this.getLargeImage(),this.getLargeImageString(),this.getCardName(),this.getManaRequirement(),this.getId());
    }

}