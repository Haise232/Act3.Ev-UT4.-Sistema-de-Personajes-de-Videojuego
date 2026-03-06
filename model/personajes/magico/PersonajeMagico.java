package model.personajes.magico;

import model.personajes.Personaje;

public abstract class PersonajeMagico extends Personaje {
    private int mana;
    private int manaMaximo;
    private int inteligencia;

    public PersonajeMagico(String nombre, int nivel, int salud, int mana, int inteligencia) {
        super(nombre, nivel, salud);
        this.manaMaximo = Math.max(1, mana);
        this.mana = this.manaMaximo;
        this.inteligencia = Math.max(1, inteligencia);
    }

    public void regenerarMana(int cantidad) {
        this.mana = Math.min(mana + Math.max(0, cantidad), manaMaximo);
        System.out.println(getNombre() + " regenera " + cantidad + " de maná. Maná: " + mana + "/" + manaMaximo);
    }

    public void concentrarse() {
        int recuperado = inteligencia * 2;
        regenerarMana(recuperado);
        System.out.println(getNombre() + " se concentra y recupera " + recuperado + " de maná.");
    }

    public boolean gastarMana(int coste) {
        if (mana >= coste) {
            mana -= coste;
            return true;
        }
        System.out.println(getNombre() + " no tiene suficiente maná! (" + mana + "/" + coste + ")");
        return false;
    }

    public int calcularPoderMagico() {
        return inteligencia + (getNivel() * 3);
    }

    public int getMana() { return mana; }
    public int getManaMaximo() { return manaMaximo; }
    public int getInteligencia() { return inteligencia; }

    public void setInteligencia(int inteligencia) { if (inteligencia >= 1) this.inteligencia = inteligencia; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Maná: %d/%d | Int: %d", mana, manaMaximo, inteligencia);
    }
}
