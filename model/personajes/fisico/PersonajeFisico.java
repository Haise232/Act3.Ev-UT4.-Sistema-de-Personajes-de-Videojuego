package model.personajes.fisico;

import model.personajes.Personaje;

public abstract class PersonajeFisico extends Personaje {
    protected int fuerzaFisica;
    protected int armadura;
    protected double probabilidadCritico;
    protected int durabilidadArma;
    protected String tipoArma;

    public PersonajeFisico(String nombre, int nivel, int salud, int fuerzaFisica, String tipoArma, int armadura) {
        super(nombre, nivel, salud);
        this.fuerzaFisica = fuerzaFisica;
        this.armadura = armadura;
        this.tipoArma = tipoArma;
        this.probabilidadCritico = 0.25;
        this.durabilidadArma = 100;
    }

    public abstract int golpeCritico();

    public abstract int calcularArmadura(Personaje atacante);

    @Override
    public void recibirDanio(int danio) {
        int danioReal = Math.max(0, danio - this.armadura);
        if (danioReal == 0) {
            System.out.println("¡CLINK! El ataque rebota en la armadura de " + getNombre() + " sin hacerle daño.");
        }
        super.recibirDanio(danioReal);
    }

    public int desgasteArma() {
        int prob = (int) (Math.random() * 100);
        if (prob >= 50) {
            System.out.println(getNombre() + " ¡Se desgasta el arma!");
            this.durabilidadArma -= 6;
        } else if (prob >= 25) {
            System.out.println(getNombre() + " ¡Leve desgaste del arma!");
            this.durabilidadArma -= 2;
        }
        if (this.durabilidadArma <= 0) {
            this.durabilidadArma = 0;
            if (!this.tipoArma.equals("Puños")) {
                System.out.println("¡CRACK! ¡El " + this.tipoArma + " de " + getNombre() + " se ha roto!");
                this.tipoArma = "Puños";
                this.fuerzaFisica /= 2;
            }
        }
        return this.durabilidadArma;
    }

    public int getFuerzaFisica() { return fuerzaFisica; }
    public int getArmadura() { return armadura; }
    public String getTipoArma() { return tipoArma; }
    public int getDurabilidadArma() { return durabilidadArma; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Fuerza: %d | Arma: %s [%d%%]", fuerzaFisica, tipoArma, durabilidadArma);
    }
}
