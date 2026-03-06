package model.personajes;

public abstract class Personaje {
    private String nombre;
    private int nivel;
    private int salud;
    private int saludMaxima;

    public Personaje(String nombre, int nivel, int salud) {
        this.nombre = nombre;
        this.nivel = Math.max(1, nivel);
        this.saludMaxima = Math.max(1, salud);
        this.salud = this.saludMaxima;
    }

    public abstract void atacar(Personaje objetivo);

    public void recibirDanio(int cantidad) {
        salud = Math.max(0, salud - Math.max(0, cantidad));
        if (salud == 0) {
            System.out.println(nombre + " ha caído en combate!");
        }
    }

    public void curarSalud(int cantidad) {
        salud = Math.min(saludMaxima, salud + Math.max(0, cantidad));
    }

    public boolean estaVivo() { return salud > 0; }

    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNivel(int nivel) { if (nivel >= 1) this.nivel = nivel; }
    public void setSalud(int salud) { if (salud >= 0) this.salud = salud; }

    public String getString() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("%s (Nivel %d) | Salud: %d/%d", nombre, nivel, salud, saludMaxima);
    }
}
