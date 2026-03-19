package model.personajes.magico;

import model.interfaces.Especializable;
import model.interfaces.Magico;
import model.personajes.Personaje;

public class Clerigo extends PersonajeMagico implements Magico, Especializable {
    public Clerigo(String nombre, int nivel) {
        super(nombre, nivel, 95 + nivel * 6, 100 + nivel * 10, 14 + nivel * 2);
    }

    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    @Override
    public void lanzarHechizo(Personaje objetivo) {
        if (objetivo == null) return;
        if (!gastarMana(15)) return;
        int damage = calcularPoderMagico() + 5;
        objetivo.recibirDanio(damage);
        System.out.printf("%s lanza Luz Divina y causa %d daño a %s.\n", getNombre(), damage, objetivo.getNombre());
    }

    @Override
    public String getNombreHechizo() { return "Luz Divina"; }

    @Override
    public void ataqueEspecial(Personaje objetivo) {
        if (objetivo == null) return;
        if (!gastarMana(25)) return;
        int heal = 25 + getNivel() * 2;
        objetivo.curar(heal);
        System.out.printf("%s channelea Sanación Mayor y cura %d puntos a %s.\n", getNombre(), heal, objetivo.getNombre());
    }

    @Override
    public String getNombreEspecial() { return "Sanación Mayor"; }

    public void sanacion(Personaje objetivo) {
        if (objetivo == null) return;
        if (!gastarMana(25)) return;
        objetivo.curar(25 + getNivel() * 2);
    }

    @Override
    public String getRol() { return "Clerigo"; }
}
