package com.DuelLinks.CardGameplay;

import javax.swing.*;

/**
 * clase abstracta que delimita los parametros y
 * funciones comunes de lot ipos de las cartas, Extiene JButton
 */
public abstract class Card extends JButton {

    private int manaRequirement;

    private ImageIcon smallImage;
    private ImageIcon largeImage;
    private String largeImageString;

    private String cardName;

    private int id;

    /**
     * At. Jose Antonio Retana
     * @param icon
     */
    public Card(ImageIcon icon) {

        super(icon);

    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public int getManaRequirement() {
        return manaRequirement;
    }

    /**
     * At. Jose Antonio Retana
     * @param manaRequirement
     */
    public void setManaRequirement(int manaRequirement) {
        this.manaRequirement = manaRequirement;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public ImageIcon getSmallImage() {
        return smallImage;
    }

    /**
     * At. Jose Antonio Retana
     * @param smallImage
     */
    public void setSmallImage(ImageIcon smallImage) {
        this.smallImage = smallImage;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public ImageIcon getLargeImage() {
        return largeImage;
    }

    /**
     * At. Jose Antonio Retana
     * @param largeImage
     */
    public void setLargeImage(ImageIcon largeImage) {
        this.largeImage = largeImage;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public String getLargeImageString() {
        return largeImageString;
    }

    /**
     * At. Jose Antonio Retana
     * @param largeImageString
     */
    public void setLargeImageString(String largeImageString) {
        this.largeImageString = largeImageString;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * At. Jose Antonio Retana
     * @param cardName
     */
    public void setCardNameString(String cardName) {
        this.cardName = cardName;
    }

    /**
     * At. Jose Antonio Retana
     * @param cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * At. Jose Antonio Retana
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * At. Jose Antonio Retana
     * @param o
     * @return
     */
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
