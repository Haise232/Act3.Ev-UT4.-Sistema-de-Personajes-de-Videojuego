package app;

import controller.GestorJuego;
import view.ConsolaView;
import model.personajes.fisico.melee.Guerrero;
import model.personajes.fisico.melee.Asesino;
import model.personajes.magico.Mago;

public class Main {
    public static void main(String[] args) {
        GestorJuego gestor = new GestorJuego();
        ConsolaView vista = new ConsolaView();

        vista.mostrarMensaje("Bienvenido al juego de personajes");

        gestor.agregarPersonaje(new Guerrero("Thorin", 5));
        gestor.agregarPersonaje(new Asesino("Sombra", 4));
        gestor.agregarPersonaje(new Mago("Gandalf", 10));

        vista.mostrarPersonajes(gestor.getPersonajes());
        gestor.ejecutarAcciones();
    }
}
