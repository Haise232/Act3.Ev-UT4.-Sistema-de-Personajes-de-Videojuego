package model.personajes;

public abstract class Personaje {
    private String nombre;
    private int nivel;
    private int salud;
    private int saludMaxima;
    private boolean defendiendo;

    public Personaje(String nombre, int nivel, int salud) {
        this.nombre = nombre;
        this.nivel = Math.max(1, nivel);
        this.saludMaxima = Math.max(1, salud);
        this.salud = this.saludMaxima;
        this.defendiendo = false;
    }

    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }
    public boolean isDefendiendo() { return defendiendo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNivel(int nivel) { if (nivel >= 1) this.nivel = nivel; }
    public void setSalud(int salud) { this.salud = Math.max(0, Math.min(salud, saludMaxima)); }

    public boolean estaVivo() { return salud > 0; }

    public void recibirDanio(int cantidad) {
        int dano = Math.max(0, cantidad);
        if (defendiendo) {
            dano = Math.max(0, dano / 2);
        }
        salud = Math.max(0, salud - dano);
        this.defendiendo = false;
        System.out.printf("%s recibe %d de daño. Salud actual: %d/%d\n", nombre, dano, salud, saludMaxima);
    }

    public void curar(int cantidad) {
        int curar = Math.max(0, cantidad);
        salud = Math.min(saludMaxima, salud + curar);
        System.out.printf("%s se cura %d. Salud actual: %d/%d\n", nombre, curar, salud, saludMaxima);
    }

    public void defender() {
        defendiendo = true;
        System.out.printf("%s se prepara para defender (+50%% defensa)\n", nombre);
    }

    public abstract void atacar(Personaje objetivo);
    public abstract String getRol();

    public String getInfoResumen() {
        return String.format("%s (%s) Nivel %d | Salud: %d/%d", nombre, getRol(), nivel, salud, saludMaxima);
    }

    @Override
    public String toString() {
        return getInfoResumen();
    }
}
