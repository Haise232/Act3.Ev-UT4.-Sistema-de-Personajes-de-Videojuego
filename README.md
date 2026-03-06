# ⚔️ Sistema de Personajes de Videojuego

Proyecto de Java que implementa un sistema de personajes de videojuego basado en **herencia**, **polimorfismo** e **interfaces**. Demuestra los principios de la Programación Orientada a Objetos mediante una jerarquía de clases de personajes con distintas habilidades y roles de combate.

---

## 👥 Creadores

| Integrante | Rama |
|------------|------|
| **Izel** | `feature/base-fisico` |
| **Juanjo** | `feature/fisico-sigilo` |
| **Joaquín** | `feature/magico-e-interface` |
| **Adrián** | `feature/main-vista` |

---

## 📁 Estructura del Proyecto

```
├── app/
│   └── Main.java                         # Punto de entrada de la aplicación
├── controller/
│   └── GestorJuego.java                  # Controlador de la lógica de juego
├── model/
│   ├── interfaces/
│   │   ├── Curable.java                  # Capacidad de curar a otros personajes
│   │   ├── Defendible.java               # Capacidad de bloquear daño
│   │   ├── Invocable.java                # Capacidad de invocar servidores
│   │   ├── Magico.java                   # Capacidad de lanzar hechizos
│   │   └── Sigiloso.java                 # Capacidad de ocultarse
│   └── personajes/
│       ├── Personaje.java                # Clase base abstracta
│       ├── fisico/
│       │   ├── PersonajeFisico.java      # Base para personajes físicos
│       │   ├── melee/
│       │   │   ├── PersonajeFisicoMelee.java
│       │   │   ├── Asesino.java          # Melee + Sigiloso
│       │   │   └── Guerrero.java         # Melee + Defendible
│       │   └── rango/
│       │       ├── PersonajeFisicoRango.java
│       │       ├── Arquero.java          # Rango con arco
│       │       └── Ballestero.java       # Rango con ballesta
│       └── magico/
│           ├── PersonajeMagico.java      # Base para personajes mágicos
│           ├── Clerigo.java              # Mágico + Curable + Magico
│           ├── Mago.java                 # Mágico + Magico (elemental)
│           └── Nigromante.java           # Mágico + Magico + Invocable
└── view/
    └── ConsolaView.java                  # Vista por consola
```

---

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)**:

- **Modelo** (`model/`): Contiene la jerarquía de personajes y las interfaces que definen sus capacidades.
- **Vista** (`view/`): Se encarga de mostrar información al usuario por consola.
- **Controlador** (`controller/`): Gestiona la lógica del juego (rondas de combate, gestión de personajes).

### Jerarquía de Clases

```
Personaje (abstracta)
├── PersonajeFisico (abstracta)
│   ├── PersonajeFisicoMelee (abstracta)
│   │   ├── Guerrero        → implementa Defendible
│   │   └── Asesino         → implementa Sigiloso
│   └── PersonajeFisicoRango (abstracta)
│       ├── Arquero
│       └── Ballestero
└── PersonajeMagico (abstracta)
    ├── Mago                → implementa Magico
    ├── Clérigo             → implementa Curable, Magico
    └── Nigromante          → implementa Magico, Invocable
```

### Interfaces

| Interfaz | Método(s) principal(es) | Implementada por |
|----------|------------------------|------------------|
| `Defendible` | `defender(int danio)` | Guerrero |
| `Sigiloso` | `esconderse()`, `revelarse()`, `estaEscondido()` | Asesino |
| `Curable` | `curar(Personaje objetivo)` | Clérigo |
| `Magico` | `lanzarHechizo(Personaje objetivo)`, `getNombreHechizo()` | Mago, Clérigo, Nigromante |
| `Invocable` | `invocarServidor(String tipo)`, `contarServidores()`, `desmontarServidor(Personaje)` | Nigromante |

---

## 🎮 Personajes

| Personaje | Tipo | Arma | Características |
|-----------|------|------|-----------------|
| **Guerrero** | Físico Melee | Espada | Alta salud y armadura, puede bloquear daño con escudo |
| **Asesino** | Físico Melee | Daga | Ataque desde las sombras, ataque doble, capacidad de ocultarse |
| **Arquero** | Físico Rango | Arco | Alta precisión, disparo a distancia, disparo en área |
| **Ballestero** | Físico Rango | Ballesta | Mayor fuerza de impacto, disparo a distancia |
| **Mago** | Mágico | Hechizos | Hechizos elementales (Fuego, Hielo…), alta inteligencia |
| **Clérigo** | Mágico | Fe divina | Curación de aliados, bendición, daño sagrado |
| **Nigromante** | Mágico | Magia oscura | Drena vida, acumula almas, invoca servidores, ritual de muerte |

---

## ⚙️ Mecánicas del Sistema

- **Combate por turnos**: Cada personaje ataca al siguiente en la lista.
- **Golpes críticos**: Probabilidad de causar daño multiplicado.
- **Sistema de armadura**: Los personajes físicos reducen el daño entrante.
- **Desgaste de arma**: Las armas físicas se desgastan con el uso y pueden romperse.
- **Sistema de maná**: Los personajes mágicos consumen maná para lanzar hechizos y pueden regenerarlo concentrándose.
- **Proyectiles**: Los personajes de rango tienen munición limitada y deben recargar.
- **Precisión a distancia**: Afectada por la distancia al objetivo.
- **Robo de vida**: El Nigromante cura parte del daño infligido.
- **Invocación**: El Nigromante puede invocar y detonar servidores usando almas.

---

## 🚀 Ejecución

```bash
# Compilar
javac app/Main.java

# Ejecutar
java app.Main
```

---

## 🔀 Ramas del Repositorio

| Rama | Responsable | Descripción |
|------|-------------|-------------|
| `feature/base-fisico` | Izel | Clases base de personajes físicos (`Personaje`, `PersonajeFisico`, `PersonajeFisicoMelee`, `PersonajeFisicoRango`, `Guerrero`, `Arquero`, `Ballestero`) |
| `feature/fisico-sigilo` | Juanjo | Personaje `Asesino` e interfaz `Sigiloso` |
| `feature/magico-e-interface` | Joaquín | Personajes mágicos (`Mago`, `Clérigo`, `Nigromante`) e interfaces (`Curable`, `Defendible`, `Invocable`, `Magico`) |
| `feature/main-vista` | Adrián | Clase `Main`, vista por consola (`ConsolaView`) y controlador (`GestorJuego`) |