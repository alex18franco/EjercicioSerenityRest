package co.com.reto2021.wolox.model;

import java.util.List;

public class UsuarioRegistrado {
    private int total_pages;
    private int current_page;
    private List<Usuario> page;

    public int getTotal_pages() {
        return total_pages;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public List<Usuario> getPage() {
        return page;
    }
}
