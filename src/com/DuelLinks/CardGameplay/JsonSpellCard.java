package com.DuelLinks.CardGameplay;

public class JsonSpellCard {

    private int ManaRequirement;

    private String smallImage;

    private String largeImage;

    private String name;

    private String id;

    public JsonSpellCard(String smallImg, String largeImg, int manaReq, String name, String id){

        this.largeImage = largeImg;

        this.ManaRequirement = manaReq;

        this.smallImage = smallImg;

        this.name = name;

        this.id = id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
