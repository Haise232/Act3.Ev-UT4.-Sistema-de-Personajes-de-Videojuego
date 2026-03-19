package controller;

import model.personajes.Personaje;

public class GestorJuego {
    private Personaje jugador;
    private Personaje enemigo;

    public GestorJuego(Personaje jugador, Personaje enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
    }

    public Personaje getJugador() { return jugador; }
    public Personaje getEnemigo() { return enemigo; }

    public void actualizarJugador(Personaje jugador) {
        this.jugador = jugador;
    }

    public void actualizarEnemigo(Personaje enemigo) {
        this.enemigo = enemigo;
    }

    public boolean batallaActiva() {
        return jugador != null && enemigo != null && jugador.estaVivo() && enemigo.estaVivo();
    }
}

