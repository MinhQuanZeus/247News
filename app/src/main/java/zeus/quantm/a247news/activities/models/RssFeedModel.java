package zeus.quantm.a247news.activities.models;


public class RssFeedModel {

    public String title;
    public String link;
    public String description;
    public String pubDate;
    public String image;

    public RssFeedModel(String title, String link, String description, String pubDate, String image) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.image = image;
    }

    @Override
    public String toString() {
        return "RssFeedModel{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

