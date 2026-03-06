package app;

import controller.GestorJuego;
import view.ConsolaView;

import model.personajes.Personaje;
import model.personajes.fisico.melee.Guerrero;
import model.personajes.fisico.melee.Asesino;
import model.personajes.fisico.rango.Arquero;
import model.personajes.fisico.rango.Ballestero;
import model.personajes.magico.Mago;
import model.personajes.magico.Clerigo;
import model.personajes.magico.Nigromante;

import model.interfaces.Defendible;
import model.interfaces.Sigiloso;
import model.interfaces.Curable;
import model.interfaces.Invocable;

public class Main {

    public static void main(String[] args) {

        GestorJuego gestor = new GestorJuego();
        ConsolaView vista = new ConsolaView();

        // ── 1. Presentación ─────────────────────────────────────────────────────
        vista.mostrarTitulo("SISTEMA DE PERSONAJES — POO con Herencia e Interfaces");
        vista.mostrarMensaje("Iniciando demostración completa del sistema...");

        // ── 2. Crear personajes (uno de cada clase concreta) ────────────────────
        vista.mostrarSeccion("1. CREACIÓN DE PERSONAJES");

        Guerrero   guerrero   = new Guerrero("Thorin", 5);
        Asesino    asesino    = new Asesino("Sombra", 4);
        Arquero    arquero    = new Arquero("Legolas", 6);
        Ballestero ballestero = new Ballestero("Varys", 3);
        Mago       mago       = new Mago("Gandalf", 10);
        Mago       magoHielo  = new Mago("Elsa", 7, "Hielo");
        Clerigo    clerigo    = new Clerigo("Benedictus", 5);
        Nigromante nigromante = new Nigromante("Voldemort", 8);

        gestor.agregarPersonaje(guerrero);
        gestor.agregarPersonaje(asesino);
        gestor.agregarPersonaje(arquero);
        gestor.agregarPersonaje(ballestero);
        gestor.agregarPersonaje(mago);
        gestor.agregarPersonaje(magoHielo);
        gestor.agregarPersonaje(clerigo);
        gestor.agregarPersonaje(nigromante);

        vista.mostrarPersonajes(gestor.getPersonajes());

        // ── 3. Polimorfismo: todos atacan mediante atacar(Personaje) ────────────
        vista.mostrarSeccion("2. POLIMORFISMO — Ronda de combate automática");
        gestor.ejecutarAcciones();
        vista.mostrarEstadoGrupo(gestor.getPersonajes());

        // ── 4. Interfaces — Defendible ──────────────────────────────────────────
        vista.mostrarSeccion("3. INTERFAZ Defendible (Guerrero)");
        Personaje objetivo = asesino;
        int danioEntrante = 50;
        vista.mostrarMensaje("Ataque de " + danioEntrante + " sobre " + guerrero.getNombre());
        int danioFinal = guerrero.defender(danioEntrante);
        guerrero.recibirDanio(danioFinal);
        vista.mostrarEstadoPersonaje(guerrero);

        // ── 5. Interfaz — Sigiloso ──────────────────────────────────────────────
        vista.mostrarSeccion("4. INTERFAZ Sigiloso (Asesino)");
        asesino.esconderse();
        vista.mostrarMensaje("¿Está escondido? → " + asesino.estaEscondido());
        asesino.atacar(guerrero);
        asesino.revelarse();
        vista.mostrarEstadoPersonaje(asesino);

        // ── 6. Interfaz — Curable ───────────────────────────────────────────────
        vista.mostrarSeccion("5. INTERFAZ Curable (Clerigo)");
        vista.mostrarMensaje("Estado de " + guerrero.getNombre() + " antes de curar:");
        vista.mostrarEstadoPersonaje(guerrero);
        clerigo.curar(guerrero);
        clerigo.bendicion(asesino);
        vista.mostrarMensaje("Estado tras curación:");
        vista.mostrarEstadoPersonaje(guerrero);

        // ── 7. Interfaz — Invocable ─────────────────────────────────────────────
        vista.mostrarSeccion("6. INTERFAZ Invocable (Nigromante)");
        // Primero acumulamos almas atacando
        nigromante.lanzarHechizo(mago);
        nigromante.lanzarHechizo(mago);
        vista.mostrarMensaje("Almas acumuladas: " + nigromante.getAlmasAcumuladas());
        nigromante.invocarServidor("Esqueleto");
        nigromante.invocarServidor("Zombie");
        vista.mostrarMensaje("Servidores activos: " + nigromante.contarServidores());
        nigromante.desmontarServidor(mago);

        // ── 8. Comportamiento específico de Rango ───────────────────────────────
        vista.mostrarSeccion("7. PERSONAJES DE RANGO — Disparo y recarga");
        arquero.atacar(nigromante);
        ballestero.atacar(guerrero);
        int danoArea = arquero.disparoArea(3);
        vista.mostrarMensaje("Daño de disparo en área: " + danoArea + " por objetivo");
        arquero.recargar();
        vista.mostrarEstadoPersonaje(arquero);

        // ── 9. Comportamiento específico de Magia ───────────────────────────────
        vista.mostrarSeccion("8. PERSONAJES MÁGICOS — Maná y concentración");
        mago.atacar(ballestero);
        mago.concentrarse();
        magoHielo.atacar(nigromante);
        vista.mostrarMensaje("Elemento de " + magoHielo.getNombre() + ": " + magoHielo.getElemento());
        nigromante.ritualDeMuerte(ballestero);

        // ── 10. Estado final de todos los personajes ────────────────────────────
        vista.mostrarTitulo("ESTADO FINAL DEL GRUPO");
        vista.mostrarEstadoGrupo(gestor.getPersonajes());

        // ── 11. Resumen: iteración polimórfica ──────────────────────────────────
        vista.mostrarSeccion("RESUMEN — Iteración polimórfica sobre List<Personaje>");
        int vivos = 0, caidos = 0;
        for (Personaje p : gestor.getPersonajes()) {
            if (p.estaVivo()) vivos++; else caidos++;
        }
        vista.mostrarMensaje("Personajes vivos: " + vivos + " | Caídos: " + caidos);
        vista.mostrarMensaje("Demostración completada con éxito.");
    }
}
