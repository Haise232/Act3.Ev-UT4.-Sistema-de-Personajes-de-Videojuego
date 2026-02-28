package model.personajes.magico;

import model.interfaces.Invocable;
import model.interfaces.Magico;
import model.personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Nigromante extends PersonajeMagico implements Magico, Invocable {

    private int poderOscuro;
    private int almasAcumuladas;
    private final List<String> servidoresInvocados;

    public Nigromante(String nombre, int nivel) {
        super(nombre, nivel,
                65 + nivel * 5, // salud (frágil)
                90 + nivel * 9, // mana (alto) para el tema de invocaciones
                16 + nivel * 2); // inteligencia
        this.poderOscuro = 12 + nivel * 2;
        this.almasAcumuladas = 0;
        this.servidoresInvocados = new ArrayList<>();
    }

    @Override
    public void lanzarHechizo(Personaje objetivo) {
        int coste = 20;
        if (gastarMana(coste)) {
            int damage = calcularPoderMagico() + poderOscuro;
            objetivo.recibirDamage(damage);
            // El nigromante roba parte de la vida drenada
            int vida = damage / 4;
            curarSalud(vida);
            almasAcumuladas++;
            System.out.printf("%s lanza '%s' sobre %s: %d daño oscuro, +%d vida robada. [Almas: %d]%n",
                    getNombre(), getNombreHechizo(), objetivo.getNombre(),
                    damage, vida, almasAcumuladas);
        }
    }

    @Override
    public String getNombreHechizo() {
        return "Drenar Vida";
    }

    // EInvocación de servidores usando almas acumuladas
    @Override
    public void invocarServidor(String tipoServidor) {
        int coste = 30;
        if (almasAcumuladas > 0 && gastarMana(coste)) {
            almasAcumuladas--;
            servidoresInvocados.add(tipoServidor);
            System.out.printf("%s invoca un %s usando un alma! [Servidores: %d | Almas: %d]%n",
                    getNombre(), tipoServidor, servidoresInvocados.size(), almasAcumuladas);
        } else {
            System.out.println(getNombre() + " necesita almas para invocar (tiene " + almasAcumuladas + ").");
        }
    }

    @Override
    public int contarServidores() {
        return servidoresInvocados.size();
    }

    @Override
    public void desmontarServidor(Personaje objetivo) {
        if (!servidoresInvocados.isEmpty()) {
            String ultimo = servidoresInvocados.remove(servidoresInvocados.size() - 1);
            int damageExplosion = poderOscuro * 2;
            objetivo.recibirDamage(damageExplosion);
            System.out.printf("%s hace explotar a su %s sobre %s causando %d daño! [Servidores: %d]%n",
                    getNombre(), ultimo, objetivo.getNombre(), damageExplosion, servidoresInvocados.size());
        } else {
            System.out.println(getNombre() + " no tiene servidores activos.");
        }
    }

    public void ritualdeMuerte(Personaje objetivo) {
        System.out.println(getNombre() + " comienza el Ritual de Muerte...");
        for (int i = 0; i < 3; i++) {
            if (getMana() >= 20)
                lanzarHechizo(objetivo);
        }
        System.out.println("Ritual completado. Almas acumuladas: " + almasAcumuladas);
    }

    public int getPoderOscuro() {
        return poderOscuro;
    }

    public int getAlmasAcumuladas() {
        return almasAcumuladas;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | PoderOscuro: %d | Almas: %d | Servidores: %d",
                poderOscuro, almasAcumuladas, servidoresInvocados.size());
    }

}