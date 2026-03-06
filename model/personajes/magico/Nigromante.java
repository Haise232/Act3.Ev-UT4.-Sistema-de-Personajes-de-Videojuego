package model.personajes.magico;

import model.interfaces.Invocable;
import model.interfaces.Magico;
import model.personajes.Personaje;
import java.util.ArrayList;
import java.util.List;

public class Nigromante extends PersonajeMagico implements Magico, Invocable {
    private int poderOscuro;
    private int almasAcumuladas;
    private final List<String> servidores;

    public Nigromante(String nombre, int nivel) {
        super(nombre, nivel, 65 + nivel * 5, 90 + nivel * 9, 16 + nivel * 2);
        this.poderOscuro = 12 + nivel * 2;
        this.almasAcumuladas = 0;
        this.servidores = new ArrayList<>();
    }

    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    @Override
    public void lanzarHechizo(Personaje objetivo) {
        if (!gastarMana(20)) return;
        int danio = calcularPoderMagico() + poderOscuro;
        objetivo.recibirDanio(danio);
        curarSalud(danio / 4);
        almasAcumuladas++;
        System.out.printf("%s drena %d de vida a %s. [Almas: %d]%n",
                getNombre(), danio, objetivo.getNombre(), almasAcumuladas);
    }

    @Override
    public String getNombreHechizo() { return "Drenar Vida"; }

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

    @Override
    public void desmontarServidor(Personaje objetivo) {
        if (servidores.isEmpty()) {
            System.out.println(getNombre() + " no tiene servidores activos.");
            return;
        }
        String ultimo = servidores.remove(servidores.size() - 1);
        int danio = poderOscuro * 2;
        objetivo.recibirDanio(danio);
        System.out.printf("%s explota a su %s causando %d daño. [Servidores: %d]%n",
                getNombre(), ultimo, danio, servidores.size());
    }

    public void ritualDeMuerte(Personaje objetivo) {
        System.out.println(getNombre() + " inicia el Ritual de Muerte...");
        for (int i = 0; i < 3 && getMana() >= 20; i++) lanzarHechizo(objetivo);
        System.out.println("Ritual completado. Almas: " + almasAcumuladas);
    }

    public int getPoderOscuro() { return poderOscuro; }
    public int getAlmasAcumuladas() { return almasAcumuladas; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | PoderOscuro: %d | Almas: %d [Nigromante]", poderOscuro, almasAcumuladas);
    }
}
