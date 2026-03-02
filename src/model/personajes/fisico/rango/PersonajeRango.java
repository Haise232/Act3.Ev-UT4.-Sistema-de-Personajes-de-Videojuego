public class PersonajeRango extends PersonajeFisico {

    protected int municion;
    protected int probabilidadCamuflaje; 

    public PersonajeRango(String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura) {
        super(nombre, nivel, salud, estamina, tipoArma);
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
}