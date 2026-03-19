package model.personajes.magico;

import java.util.ArrayList;
import java.util.List;
import model.interfaces.Especializable;
import model.interfaces.Invocable;
import model.interfaces.Magico;
import model.personajes.Personaje;

public class Nigromante extends PersonajeMagico implements Magico, Invocable, Especializable {
    private final int poderOscuro;
    private int almasAcumuladas;
    private final List<String> servidores;

    public Nigromante(String nombre, int nivel) {
        super(nombre, nivel, 85 + nivel * 6, 110 + nivel * 10, 16 + nivel * 2);
        this.poderOscuro = 10 + nivel * 2;
        this.almasAcumuladas = 0;
        this.servidores = new ArrayList<>();
    }

    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    @Override
    public void lanzarHechizo(Personaje objetivo) {
        if (objetivo == null) return;
        if (!gastarMana(20)) return;
        int damage = calcularPoderMagico() + poderOscuro;
        objetivo.recibirDanio(damage);
        if (!objetivo.estaVivo()) {
            almasAcumuladas++;
            System.out.printf("%s absorbe el alma de %s. Almas: %d\n", getNombre(), objetivo.getNombre(), almasAcumuladas);
            curar(damage / 2);
        } else {
            curar(damage / 4);
        }
        System.out.printf("%s lanza Drenar Vida y causa %d a %s.\n", getNombre(), damage, objetivo.getNombre());
    }

    @Override
    public String getNombreHechizo() {
        return "Drenar Vida";
    }

    @Override
    public void invocarServidor(String tipo) {
        if (almasAcumuladas <= 0) {
            System.out.println(getNombre() + " no tiene almas para invocar servidor.");
            return;
        }
        if (!gastarMana(30)) return;
        almasAcumuladas--;
        String servidor = tipo == null ? "Servidor Oscuro" : tipo;
        servidores.add(servidor);
        System.out.printf("%s invoca %s. Servidores activos: %d.\n", getNombre(), servidor, servidores.size());
    }

    @Override
    public int contarServidores() {
        return servidores.size();
    }

    @Override
    public void desmontarServidor(Personaje objetivo) {
        if (servidores.isEmpty()) {
            System.out.println(getNombre() + " no tiene servidores para explotar.");
            return;
        }
        if (objetivo == null) return;
        String servidor = servidores.remove(servidores.size() - 1);
        int damage = poderOscuro * 3;
        objetivo.recibirDanio(damage);
        System.out.printf("%s explota a %s y causa %d de daño a %s.\n", getNombre(), servidor, damage, objetivo.getNombre());
    }

    @Override
    public void ataqueEspecial(Personaje objetivo) {
        ritualDeMuerte(objetivo);
    }

    @Override
    public String getNombreEspecial() { return "Ritual de Muerte"; }

    public void ritualDeMuerte(Personaje objetivo) {
        System.out.println(getNombre() + " usa Ritual de Muerte.");
        for (int i = 0; i < 2; i++) {
            lanzarHechizo(objetivo);
        }
    }

    @Override
    public String getRol() { return "Nigromante"; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | PoderOscuro: %d | Almas: %d | Servidores: %d", poderOscuro, almasAcumuladas, servidores.size());
    }
}
