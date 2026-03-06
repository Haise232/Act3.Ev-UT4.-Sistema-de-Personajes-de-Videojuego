package app;

import controller.GestorJuego;
import view.ConsolaView;
import model.personajes.fisicos.melee.*;
import model.personajes.fisicos.rango.*;
import model.personajes.magicos.*;

public class Main {
    public static void main(String[] args) {
        GestorJuego gestor = new GestorJuego();
        ConsolaView vista = new ConsolaView();

        vista.mostrarMensaje("Bienvenido al juego de personajes");

        gestor.agregarPersonaje(new Guerrero());
        gestor.agregarPersonaje(new Asesino());
        gestor.agregarPersonaje(new Mago());

        vista.mostrarPersonajes(gestor.getPersonajes());
        gestor.ejecutarAcciones();
    }
}