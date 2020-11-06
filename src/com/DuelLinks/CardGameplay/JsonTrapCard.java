package com.DuelLinks.CardGameplay;
/**
 * At. Jose Antonio Retana
 * Obtiene informacion del archivo Cards.Jason y almacena la informacion;
 */
public class JsonTrapCard {

    private int ManaRequirement;

    private String smallImage;

    private String largeImage;

    private String name;

    private String id;

    /**
     * At. Jose Antonio Retana
     * @param smallImg
     * @param largeImg
     * @param manaReq
     * @param name
     * @param id
     */
    public JsonTrapCard(String smallImg, String largeImg, int manaReq, String name, String id){

        this.largeImage = largeImg;

        this.ManaRequirement = manaReq;

        this.smallImage = smallImg;

        this.name = name;

        this.id = id;

    }

    public JsonTrapCard(){
        super();
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public String getSmallImage() {
        return smallImage;
    }

    /**
     * At. Jose Antonio Retana
     * @param smallImage
     */
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public String getLargeImage() {
        return largeImage;
    }

    /**
     * At. Jose Antonio Retana
     * @param largeImage
     */
    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public int getManaRequirement() {
        return ManaRequirement;
    }

    /**
     * At. Jose Antonio Retana
     * @param manaRequirement
     */
    public void setManaRequirement(int manaRequirement) {
        ManaRequirement = manaRequirement;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * At. Jose Antonio Retana
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * At. Jose Antonio Retana
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * At. Jose Antonio Retana
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
