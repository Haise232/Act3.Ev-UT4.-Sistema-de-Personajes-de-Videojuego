package model.personajes.fisico.rango;

import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;

public abstract class PersonajeFisicoRango extends PersonajeFisico {
    protected int alcanceMaximo;
    protected int proyectiles;
    protected int proyectilesMaximos;
    protected double precisionBase;
    protected boolean enRecarga;

    public PersonajeFisicoRango(String nombre, int nivel, int salud, int fuerzaFisica, String tipoArma, int armadura,
            int alcanceMaximo, int proyectilesMaximos, double precisionBase) {
        super(nombre, nivel, salud, fuerzaFisica, tipoArma, armadura);
        this.alcanceMaximo = alcanceMaximo;
        this.proyectilesMaximos = proyectilesMaximos;
        this.proyectiles = proyectilesMaximos;
        this.precisionBase = precisionBase;
        this.enRecarga = false;
    }

    @Override
    public void atacar(Personaje objetivo) {
        atacarDistancia(alcanceMaximo / 2, objetivo);
    }

    @Override
    public int golpeCritico() {
        if (Math.random() <= this.probabilidadCritico) {
            int danioCritico = (int) (this.fuerzaFisica * 2.5);
            System.out.println(getNombre() + " ¡Golpe crítico a distancia! Daño: " + danioCritico);
            return danioCritico;
        }
        return this.fuerzaFisica;
    }

    @Override
    public int calcularArmadura(Personaje atacante) {
        if (atacante instanceof PersonajeFisicoRango) {
            return (int) (this.armadura * 0.15);
        }
        return this.armadura;
    }

    public void atacarDistancia(int distancia, Personaje objetivo) {
        if (enRecarga) {
            System.out.println(getNombre() + " está recargando y no puede atacar.");
            return;
        }
        if (proyectiles <= 0) {
            System.out.println(getNombre() + " no tiene proyectiles y necesita recargar.");
            recargar();
            return;
        }
        if (distancia > alcanceMaximo) {
            System.out.println(getNombre() + " está fuera de alcance.");
            return;
        }
        proyectiles--;
        double precisionFinal = Math.max(0.1, precisionBase - (double) distancia / alcanceMaximo * 0.5);
        if (Math.random() > precisionFinal) {
            System.out.println(getNombre() + " falla el ataque a distancia.");
            return;
        }
        int danio = golpeCritico();
        objetivo.recibirDanio(danio);
        System.out.println(getNombre() + " ataca a distancia. Daño: " + danio
                + " | Proyectiles: " + proyectiles + "/" + proyectilesMaximos);
    }

    public void recargar() {
        proyectiles = proyectilesMaximos;
        enRecarga = false;
        System.out.println(getNombre() + " recarga. Proyectiles: " + proyectiles + "/" + proyectilesMaximos);
    }

    public int disparoArea(int numObjetivos) {
        if (proyectiles < 3) {
            System.out.println("Proyectiles insuficientes para disparo en área.");
            return 0;
        }
        proyectiles -= 3;
        int danio = (int) (this.fuerzaFisica * 0.6);
        System.out.println("¡DISPARO EN ÁREA de " + getNombre() + "! Objetivos: " + numObjetivos + " | Daño: " + danio + " c/u");
        return danio;
    }

    public int getAlcanceMaximo() { return alcanceMaximo; }
    public int getProyectiles() { return proyectiles; }
    public int getProyectilesMaximos() { return proyectilesMaximos; }
    public double getPrecisionBase() { return precisionBase; }
    public boolean isEnRecarga() { return enRecarga; }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Alcance: %d | Proyectiles: %d/%d | Precisión: %.0f%%",
                        alcanceMaximo, proyectiles, proyectilesMaximos, precisionBase * 100);
    }
}
