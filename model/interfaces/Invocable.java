package model.interfaces;

import model.personajes.Personaje;

public interface Invocable {
    void invocarServidor(String tipo);
    int contarServidores();
    void desmontarServidor(Personaje objetivo);
}
