package co.com.reto2021.wolox.model;

public class AlbumComprado {
    private Album album;
    private int user_id;

    public Album getAlbum() {
        return album;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "AlbumComprado{" +
                "album=" + album.toString() +
                ", user_id=" + user_id +
                '}';
    }
}
