package model.personajes.fisico.melee;

import model.interfaces.Especializable;
import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;

public class Guerrero extends PersonajeFisico implements Especializable {
    public Guerrero(String nombre, int nivel) {
        super(nombre, nivel, 120 + nivel * 8, 18 + nivel * 2, 14 + nivel);
    }

    @Override
    public void atacar(Personaje objetivo) {
        int dano = getFuerza() + getNivel() * 3;
        objetivo.recibirDanio(dano);
        System.out.printf("%s golpea con espada y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public void ataqueEspecial(Personaje objetivo) {
        int dano = getFuerza() * 2 + getNivel() * 2;
        objetivo.recibirDanio(dano);
        System.out.printf("%s ejecuta Corte Relámpago y causa %d daño a %s.\n", getNombre(), dano, objetivo.getNombre());
    }

    @Override
    public String getNombreEspecial() { return "Corte Relámpago"; }

    @Override
    public String getRol() { return "Guerrero"; }
}
