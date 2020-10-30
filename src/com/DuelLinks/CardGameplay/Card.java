package com.DuelLinks.CardGameplay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Card extends JButton {

    private int ManaRequirement;

    private ImageIcon smallImage;

    public Card(ImageIcon icon) {

        super(icon);

    }


    public int getManaRequirement() {
        return ManaRequirement;
    }

    public void setManaRequirement(int manaRequirement) {
        ManaRequirement = manaRequirement;
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

    private ImageIcon largeImage;





}
