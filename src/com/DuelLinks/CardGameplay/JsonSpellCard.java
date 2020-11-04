package com.DuelLinks.CardGameplay;

public class JsonSpellCard {

    private int ManaRequirement;

    private String smallImage;

    private String largeImage;

    private String name;

    public JsonSpellCard(String smallImg, String largeImg, int manaReq, String name){

        this.largeImage = largeImg;

        this.ManaRequirement = manaReq;

        this.smallImage = smallImg;

        this.name = name;

    }

    public JsonSpellCard(){
        super();
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public int getManaRequirement() {
        return ManaRequirement;
    }

    public void setManaRequirement(int manaRequirement) {
        ManaRequirement = manaRequirement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
