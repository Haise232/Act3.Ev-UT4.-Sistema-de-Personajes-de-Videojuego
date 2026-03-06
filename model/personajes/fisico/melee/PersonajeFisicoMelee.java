package model.personajes.fisico.melee;

import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;

public abstract class PersonajeFisicoMelee extends PersonajeFisico {
    protected int agilidad;
    protected int peso;

    public PersonajeFisicoMelee(String nombre, int nivel, int salud, int fuerzaFisica, String tipoArma, int armadura, int agilidad, int peso) {
        super(nombre, nivel, salud, fuerzaFisica, tipoArma, armadura);
        this.agilidad = agilidad;
        this.peso = peso;
    }

    @Override
    public void atacar(Personaje objetivo) {
        int danioCausado = golpeCritico();
        desgasteArma();
        objetivo.recibirDanio(danioCausado);
    }

    @Override
    public int golpeCritico() {
        if (Math.random() < this.probabilidadCritico) {
            System.out.println("¡GOLPE CRÍTICO de " + getNombre() + "!");
            return this.fuerzaFisica * 4;
        }
        return this.fuerzaFisica;
    }

    @Override
    public int calcularArmadura(Personaje atacante) {
        return this.armadura;
    }

    public int getAgilidad() { return agilidad; }
    public int getPeso() { return peso; }
}
