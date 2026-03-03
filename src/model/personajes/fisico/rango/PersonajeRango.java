package model.personajes.fisico.rango;
import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;

public abstract class PersonajeRango extends PersonajeFisico {

    protected int municion = 30;
    protected double probabilidadCamuflaje = 0.5; 

    public PersonajeRango(String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura) {
        super(nombre, nivel, salud, estamina, fuerzaFisica, tipoArma, 0);
    }

    

    @Override
    public int golpeCritico() {

        if (Math.random() < this.probabilidadCritico) {
            System.out.println("¡GOLPE CRÍTICO!");
            return this.fuerzaFisica * 2; 
        }
        else {
            return this.fuerzaFisica;
        }
    }

    @Override 
    public void atacar(Personaje objetivo) {
        if (municion > 0) {
            int danioCausado = golpeCritico();
            municion -= 1;
            objetivo.recibir(danioCausado);
        }
        else if (!this.tipoArma.equals("Puños")) {
            System.out.println("¡" + this.nombre + " se ha quedado sin munición!");
            this.durabilidadArma = 0;
            desgasteArma();
        }
        else {
            System.out.println(this.nombre + " ataca a la desesperada cuerpo a cuerpo!");
            int danioCausado = golpeCritico();
            objetivo.recibir(danioCausado);
        }
        
    }

    @Override
    public void recibir(int danio) {
        if (Math.random() < this.probabilidadCamuflaje) {
            System.out.println(this.nombre + " se camufla hábilmente! El ataque pasa de largo y no recibe daño.");
            return;
        }
        System.out.println(this.nombre + " no logró esconderse a tiempo...");
        super.recibir(danio);
    }
}