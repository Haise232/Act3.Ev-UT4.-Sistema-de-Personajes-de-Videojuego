package model.personajes.fisico.rango;

import model.personajes.Personaje;

public class Arquero extends PersonajeFisicoRango {

    public Arquero(String nombre, int nivel) {
        super(nombre, nivel, 80 + nivel * 5, 18 + nivel * 3, "Arco", 8 + nivel, 30 + nivel * 2, 20 + nivel * 2, 0.85);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(getNombre() + " apunta con precisión...");
        atacarDistancia(getAlcanceMaximo() / 2, objetivo);
    }

    @Override
    public String toString() {
        return super.toString() + " [Arquero]";
    }
}
