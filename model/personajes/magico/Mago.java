    package model.personajes.magico;

import model.interfaces.Magico;
import model.personajes.Personaje;

public class Mago extends PersonajeMagico implements Magico {

    private int poderElemental;
    private String elementoElegido;

    // Constructor principal: inicializa el mago con nombre, nivel, vida, maná y poder mágico base
    public Mago(String nombre, int nivel) {
        super(nombre, nivel, 60 + nivel * 4, 100 + nivel * 10, 18 + nivel * 3);
        this.poderElemental = 10 + nivel * 2;
        this.elementoElegido = "Fuego"; //Se puede cambiar pero es el elemento por defecto
    }

    // Constructor alternativo: permite elegir el elemento
    public Mago(String nombre, int nivel, String elemento) {
        this(nombre, nivel);
        this.elementoElegido = elemento;
    }

    // Implementación del ataque: el mago ataca lanzando un hechizo
    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    // Lógica para lanzar un hechizo, consume maná y causa daño mágico
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

    // Devuelve el nombre del hechizo según el elemento elegido
    @Override
    public String getNombreHechizo() {
        return "Bola de " + elementoElegido;
    }

    // Getters y setters para el elemento y poder elemental
    public String getElemento() { return elementoElegido; }
    public void setElemento(String elemento) { this.elementoElegido = elemento; }
    public int getPoderElemental() { return poderElemental; }

    // Representación en texto del mago, incluyendo su elemento y poder elemental
    @Override
    public String toString() {
        return super.toString() + String.format(" | Elemento: %s | PoderElem: %d", elementoElegido, poderElemental);
    }
}
