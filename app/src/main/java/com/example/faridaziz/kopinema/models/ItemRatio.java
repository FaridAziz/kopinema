package com.example.faridaziz.kopinema.models;

public class ItemRatio {
    private int imgResource;
    private String ratio;
    private String detailRatio;

    public ItemRatio(int imageResource, String text1, String text2){
        imgResource = imageResource;
        ratio = text1;
        detailRatio = text2;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getDetailRatio() {
        return detailRatio;
    }

    public void setDetailRatio(String detailRatio) {
        this.detailRatio = detailRatio;
    }
}