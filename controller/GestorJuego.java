package controller;

import model.personajes.Personaje;
import model.interfaces.Curable;
import model.interfaces.Defendible;
import java.util.ArrayList;
import java.util.List;

public class GestorJuego {
    private List<Personaje> personajes = new ArrayList<>();

    public void agregarPersonaje(Personaje p) {
        if (!personajes.contains(p)) personajes.add(p);
    }

    public void ejecutarAccion(){
        for (Personaje p : personajes) {
            p.atacar(null);

            if (p instanceof Curable) {
                ((Curable) p).recibirCura(10);
            }
        }
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}