package view;

import model.personajes.Personaje;
import model.personajes.fisico.PersonajeFisico;
import model.personajes.magico.PersonajeMagico;
import java.util.List;

public class ConsolaView {

    public void mostrarMensaje(String mensaje) {
        System.out.println(">> " + mensaje);
    }

    public void mostrarTitulo(String titulo) {
        System.out.println("\n========================================");
        System.out.println("  " + titulo);
        System.out.println("========================================");
    }

    public void mostrarSeccion(String titulo) {
        System.out.println("\n--- " + titulo + " ---");
    }

    public void mostrarPersonajes(List<Personaje> personajes) {
        mostrarSeccion("PERSONAJES EN JUEGO");
        for (Personaje p : personajes) {
            System.out.println("  - " + p.getString());
        }
        System.out.println();
    }

    public void mostrarEstadoPersonaje(Personaje p) {
        System.out.printf("  [%s] Salud: %d/%d", p.getNombre(), p.getSalud(), p.getSaludMaxima());
        if (p instanceof PersonajeMagico) {
            PersonajeMagico pm = (PersonajeMagico) p;
            System.out.printf(" | Mana: %d/%d", pm.getMana(), pm.getManaMaximo());
        }
        if (p instanceof PersonajeFisico) {
            PersonajeFisico pf = (PersonajeFisico) p;
            System.out.printf(" | Arma: %s [%d%%]", pf.getTipoArma(), pf.getDurabilidadArma());
        }
        if (p.estaVivo()) {
            System.out.println(" (vivo)");
        } else {
            System.out.println(" (caido)");
        }
    }

    public void mostrarEstadoGrupo(List<Personaje> personajes) {
        mostrarSeccion("ESTADO DEL GRUPO");
        for (Personaje p : personajes) {
            mostrarEstadoPersonaje(p);
        }
    }

    public void mostrarSeparador() {
        System.out.println("----------------------------------------");
    }
}
