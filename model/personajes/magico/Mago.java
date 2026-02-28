    package model.personajes.magico;

import model.interfaces.Magico;
import model.personajes.Personaje;

public class Mago extends PersonajeMagico implements Magico {

    private int poderElemental;
    private String elementoElegido;

    public Mago(String nombre, int nivel) {
        super(nombre, nivel, 60 + nivel * 4, 100 + nivel * 10, 18 + nivel * 3);
        this.poderElemental = 10 + nivel * 2;
        this.elementoElegido = "Fuego";
    }

    public Mago(String nombre, int nivel, String elemento) {
        this(nombre, nivel);
        this.elementoElegido = elemento;
    }

    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    @Override
    public void lanzarHechizo(Personaje objetivo) {
        int coste = 20;
        if (gastarMana(coste)) {
            int damage = calcularPoderMagico() + poderElemental;
            objetivo.recibirDamage(damage);
            System.out.printf("%s lanza %s sobre %s causando %d daño mágico.%n",
                    getNombre(), getNombreHechizo(), objetivo.getNombre(), damage);
        }
    }

    @Override
    public String getNombreHechizo() {
        return "Bola de " + elementoElegido;
    }

    public void invocar() {
        System.out.println(getNombre() + " invoca una criatura de " + elementoElegido + "!");
    }

    public String getElemento() { return elementoElegido; }
    public void setElemento(String elemento) { this.elementoElegido = elemento; }
    public int getPoderElemental() { return poderElemental; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Elemento: %s | PoderElem: %d", elementoElegido, poderElemental);
    }
}
