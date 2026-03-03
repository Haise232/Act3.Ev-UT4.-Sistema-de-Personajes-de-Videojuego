package model.personajes.fisico.melee;
import model.personajes.Personaje;


public abstract class Guerrero extends PersonajeMelee {
    
    public Guerrero (String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura, int agilidad, int peso) {
        super(nombre, nivel, salud, estamina, fuerzaFisica, tipoArma, armadura, agilidad, peso);
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println("¡Por el honor de mi clan!");
        estamina -= 5;
        super.atacar(objetivo);
    }
}