package model.personajes.fisico.melee;

import model.interfaces.Defendible;
import model.personajes.Personaje;

public class Guerrero extends PersonajeFisicoMelee implements Defendible {

    public Guerrero(String nombre, int nivel) {
        super(nombre, nivel, 120 + nivel * 8, 22 + nivel * 3, "Espada", 20 + nivel * 2, 8 + nivel, 90);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(getNombre() + " ¡Por el honor de mi clan!");
        super.atacar(objetivo);
    }

    @Override
    public int defender(int danio) {
        int reduccion = (int) (danio * 0.3);
        System.out.println(getNombre() + " bloquea " + reduccion + " de daño con su escudo.");
        return danio - reduccion;
    }

    @Override
    public String toString() {
        return super.toString() + " [Guerrero]";
    }
}
