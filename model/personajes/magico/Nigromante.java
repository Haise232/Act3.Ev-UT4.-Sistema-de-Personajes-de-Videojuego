package model.personajes.magico;

import model.interfaces.Invocable;
import model.interfaces.Magico;
import model.personajes.Personaje;
import java.util.ArrayList;
import java.util.List;

public class Nigromante extends PersonajeMagico implements Magico, Invocable {

    private int poderOscuro;
    private int almasAcumuladas;
    private final List<String> servidores; // Lista de servidores invocados activos

    public Nigromante(String nombre, int nivel) {
        super(nombre, nivel, 65 + nivel * 5, 90 + nivel * 9, 16 + nivel * 2);
        this.poderOscuro = 12 + nivel * 2;
        this.almasAcumuladas = 0;
        this.servidores = new ArrayList<>();
    }

    // Obligatorio: implementa el método abstracto de Personaje
    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    // Implementación de Magico: drena vida y acumula almas al matar
    @Override
    public void lanzarHechizo(Personaje objetivo) {
        if (!gastarMana(20)) return;
        int damage = calcularPoderMagico() + poderOscuro;
        objetivo.recibirDamage(damage);
        curarSalud(damage / 4); // Roba 1/4 del daño como vida
        almasAcumuladas++;
        System.out.printf("%s drena %d de vida a %s. [Almas: %d]%n",
                getNombre(), damage, objetivo.getNombre(), almasAcumuladas);
    }

    @Override
    public String getNombreHechizo() { return "Drenar Vida"; }

    // Implementación de Invocable: consume 1 alma + mana para invocar un servidor
    @Override
    public void invocarServidor(String tipo) {
        if (almasAcumuladas <= 0 || !gastarMana(30)) {
            System.out.println(getNombre() + " no puede invocar (almas: " + almasAcumuladas + ").");
            return;
        }
        almasAcumuladas--;
        servidores.add(tipo);
        System.out.printf("%s invoca un %s. [Servidores: %d | Almas: %d]%n",
                getNombre(), tipo, servidores.size(), almasAcumuladas);
    }

    @Override
    public int contarServidores() { return servidores.size(); }

    // Elimina el último servidor y lo hace explotar sobre el objetivo
    @Override
    public void desmontarServidor(Personaje objetivo) {
        if (servidores.isEmpty()) {
            System.out.println(getNombre() + " no tiene servidores activos.");
            return;
        }
        String ultimo = servidores.remove(servidores.size() - 1); // Extrae y elimina el último
        int damage = poderOscuro * 2;
        objetivo.recibirDamage(damage);
        System.out.printf("%s explota a su %s causando %d daño. [Servidores: %d]%n",
                getNombre(), ultimo, damage, servidores.size());
    }

    // Lanza lanzarHechizo 3 veces seguidas mientras haya mana
    public void ritualDeMuerte(Personaje objetivo) {
        System.out.println(getNombre() + " inicia el Ritual de Muerte...");
        for (int i = 0; i < 3 && getMana() >= 20; i++) lanzarHechizo(objetivo);
        System.out.println("Ritual completado. Almas: " + almasAcumuladas);
    }

    public int getPoderOscuro() { return poderOscuro; }
    public int getAlmasAcumuladas() { return almasAcumuladas; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | PoderOscuro: %d | Almas: %d | Servidores: %d",
                poderOscuro, almasAcumuladas, servidores.size());
    }
}