package app;

import javax.swing.*;
import view.SwingJuegoView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingJuegoView().mostrar());
    }
}
