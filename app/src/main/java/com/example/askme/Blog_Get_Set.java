package com.example.askme;

public class Blog_Get_Set {

    private String title;
    private String desc;

    private String image;

    public Blog_Get_Set(){

    }

    Blog_Get_Set(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
