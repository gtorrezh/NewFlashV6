package project1.uf1.dam.newflashv6.model;

import java.io.Serializable;

public class RSSFeed implements Serializable {


    //atributos del documento xml o rss
    private String title;
    private String link;
    private String description;


    public RSSFeed() {
    }

    public RSSFeed(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;

    }



    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }




    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
