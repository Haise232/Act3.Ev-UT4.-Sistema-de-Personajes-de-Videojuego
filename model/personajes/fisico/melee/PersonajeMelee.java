public class PersonajeMelee extends PersonajeFisicio {
    protected int agilidad; 
    protected int peso; 


    public PersonajeMelee (String nombre, int nivel, int salud, int estamina, int fuerzaFisica, String tipoArma, int armadura, int agilidad, int peso) {
        super(nombre, nivel, salud, estamina, tipoArma, fuerzaFisica);
        this.agilidad = agilidad;
        this.peso = peso;
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