package model.interfaces;

import model.personajes.Personaje;

public interface Especializable {
    void ataqueEspecial(Personaje objetivo);
    String getNombreEspecial();
}
