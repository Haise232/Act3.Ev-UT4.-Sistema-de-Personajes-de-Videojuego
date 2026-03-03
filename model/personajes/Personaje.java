package model.personajes;

public abstract class Personaje {
	private String nombre;
	private int nivel;
	private int salud;

	public Personaje(String nombre, int nivel, int salud) {
		this.nombre = nombre;
		this.nivel = Math.max(1, nivel);
		this.salud = Math.max(1, salud);
	}

	public String getNombre() { return nombre; }
	public int getNivel() { return nivel; }
	public int getSalud() { return salud; }

	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setNivel(int nivel) { if (nivel >= 1) this.nivel = nivel; }
	public void setSalud(int salud) { if (salud >= 0) this.salud = salud; }

	public boolean estaVivo() { return salud > 0; }

	public void recibirDanio(int cantidad) {
		salud = Math.max(0, salud - Math.max(0, cantidad));
	}

	@Override
	public String toString() {
		return String.format("%s (Nivel %d) | Salud: %d", nombre, nivel, salud);
	}
}
