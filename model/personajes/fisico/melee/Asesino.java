package model.personajes.fisico.melee;

import model.interfaces.Sigiloso;
import model.personajes.Personaje;

public class Asesino extends PersonajeFisicoMelee implements Sigiloso {
    private boolean escondido;
    private double probabilidadAtaqueDoble;

    public Asesino(String nombre, int nivel) {
        super(nombre, nivel, 70 + nivel * 4, 20 + nivel * 3, "Daga", 10 + nivel, 15 + nivel * 2, 60);
        this.escondido = false;
        this.probabilidadAtaqueDoble = 0.5;
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(getNombre() + " ¡Ataque desde las sombras!");
        super.atacar(objetivo);
        ataqueDoble(objetivo);
    }

    public void ataqueDoble(Personaje objetivo) {
        if (Math.random() < probabilidadAtaqueDoble) {
            System.out.println(getNombre() + " ¡Ataque doble!");
            objetivo.recibirDanio(golpeCritico());
        }
    }

    @Override
    public void esconderse() {
        escondido = true;
        System.out.println(getNombre() + " se funde con las sombras.");
    }

    @Override
    public void revelarse() {
        escondido = false;
        System.out.println(getNombre() + " emerge de las sombras.");
    }

    @Override
    public boolean estaEscondido() { return escondido; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Escondido: %s [Asesino]", escondido ? "Sí" : "No");
    }
}
