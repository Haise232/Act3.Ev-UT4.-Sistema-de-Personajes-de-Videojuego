package model.personajes.fisico.melee;
import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;

public abstract class PersonajeMelee extends PersonajeFisico {
    protected int agilidad; 
    protected int peso; 


    public PersonajeMelee (String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura, int agilidad, int peso) {
        super(nombre, nivel, salud, estamina, fuerzaFisica, tipoArma, armadura);
        this.agilidad = agilidad;
        this.peso = peso;
    }

    @Override 
    public void atacar(Personaje Objetivo) {
        int daniocausado = golpeCritico();
        desgasteArma();
        
        Objetivo.recibir(daniocausado);
    }

    @Override
    public int golpeCritico() {
        if (Math.random() < this.probabilidadCritico) {
            System.out.println("¡GOLPE CRÍTICO!");
            return this.fuerzaFisica * 4;
        }
        else {
            return this.fuerzaFisica;
        }
    }
}