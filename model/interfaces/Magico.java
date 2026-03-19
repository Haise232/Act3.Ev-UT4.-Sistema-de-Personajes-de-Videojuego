package model.interfaces;

import model.personajes.Personaje;

public interface Magico {
    void lanzarHechizo(Personaje objetivo);
    String getNombreHechizo();
}
