package model.personajes.fisico.rango;

import model.interfaces.Especializable;
import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;

public class Arquero extends PersonajeFisico implements Especializable {
    public Arquero(String nombre, int nivel) {
        super(nombre, nivel, 100 + nivel * 7, 16 + nivel * 2, 10 + nivel);
    }

    @Override
    public void atacar(Personaje objetivo) {
        int dano = getFuerza() + getNivel() * 2;
        objetivo.recibirDanio(dano);
        System.out.printf("%s dispara una flecha y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public void ataqueEspecial(Personaje objetivo) {
        int dano = getFuerza() + getNivel() * 4;
        objetivo.recibirDanio(dano);
        System.out.printf("%s ejecuta Disparo Preciso y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public String getNombreEspecial() { return "Disparo Preciso"; }

    @Override
    public String getRol() { return "Arquero"; }
}
