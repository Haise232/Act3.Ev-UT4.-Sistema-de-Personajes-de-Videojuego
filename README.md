# Sistema de Personajes de Videojuego (Turnos estilo RPG)

Juego de combate por turnos con clases, habilidades y UI Swing.

## 🚀 Qué incluye esta versión

- Motor de batalla en `controller/JuegoBatalla.java`.
- Interfaz gráfica en `view/SwingJuegoView.java`.
- Personajes físicos y mágicos con estadísticas y ataques.
- Habilidades especiales con interfaz `Especializable`.
- Sistema de estado de batalla con salud/maná y registro de eventos.

## 📁 Estructura del proyecto

- `app/Main.java` - Punto de entrada (lanza Swing).
- `controller/JuegoBatalla.java` - Lógica de combate y turnos.
- `controller/Accion.java` - Enum de acciones (ATACAR, DEFENDER, CURAR, ESPECIAL).
- `controller/GestorJuego.java` - Clase de gestión de personajes.
- `view/SwingJuegoView.java` - Interfaz gráfica con selección y acciones.
- `model/personajes` - Clases de personajes.
- `model/interfaces` - Contratos de habilidades (`Magico`, `Invocable`, `Especializable`).

## ▶️ Cómo ejecutar

1. Compilar todo:
   ```bash
   find . -name '*.java' > /tmp/javafiles.txt && javac @/tmp/javafiles.txt
   ```
2. Ejecutar:
   ```bash
   java app.Main
   ```

## 🎮 Cómo jugar (UI Swing)

1. Ingresa nombre de jugador.
2. Elige las clases de tu personaje y del enemigo.
3. Presiona "Iniciar batalla".
4. Usa los botones para atacar, defender, curar o usar especial.
5. La batalla termina cuando uno de los dos llega a 0 de salud.

## 🧠 Mejoras de diseño realizadas

- Refactorización del motor de combate para separar lógica de UI.
- Uso de interfaz `Especializable` para acciones especiales genericas.
- Interfaz Swing con barras de salud, log y modo reiniciar.
- Mejora de las clases de personajes con métodos de resumen y limpieza.

## 💡 Ideas para la próxima versión

1. Guardar y cargar partidas desde JSON.
2. Implementar equipo de 3 personajes y selección de swaps.
3. Añadir estados (veneno, aturdido, quemado) y efectos por turno.
4. Añadir daños críticos y resistencias elementales.

---

> Nota: Si modificas o agregas clases, asegúrate de recompilar todas las clases con `find . -name '*.java' > /tmp/javafiles.txt && javac @/tmp/javafiles.txt`.

