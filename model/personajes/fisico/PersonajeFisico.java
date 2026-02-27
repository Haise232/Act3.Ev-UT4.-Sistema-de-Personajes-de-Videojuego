import java.util.UUID;
import model.personajes.Personaje;

public abstract class PersonajeFisico extends Personaje {

    protected int fuerzaFisica;
    protected int armadura;
    protected double probabilidadCritico;
    protected int durabilidadArma;        

    public PersonajeFisico(String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura) {
        super(nombre, nivel, salud, estamina, tipoArma); 
        this.fuerzaFisica = fuerzaFisica;
        this.armadura = armadura;
        this.probabilidadCritico = 0.25;  
        this.durabilidadArma = 100;
    }

    public abstract  int  golpeCritico();

    public int desgasteArma() {
        int probabilidadDesgaste = (int) (Math.random() * 100 );

        if (probabilidadDesgaste >= 25 && probabilidadDesgaste < 50) {
            System.out.println("¡SE DESGASTA EL ARMA..!");
            return  this.durabilidadArma -= 2;
        }
        else if (probabilidadDesgaste >= 50) {
            System.out.println("¡SE DESGASTA EL ARMA..!");
            return this.durabilidadArma -= 6;
        }
        

        if (durabilidadArma <= 0) {
            System.out.println(this.nombre + " ¡su " + this.tipoArma + " se ha roto! Pelea con los puños.");
            tipoArma = "puños";
            fuerzaFisica = (int)(fuerzaFisica * 0.6);
            this.durabilidadArma = 0;
        }

        if (this.durabilidadArma <= 0) {
            this.durabilidadArma = 0; 

            if (!this.tipoArma.equals("Puños")) { 
                
                System.out.println("¡CRACK! ¡Tu " + this.tipoArma + " se ha roto en pedazos!");
                this.tipoArma = "Puños";

                this.fuerzaFisica = this.fuerzaFisica / 2; 
                
                System.out.println("Ahora peleas a puñetazos. Tu fuerza baja drásticamente a: " + this.fuerzaFisica);
            }
        }

        return this.durabilidadArma;
    }

    public abstract int calcularArmadura(Personaje atacante);


}