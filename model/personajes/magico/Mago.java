package model.personajes.magico;

import model.interfaces.Especializable;
import model.interfaces.Magico;
import model.personajes.Personaje;

public class Mago extends PersonajeMagico implements Magico, Especializable {
    private int poderElemental;
    private String elementoElegido;

    public Mago(String nombre, int nivel) {
        super(nombre, nivel, 80 + nivel * 6, 120 + nivel * 12, 18 + nivel * 2);
        this.poderElemental = 8 + nivel * 2;
        this.elementoElegido = "Fuego";
    }

    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    @Override
    public void lanzarHechizo(Personaje objetivo) {
        if (objetivo == null) return;
        int coste = 20;
        if (!gastarMana(coste)) return;
        int damage = calcularPoderMagico() + poderElemental;
        objetivo.recibirDanio(damage);
        System.out.printf("%s lanza Bola de %s y causa %d de daño a %s.\n", getNombre(), elementoElegido, damage, objetivo.getNombre());
    }

    @Override
    public String getNombreHechizo() {
        return "Bola de " + elementoElegido;
    }

    @Override
    public void ataqueEspecial(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    @Override
    public String getNombreEspecial() { return "Bola de " + elementoElegido; }

    @Override
    public String getRol() { return "Mago"; }
}
