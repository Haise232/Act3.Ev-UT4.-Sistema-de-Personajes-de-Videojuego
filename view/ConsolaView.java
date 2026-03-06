package view;

import model.personajes.Personaje;
import java.util.List;

public class ConsolaView {
    public void mostrarMensaje(String mensaje) {
        System.out.println("Sistema: " + mensaje);
    }    

    public void mostrarPersonajes(List<Personaje> personajes) {
        System.out.println("Personajes disponibles:");
        for (Personaje p : personajes) {
            System.out.println("- " + p.getString());
        }
        System.out.println("-----------------------------");
    }
}
