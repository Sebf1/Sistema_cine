package cl.duoc.poo.modelo;

/**
 *
 * @author sebastian ganz
 */
public class Movie {
    
    // Atributos
    private int id;
    private String titulo;
    private String director;
    private int ano;
    private int duracion;
    private String genero;

// Constructor Sin Parametros
    
    public Movie() {
    }

// Constructor con parametros

    public Movie(int id, String titulo, String director, int ano, int duracion, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.ano = ano;
        this.duracion = duracion;
        this.genero = genero;
    }
    
// accesadores y mutadores

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    // metodo impresion toString

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", titulo=" + titulo + ", director=" + director + ", ano=" + ano + ", duracion=" + duracion + ", genero=" + genero + '}';
    }
    
}
    

    
    