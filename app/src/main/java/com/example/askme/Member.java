package com.example.askme;

public class Member {

    String title, url;

    public Member() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Member{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
