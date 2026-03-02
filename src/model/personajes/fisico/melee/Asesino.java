package model.personajes.fisico.melee;


public abstract class Asesino extends PersonajeMelee {

    public Asesino (String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura, int agilidad, int peso) {
        super(nombre, nivel, salud, estamina, fuerzaFisica, tipoArma, armadura, agilidad, peso);
    }

}