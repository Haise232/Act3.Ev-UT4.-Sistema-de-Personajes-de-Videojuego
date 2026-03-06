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

public class Main {

    public static void main(String[] args) {

        GestorJuego gestor = new GestorJuego();
        ConsolaView vista = new ConsolaView();

        // Presentacion
        vista.mostrarTitulo("SISTEMA DE PERSONAJES - Herencia e Interfaces");
        vista.mostrarMensaje("Iniciando demostracion del sistema...");

        // Crear personajes
        vista.mostrarSeccion("1. CREACION DE PERSONAJES");

        Guerrero guerrero = new Guerrero("Thorin", 5);
        Asesino asesino = new Asesino("Sombra", 4);
        Arquero arquero = new Arquero("Legolas", 6);
        Ballestero ballestero = new Ballestero("Varys", 3);
        Mago mago = new Mago("Gandalf", 10);
        Mago magoHielo = new Mago("Elsa", 7, "Hielo");
        Clerigo clerigo = new Clerigo("Benedictus", 5);
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

        // Polimorfismo: todos atacan
        vista.mostrarSeccion("2. POLIMORFISMO - Ronda de combate");
        gestor.ejecutarAcciones();
        vista.mostrarEstadoGrupo(gestor.getPersonajes());

        // Interfaz Defendible
        vista.mostrarSeccion("3. INTERFAZ Defendible (Guerrero)");
        int danioEntrante = 50;
        vista.mostrarMensaje("Ataque de " + danioEntrante + " sobre " + guerrero.getNombre());
        int danioFinal = guerrero.defender(danioEntrante);
        guerrero.recibirDanio(danioFinal);
        vista.mostrarEstadoPersonaje(guerrero);

        // Interfaz Sigiloso
        vista.mostrarSeccion("4. INTERFAZ Sigiloso (Asesino)");
        asesino.esconderse();
        vista.mostrarMensaje("Esta escondido? -> " + asesino.estaEscondido());
        asesino.atacar(guerrero);
        asesino.revelarse();
        vista.mostrarEstadoPersonaje(asesino);

        // Interfaz Curable
        vista.mostrarSeccion("5. INTERFAZ Curable (Clerigo)");
        vista.mostrarMensaje("Estado de " + guerrero.getNombre() + " antes de curar:");
        vista.mostrarEstadoPersonaje(guerrero);
        clerigo.curar(guerrero);
        clerigo.bendicion(asesino);
        vista.mostrarMensaje("Estado tras curacion:");
        vista.mostrarEstadoPersonaje(guerrero);

        // Interfaz Invocable
        vista.mostrarSeccion("6. INTERFAZ Invocable (Nigromante)");
        nigromante.lanzarHechizo(mago);
        nigromante.lanzarHechizo(mago);
        vista.mostrarMensaje("Almas acumuladas: " + nigromante.getAlmasAcumuladas());
        nigromante.invocarServidor("Esqueleto");
        nigromante.invocarServidor("Zombie");
        vista.mostrarMensaje("Servidores activos: " + nigromante.contarServidores());
        nigromante.desmontarServidor(mago);

        // Personajes de Rango
        vista.mostrarSeccion("7. PERSONAJES DE RANGO - Disparo y recarga");
        arquero.atacar(nigromante);
        ballestero.atacar(guerrero);
        int danoArea = arquero.disparoArea(3);
        vista.mostrarMensaje("Dano de disparo en area: " + danoArea + " por objetivo");
        arquero.recargar();
        vista.mostrarEstadoPersonaje(arquero);

        // Personajes Magicos
        vista.mostrarSeccion("8. PERSONAJES MAGICOS - Mana y concentracion");
        mago.atacar(ballestero);
        mago.concentrarse();
        magoHielo.atacar(nigromante);
        vista.mostrarMensaje("Elemento de " + magoHielo.getNombre() + ": " + magoHielo.getElemento());
        nigromante.ritualDeMuerte(ballestero);

        // Estado final
        vista.mostrarTitulo("ESTADO FINAL DEL GRUPO");
        vista.mostrarEstadoGrupo(gestor.getPersonajes());

        // Resumen
        vista.mostrarSeccion("RESUMEN");
        int vivos = 0;
        int caidos = 0;
        for (Personaje p : gestor.getPersonajes()) {
            if (p.estaVivo()) {
                vivos++;
            } else {
                caidos++;
            }
        }
        vista.mostrarMensaje("Personajes vivos: " + vivos + " | Caidos: " + caidos);
        vista.mostrarMensaje("Demostracion completada.");
    }
}
