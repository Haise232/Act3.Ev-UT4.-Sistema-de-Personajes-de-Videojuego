package controller;

import model.personajes.Personaje;
import java.util.ArrayList;
import java.util.List;

public class GestorJuego {
    private List<Personaje> personajes = new ArrayList<>();

    public void agregarPersonaje(Personaje p) {
        if (!personajes.contains(p)) personajes.add(p);
    }

    public void ejecutarAcciones() {
        System.out.println("\n=== Ronda de combate ===");
        for (int i = 0; i < personajes.size(); i++) {
            Personaje atacante = personajes.get(i);
            if (atacante.estaVivo()) {
                Personaje objetivo = personajes.get((i + 1) % personajes.size());
                if (objetivo.estaVivo() && objetivo != atacante) {
                    System.out.println("\n[Turno de " + atacante.getNombre() + "]");
                    atacante.atacar(objetivo);
                }
            }
        }
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}
