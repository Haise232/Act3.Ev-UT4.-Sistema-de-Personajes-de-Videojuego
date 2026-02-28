package model.personajes.magico;

import model.interfaces.Curable;
import model.interfaces.Magico;
import model.personajes.Personaje;

public class Clerigo extends PersonajeMagico implements Curable, Magico {

    private int fervorDivino;
    private int curacionesRealizadas;

    public Clerigo(String nombre, int nivel) {
        super(nombre, nivel, 90 + nivel * 6, 80 + nivel * 8, 14 + nivel * 2);
        this.fervorDivino = 8 + nivel;
        this.curacionesRealizadas = 0;
    }

    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    @Override
    public void lanzarHechizo(Personaje objetivo) {
        int coste = 15;
        if (gastarMana(coste)) {
            int damage = calcularPoderMagico() / 2 + fervorDivino;
            objetivo.recibirDamage(damage);
            System.out.printf("%s lanza %s sobre %s causando %d daño sagrado.%n",
                    getNombre(), getNombreHechizo(), objetivo.getNombre(), damage);
        }
    }

    @Override
    public String getNombreHechizo() {
        return "Castigo Divino";
    }

    @Override
    public void curar(Personaje objetivo) {
        int coste = 25;
        if (gastarMana(coste)) {
            int curacion = fervorDivino * 3 + calcularPoderMagico();
            objetivo.curarSalud(curacion);
            curacionesRealizadas++;
            System.out.printf("%s cura a %s restaurando %d puntos de salud. [Curaciones: %d]%n",
                    getNombre(), objetivo.getNombre(), curacion, curacionesRealizadas);
        }
    }

    public void bendicion(Personaje objetivo) {
        System.out.println(getNombre() + " bendice a " + objetivo.getNombre() + " con luz divina.");
        curar(objetivo);
    }

    public int getFervorDivino() { return fervorDivino; }
    public int getCuracionesRealizadas() { return curacionesRealizadas; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Fervor: %d | Curaciones: %d", fervorDivino, curacionesRealizadas);
    }
}
