package model.personajes.fisico.rango;

import model.personajes.fisico.PersonajeFisico;
import model.personajes.Personaje;

public class Ballestero extends PersonajeFisico {
    public Ballestero(String nombre, int nivel) {
        super(nombre, nivel, 105 + nivel * 8, 15 + nivel * 2, 12 + nivel);
    }

    @Override
    public void atacar(Personaje objetivo) {
        int dano = getFuerza() + getNivel() * 2;
        objetivo.recibirDanio(dano);
        System.out.printf("%s lanza una ballesta y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public String getRol() { return "Ballestero"; }
}
