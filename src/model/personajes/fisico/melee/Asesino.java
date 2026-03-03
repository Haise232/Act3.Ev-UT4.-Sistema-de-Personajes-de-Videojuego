package model.personajes.fisico.melee;

import model.personajes.Personaje;

public abstract class Asesino extends PersonajeMelee {

    public Asesino (String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura, int agilidad, int peso) {
        super(nombre, nivel, salud, estamina, fuerzaFisica, tipoArma, armadura, agilidad, peso);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("¡Ataque desde las sombras!");
        estamina -= 3;
        super.atacar(objetivo);

    }

}