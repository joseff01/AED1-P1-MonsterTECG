package com.DuelLinks.CardGameplay;

import javax.swing.*;

public abstract class Card extends JButton {

    private int manaRequirement;

    public int getAttackDone() {
        return attackDone;
    }

    public void setAttackDone(int attackDone) {
        this.attackDone = attackDone;
    }

    private int attackDone;

    private ImageIcon smallImage;
    private ImageIcon largeImage;



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
