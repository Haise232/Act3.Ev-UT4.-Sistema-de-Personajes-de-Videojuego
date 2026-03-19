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
        mana = Math.min(manaMaximo, mana + Math.max(0, cantidad));
        System.out.printf("%s regenera %d maná (maná: %d/%d).\n", getNombre(), cantidad, mana, manaMaximo);
    }

    public boolean gastarMana(int coste) {
        if (mana >= coste) {
            mana -= coste;
            return true;
        }
        System.out.printf("%s no tiene suficiente maná (%d/%d).\n", getNombre(), mana, manaMaximo);
        return false;
    }

    public int calcularPoderMagico() {
        return inteligencia + getNivel() * 3;
    }

    public int getMana() { return mana; }
    public int getManaMaximo() { return manaMaximo; }
    public int getInteligencia() { return inteligencia; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Maná: %d/%d | Int: %d", mana, manaMaximo, inteligencia);
    }
}
