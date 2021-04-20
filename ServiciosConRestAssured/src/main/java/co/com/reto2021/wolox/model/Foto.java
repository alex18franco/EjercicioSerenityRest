package co.com.reto2021.wolox.model;

public class Foto {
    private int album_id;
    private int id;
    private String title;
    private String url;
    private String thumbnail_url;

    public int getAlbum_id() {
        return album_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    @Override
    public String toString() {
        return "Foto{" +
                "album_id=" + album_id +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnail_url='" + thumbnail_url + '\'' +
                '}';
    }
}
