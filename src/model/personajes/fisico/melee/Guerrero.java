package model.personajes.fisico.melee;
import model.personajes.Personaje;
import model.personajes.fisico.melee.PersonajeMelee;

public class Guerrero extends PersonajeMelee {
    
    public Guerrero (String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura, int agilidad, int peso) {
        super(nombre, nivel, salud, estamina, fuerzaFisica, tipoArma, armadura, agilidad, peso);
    }

    @Override
    public void ataque(Personaje personajeObjetivo) {
        
    }
}