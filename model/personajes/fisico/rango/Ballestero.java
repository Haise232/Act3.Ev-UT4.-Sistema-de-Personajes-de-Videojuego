package model.personajes.fisico.rango;

import model.personajes.Personaje;

public class Ballestero extends PersonajeFisicoRango {

    public Ballestero(String nombre, int nivel) {
        super(nombre, nivel, 85 + nivel * 5, 25 + nivel * 3, "Ballesta", 12 + nivel, 20 + nivel, 15 + nivel, 0.75);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(getNombre() + " dispara su ballesta con fuerza bruta...");
        atacarDistancia(getAlcanceMaximo() / 2, objetivo);
    }

    @Override
    public String toString() {
        return super.toString() + " [Ballestero]";
    }
}
