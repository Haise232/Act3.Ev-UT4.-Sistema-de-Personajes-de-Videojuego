package model.personajes.fisico;

import model.personajes.Personaje;

public abstract class PersonajeFisico extends Personaje {
    private int fuerza;
    private int defensa;

    public PersonajeFisico(String nombre, int nivel, int salud, int fuerza, int defensa) {
        super(nombre, nivel, salud);
        this.fuerza = Math.max(1, fuerza);
        this.defensa = Math.max(1, defensa);
    }

    public int getFuerza() { return fuerza; }
    public int getDefensa() { return defensa; }

    protected int calcularAtaque() {
        return fuerza + getNivel() * 2;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (objetivo == null) return;
        int dano = calcularAtaque();
        objetivo.recibirDanio(dano);
        System.out.printf("%s ataca con fuerza física y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Fuerza: %d | Defensa: %d", fuerza, defensa);
    }
}
