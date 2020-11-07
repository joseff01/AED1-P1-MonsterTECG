package com.DuelLinks.CardGameplay;
/**
 * @author Jose Antonio Retana
 * Obtiene informacion del archivo Cards.Jason y almacena la informacion;
 */
public class JsonSpellCard {

    private int ManaRequirement;

    private String smallImage;

    private String largeImage;

    private String name;

    private String id;

    /**
     * @author Jose Antonio Retana
     * @param smallImg
     * @param largeImg
     * @param manaReq
     * @param name
     * @param id
     */
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

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public String getSmallImage() {
        return smallImage;
    }

    /**
     * @author Jose Antonio Retana
     * @param smallImage
     */
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public String getLargeImage() {
        return largeImage;
    }

    /**
     * @author Jose Antonio Retana
     * @param largeImage
     */
    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public int getManaRequirement() {
        return ManaRequirement;
    }

    /**
     * @author Jose Antonio Retana
     * @param manaRequirement
     */
    public void setManaRequirement(int manaRequirement) {
        ManaRequirement = manaRequirement;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @author Jose Antonio Retana
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @author Jose Antonio Retana
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @author Jose Antonio Retana
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
