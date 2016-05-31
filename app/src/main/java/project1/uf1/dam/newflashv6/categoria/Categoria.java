package project1.uf1.dam.newflashv6.categoria;

import android.widget.Toast;

import project1.uf1.dam.newflashv6.R;

/**
 * Clase que representa la existencia de un Coche
 */
public class Categoria {
    private String nombre;
    private int idDrawable;

    public Categoria(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static Categoria[] ITEMS = {
            new Categoria("Deportes", R.drawable.deportes),
            new Categoria("Política", R.drawable.politica),
            new Categoria("Economía", R.drawable.economia),
            new Categoria("Música", R.drawable.musica),
            new Categoria("Cine", R.drawable.cine),
            new Categoria("Informática", R.drawable.informatica),
            new Categoria("Anime",R.drawable.anime),
            new Categoria("NewFlash",R.drawable.newflash)
    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static Categoria getItem(int id) {
        for (Categoria item : ITEMS) {
            if (item.getId() == id) {

                return item;
            }
        }
        return null;
    }
}
