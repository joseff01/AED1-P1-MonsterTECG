package com.DuelLinks.CardGameplay;

import javax.swing.*;

public abstract class Card extends JButton {

    private int manaRequirement;

    private ImageIcon smallImage;
    private ImageIcon largeImage;
    private String largeImageString;

    private String cardName;



    public Card(ImageIcon icon) {

        super(icon);

    }


    public int getManaRequirement() {
        return manaRequirement;
    }

    public void setManaRequirement(int manaRequirement) {
        this.manaRequirement = manaRequirement;
    }

    public ImageIcon getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(ImageIcon smallImage) {
        this.smallImage = smallImage;
    }

    public ImageIcon getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(ImageIcon largeImage) {
        this.largeImage = largeImage;
    }

    public String getLargeImageString() {
        return largeImageString;
    }

    public void setLargeImageString(String largeImageString) {
        this.largeImageString = largeImageString;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardNameString(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }else if (!(o instanceof Card)) {
            return false;
        }
        Card c = (Card) o;
        return (this.smallImage == ((Card) o).smallImage);
    }






}
