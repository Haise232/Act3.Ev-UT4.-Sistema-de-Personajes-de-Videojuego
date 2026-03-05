import model.personajes.Personaje; 

public abstract class PersonajeFisicoRango extends PersonajeFisico {

    protected int alcanceMaximo;
    protected int proyectiles;
    protected int proyectilesMaximos;
    protected double precisionBase;
    protected boolean enRecarga; 

    public PersonajeFisicoRango(String nombre, int vida, int ataque, int defensa, int alcanceMaximo, int proyectilesMaximos, double precisionBase) {
        super(nombre, vida, ataque, defensa);
        this.alcanceMaximo = alcanceMaximo;
        this.proyectilesMaximos = proyectilesMaximos;
        this.proyectiles = proyectilesMaximos; // Inicialmente tiene todos los proyectiles
        this.precisionBase = precisionBase;
        this.enRecarga = false; // Inicialmente no está en recarga
    }

    @Override
    public int golpeCritico(){
        if (Math.random() <= this.probabilidadCritico) {
            int dañoCritico = (int) (this.fuerzaFisica * 2.5);
            System.out.println(this.nombre + " ha realizado un golpe crítico con daño de " + dañoCritico);
            return dañoCritico;
        }
        return 0; // No se ha realizado un golpe crítico
    }

    @Override 
    public int calcularArmadura (Personaje atacante) {
        if (atacante instanceof PersonajeFisicoRango) {
            int reducida = (int) ( this.armadura * 0.15); // Reduce la armadura en un 15% contra ataques a distancia
            return reducida;

        }
        return this.armadura; // Sin reducción para otros tipos de ataques
    }

    public int atacarDistancia(int distancia) {
        if (enRecarga) {
            System.out.println(this.nombre + " está recargando y no puede atacar.");
            return 0; // No puede atacar mientras está en recarga
        }

        if (proyectiles <= 0) {
            System.out.println(this.nombre + " no tiene proyectiles y necesita recargar.");
            return 0; // No puede atacar sin proyectiles
        }

        if (distancia > alcanceMaximo) {
            System.out.println(this.nombre + " está fuera de alcance y no puede atacar.");
            return 0; // No puede atacar si el objetivo está fuera de alcance
        }

        proyectiles--; // Dispara un proyectil
        double precisionFinal = Math.max(0.1, precisionBase - (double) distancia / alcanceMaximo * 0.5); // La precisión disminuye con la distancia, pero no puede ser menor a 0.1

        if (Math.random() > precisionFinal) {
            System.out.println(this.nombre + " ha fallado el ataque a distancia.");
            return 0; // El ataque ha fallado
        }
        int daño = this.ataque; // Daño base del personaje
        System.out.println(this.nombre + " ha atacado a distancia con un daño de " + daño);
        return daño;
    }
    public void recargar() {
        if (enRecarga) {
            System.out.println(this.nombre + " ya está recargando.");
            return; // Ya está en recarga
        }
        enRecarga = true;
        proyectiles = proyectilesMaximos; // Recarga todos los proyectiles
        System.out.println(this.nombre + " ha recargado y ahora tiene " + proyectiles + " proyectiles.");
    }

    public int disparoArea(int numObjetivos) {
        if (proyectiles < 3) { System.out.println("Proyectiles insuficientes para disparo en área."); return 0; }
        proyectiles -= 3;
        int damage = (int) (this.ataque * 0.6);
        System.out.println("¡DISPARO EN ÁREA! " + numObjetivos + " objetivo(s) - Daño: " + damage + " c/u");
        return damage;
    }

    public int getAlcanceMaximo() {
        return alcanceMaximo;
    }
    public int getProyectiles() {
        return proyectiles;
    }
    public int getProyectilesMaximos() {
        return proyectilesMaximos;
    }
    public double getPrecisionBase() {
        return precisionBase;
    }
    public boolean isEnRecarga() {
        return enRecarga;
    }

    public void serAlcanceMaximo(int alcanceMaximo) {
        this.alcanceMaximo = alcanceMaximo;
    }

    public void serPrecisionBase(double precisionBase) {
        this.precisionBase = precisionBase;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n[Rango] Alcance: " + alcanceMaximo
                + " | Proyectiles: " + proyectiles + "/" + proyectilesMaximos
                + " | Precisión: " + String.format("%.0f%%", precisionBase * 100)
                + " | Recargando: " + (enRecarga ? "Sí" : "No");
    }

}