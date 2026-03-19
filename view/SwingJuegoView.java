package view;

import controller.Accion;
import controller.JuegoBatalla;
import model.personajes.Personaje;
import model.personajes.fisico.melee.Asesino;
import model.personajes.fisico.melee.Guerrero;
import model.personajes.fisico.rango.Arquero;
import model.personajes.fisico.rango.Ballestero;
import model.personajes.magico.Clerigo;
import model.personajes.magico.Mago;
import model.personajes.magico.Nigromante;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Map;

public class SwingJuegoView {
    private JFrame frame;
    private JTextField nombreJugadorField;
    private JComboBox<String> comboJugador;
    private JComboBox<String> comboEnemigo;
    private JButton iniciarButton;
    private JButton reiniciarButton;
    private JLabel jugadorLabel;
    private JLabel enemigoLabel;
    private JProgressBar jugadorSaludBar;
    private JProgressBar enemigoSaludBar;
    private JTextArea logArea;
    private JButton atacarButton;
    private JButton defenderButton;
    private JButton curarButton;
    private JButton especialButton;

    private JuegoBatalla juego;

    public void mostrar() {
        frame = new JFrame("Batalla estilo Pokémon RPG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(820, 620);
        frame.setLocationRelativeTo(null);

        frame.setContentPane(crearPanelPrincipal());
        frame.setVisible(true);
        setActionButtonsEnabled(false);
    }

    private JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        panel.add(crearPanelTop(), BorderLayout.NORTH);
        panel.add(crearPanelCenter(), BorderLayout.CENTER);
        panel.add(crearPanelBottom(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelTop() {
        JPanel top = new JPanel(new BorderLayout(8, 8));
        top.setBorder(BorderFactory.createTitledBorder("Configuración de batalla"));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Nombre jugador:"), c);
        c.gridx = 1; nombreJugadorField = new JTextField("Entrenador", 15); form.add(nombreJugadorField, c);

        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Personaje jugador:"), c);
        c.gridx = 1; comboJugador = new JComboBox<>(new String[]{"Guerrero", "Asesino", "Arquero", "Ballestero", "Mago", "Nigromante", "Clerigo"}); form.add(comboJugador, c);

        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Personaje enemigo:"), c);
        c.gridx = 1; comboEnemigo = new JComboBox<>(new String[]{"Guerrero", "Asesino", "Arquero", "Ballestero", "Mago", "Nigromante", "Clerigo"}); form.add(comboEnemigo, c);

        c.gridx = 0; c.gridy = 3; iniciarButton = new JButton("Iniciar batalla"); iniciarButton.addActionListener(e -> iniciarBatalla()); form.add(iniciarButton, c);
        c.gridx = 1; reiniciarButton = new JButton("Reiniciar"); reiniciarButton.addActionListener(e -> reiniciar()); form.add(reiniciarButton, c);

        top.add(form, BorderLayout.CENTER);
        return top;
    }

    private JPanel crearPanelCenter() {
        JPanel center = new JPanel(new BorderLayout(10, 10));

        JPanel statusPanel = new JPanel(new GridLayout(2, 1, 8, 8));
        statusPanel.setBorder(BorderFactory.createTitledBorder("Estados"));

        jugadorLabel = crearLabelCard("Jugador: -", new Color(38, 90, 170));
        enemigoLabel = crearLabelCard("Enemigo: -", new Color(170, 40, 40));

        jugadorSaludBar = crearBarraSalud();
        enemigoSaludBar = crearBarraSalud();

        statusPanel.add(crearPanelEstado(jugadorLabel, jugadorSaludBar));
        statusPanel.add(crearPanelEstado(enemigoLabel, enemigoSaludBar));

        center.add(statusPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setPreferredSize(new Dimension(780, 280));
        scroll.setBorder(BorderFactory.createTitledBorder("Registro de combate"));

        center.add(scroll, BorderLayout.CENTER);
        return center;
    }

    private JPanel crearPanelBottom() {
        JPanel bottom = new JPanel(new GridLayout(1, 4, 8, 8));
        bottom.setBorder(BorderFactory.createTitledBorder("Acciones"));

        atacarButton = new JButton("Atacar");
        defenderButton = new JButton("Defender");
        curarButton = new JButton("Curar/Regenerar");
        especialButton = new JButton("Especial");

        atacarButton.addActionListener(e -> ejecutarAccion(Accion.ATACAR));
        defenderButton.addActionListener(e -> ejecutarAccion(Accion.DEFENDER));
        curarButton.addActionListener(e -> ejecutarAccion(Accion.CURAR));
        especialButton.addActionListener(e -> ejecutarAccion(Accion.ESPECIAL));

        bottom.add(atacarButton);
        bottom.add(defenderButton);
        bottom.add(curarButton);
        bottom.add(especialButton);

        return bottom;
    }

    private JLabel crearLabelCard(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        label.setBackground(new Color(245, 245, 245));
        label.setForeground(Color.BLACK);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        return label;
    }

    private JProgressBar crearBarraSalud() {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setStringPainted(true);
        bar.setForeground(new Color(34, 170, 34));
        bar.setBackground(Color.LIGHT_GRAY);
        return bar;
    }

    private JPanel crearPanelEstado(JLabel label, JProgressBar salud) {
        JPanel p = new JPanel(new BorderLayout(4, 4));
        p.add(label, BorderLayout.NORTH);
        p.add(salud, BorderLayout.CENTER);
        return p;
    }

    private void iniciarBatalla() {
        String nombre = nombreJugadorField.getText().trim();
        if (nombre.isEmpty()) nombre = "Entrenador";

        Personaje jugador = crearPersonaje((String) comboJugador.getSelectedItem(), nombre);
        Personaje enemigo = crearPersonaje((String) comboEnemigo.getSelectedItem(), "Enemigo");

        juego = new JuegoBatalla(jugador, enemigo);
        logArea.setText("");
        appendLog("Batalla iniciada: " + jugador.getNombre() + " vs " + enemigo.getNombre() + ".");
        appendLog(juego.getUltimoEvento());
        actualizarEstado();
        setActionButtonsEnabled(true);
    }

    private void reiniciar() {
        juego = null;
        logArea.setText("");
        jugadorLabel.setText("Jugador: -");
        enemigoLabel.setText("Enemigo: -");
        jugadorSaludBar.setValue(0);
        enemigoSaludBar.setValue(0);
        setActionButtonsEnabled(false);
    }

    private Personaje crearPersonaje(String tipo, String nombre) {
        switch (tipo) {
            case "Guerrero": return new Guerrero(nombre, 1);
            case "Asesino": return new Asesino(nombre, 1);
            case "Arquero": return new Arquero(nombre, 1);
            case "Ballestero": return new Ballestero(nombre, 1);
            case "Mago": return new Mago(nombre, 1);
            case "Nigromante": return new Nigromante(nombre, 1);
            default: return new Clerigo(nombre, 1);
        }
    }

    private void ejecutarAccion(Accion accion) {
        if (juego == null || juego.isTerminado()) {
            appendLog("Inicia una batalla primero para poder usar acciones.");
            return;
        }

        String evento = juego.accionJugador(accion);
        appendLog(evento);
        actualizarEstado();

        if (juego.isTerminado()) {
            appendLog("--- " + juego.getResultadoFinal() + " ---");
            setActionButtonsEnabled(false);
        }
    }

    private void actualizarEstado() {
        if (juego == null) return;

        Personaje j = juego.getJugador();
        Personaje e = juego.getEnemigo();

        jugadorLabel.setText(getStatusLinea(j, "Jugador"));
        enemigoLabel.setText(getStatusLinea(e, "Enemigo"));

        jugadorSaludBar.setMaximum(j.getSaludMaxima());
        jugadorSaludBar.setValue(j.getSalud());
        jugadorSaludBar.setString(j.getSalud() + " / " + j.getSaludMaxima());

        enemigoSaludBar.setMaximum(e.getSaludMaxima());
        enemigoSaludBar.setValue(e.getSalud());
        enemigoSaludBar.setString(e.getSalud() + " / " + e.getSaludMaxima());
    }

    private String getStatusLinea(Personaje p, String etiqueta) {
        String estado = String.format("%s: %s (Nivel %d)", etiqueta, p.getNombre(), p.getNivel());
        if (p instanceof model.personajes.magico.PersonajeMagico) {
            model.personajes.magico.PersonajeMagico mago = (model.personajes.magico.PersonajeMagico) p;
            estado += String.format(" | Maná: %d/%d", mago.getMana(), mago.getManaMaximo());
        }
        if (p.isDefendiendo()) {
            estado += " | Defendiendo";
        }
        return estado;
    }

    private void appendLog(String texto) {
        logArea.append(texto + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void setActionButtonsEnabled(boolean enabled) {
        atacarButton.setEnabled(enabled);
        defenderButton.setEnabled(enabled);
        curarButton.setEnabled(enabled);
        especialButton.setEnabled(enabled);
    }
}
