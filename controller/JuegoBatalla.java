package controller;

import model.personajes.Personaje;
import model.interfaces.Especializable;
import model.personajes.magico.PersonajeMagico;
import model.personajes.magico.Nigromante;
import model.personajes.fisico.melee.Guerrero;
import model.personajes.fisico.melee.Asesino;
import model.personajes.fisico.rango.Arquero;
import model.personajes.fisico.rango.Ballestero;
import model.personajes.magico.Clerigo;
import model.personajes.magico.Mago;

public class JuegoBatalla {
    private Personaje jugador;
    private Personaje enemigo;
    private boolean jugadorTerminado;
    private boolean enemigoTerminado;
    private String ultimoEvento;

    public JuegoBatalla(Personaje jugador, Personaje enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.jugadorTerminado = false;
        this.enemigoTerminado = false;
        this.ultimoEvento = "Batalla iniciada entre " + jugador.getNombre() + " y " + enemigo.getNombre() + ".";
    }

    public Personaje getJugador() { return jugador; }
    public Personaje getEnemigo() { return enemigo; }
    public boolean isTerminado() { return jugadorTerminado || enemigoTerminado; }

    public String getUltimoEvento() { return ultimoEvento; }

    public String accionJugador(Accion accion) {
        if (isTerminado()) {
            return "La batalla ya terminó. Reinicia para jugar otra vez.";
        }

        switch (accion) {
            case ATACAR:
                jugador.atacar(enemigo);
                ultimoEvento = jugador.getNombre() + " ataca a " + enemigo.getNombre() + ".";
                break;
            case DEFENDER:
                jugador.defender();
                ultimoEvento = jugador.getNombre() + " se defiende para el siguiente ataque.";
                break;
            case CURAR:
                if (jugador instanceof PersonajeMagico) {
                    ((PersonajeMagico) jugador).regenerarMana(20);
                    ultimoEvento = jugador.getNombre() + " regenera maná.";
                } else {
                    jugador.curar(20);
                    ultimoEvento = jugador.getNombre() + " se cura 20 de salud.";
                }
                break;
            case ESPECIAL:
                ultimoEvento = ejecutarEspecial(jugador, enemigo);
                break;
            default:
                ultimoEvento = "Acción no válida.";
                break;
        }

        terminarSiMuerto();
        if (!isTerminado()) {
            String eventoEnemigo = turnoEnemigo();
            terminarSiMuerto();
            return ultimoEvento + "\n" + eventoEnemigo;
        }
        return ultimoEvento;
    }

    private void terminarSiMuerto() {
        if (!jugador.estaVivo()) {
            jugadorTerminado = true;
            ultimoEvento += "\n" + jugador.getNombre() + " ha sido derrotado.";
        }
        if (!enemigo.estaVivo()) {
            enemigoTerminado = true;
            ultimoEvento += "\n" + enemigo.getNombre() + " ha sido derrotado.";
        }
    }

    private String turnoEnemigo() {
        if (!enemigo.estaVivo()) {
            return enemigo.getNombre() + " no puede actuar (derrotado).";
        }

        if (enemigo instanceof Nigromante) {
            Nigromante n = (Nigromante) enemigo;
            if (n.getMana() >= 30 && n.contarServidores() > 0) {
                n.desmontarServidor(jugador);
                return enemigo.getNombre() + " usa " + "explosión de servidor" + ".";
            }
            if (n.getMana() >= 20) {
                n.lanzarHechizo(jugador);
                return enemigo.getNombre() + " lanza hechizo.";
            }
        }

        enemigo.atacar(jugador);
        return enemigo.getNombre() + " ataca a " + jugador.getNombre() + ".";
    }

    private String ejecutarEspecial(Personaje actor, Personaje objetivo) {
        if (actor instanceof Especializable) {
            Especializable especial = (Especializable) actor;
            especial.ataqueEspecial(objetivo);
            return actor.getNombre() + " usa " + especial.getNombreEspecial() + " sobre " + objetivo.getNombre() + ".";
        }
        actor.atacar(objetivo);
        return actor.getNombre() + " ataca con un ataque normal sobre " + objetivo.getNombre() + ".";
    }

    public String getResultadoFinal() {
        if (jugadorTerminado && enemigoTerminado) {
            return "Empate inesperado.";
        }
        if (enemigoTerminado) {
            return "¡Has ganado! " + jugador.getNombre() + " derrotó a " + enemigo.getNombre() + ".";
        }
        if (jugadorTerminado) {
            return "Has sido derrotado. " + enemigo.getNombre() + " gana.";
        }
        return "La batalla continúa.";
    }
}
