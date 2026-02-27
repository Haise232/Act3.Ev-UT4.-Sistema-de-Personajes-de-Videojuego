

import java.util.UUID;

public abstract class Personaje {
    protected UUID id;
    protected String nombre;
    protected int nivel; 
    protected int salud;
    protected int saludMaxima;      
    protected boolean estaVivo;     
    protected int estamina;
    protected String tipoArma;
    protected String estadoAlterado; // "normal", "envenenado", "paralizado"

    public Personaje(String nombre, int nivel, int salud, int estamina, String tipoArma) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.nivel = nivel;
        this.salud = salud;
        this.saludMaxima = salud;    
        this.estaVivo = true;
        this.tipoArma = tipoArma; 
        this.estamina = estamina;
        this.estadoAlterado = "normal";
    }

    public abstract void atacar(Personaje objetivo);

    public abstract void recibir(int danio);


    public String obtenerEstado() {
        return String.format("[%s] HP: %d/%d | Nivel: %d | Estado: %s",
            nombre, salud, saludMaxima, nivel, estadoAlterado);
    }


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
    public int getSalud() { return salud; }
    public void setSalud(int salud) { this.salud = salud; }
    public String getTipoArma() { return tipoArma; }
    public void setTipoArma(String tipoArma) { this.tipoArma = tipoArma; }
    public int getEstamina() { return estamina; }
    public void setEstamina(int estamina) { this.estamina = estamina; }
    public boolean isEstaVivo() { return estaVivo; }
    public String getEstadoAlterado() { return estadoAlterado; }
    public void setEstadoAlterado(String estadoAlterado) { this.estadoAlterado = estadoAlterado; }
}