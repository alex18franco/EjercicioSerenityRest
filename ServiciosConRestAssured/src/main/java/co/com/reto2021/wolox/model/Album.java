package co.com.reto2021.wolox.model;

public class Album {

    private int user_id;
    private int id;
    private String title;

    public int getUser_id() {
        return user_id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "user_id='" + user_id + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
