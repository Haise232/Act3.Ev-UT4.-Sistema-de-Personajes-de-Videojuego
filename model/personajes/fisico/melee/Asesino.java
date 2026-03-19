package model.personajes.fisico.melee;

import model.interfaces.Especializable;
import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;

public class Asesino extends PersonajeFisico implements Especializable {
    public Asesino(String nombre, int nivel) {
        super(nombre, nivel, 90 + nivel * 6, 20 + nivel * 2, 10 + nivel);
    }

    @Override
    public void atacar(Personaje objetivo) {
        int dano = getFuerza() + getNivel() * 4;
        objetivo.recibirDanio(dano);
        System.out.printf("%s asesta un golpe furtivo y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public void ataqueEspecial(Personaje objetivo) {
        int dano = getFuerza() * 2 + getNivel() * 3;
        objetivo.recibirDanio(dano);
        System.out.printf("%s lanza Golpe Crítico y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public String getNombreEspecial() { return "Golpe Crítico"; }

    @Override
    public String getRol() { return "Asesino"; }
}
