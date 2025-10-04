package com.painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RectanglePainter extends JFrame {

    private DrawPanel drawPanel;

    public RectanglePainter() {
        setTitle("Pintor de Rectángulos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de dibujo
        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        // Key Bindings para los colores
        bindKey("R", Color.RED);   // Rojo
        bindKey("G", Color.GREEN); // Verde
        bindKey("B", Color.BLUE);  // Azul
        bindKey("Y", Color.YELLOW);// Amarillo
        bindKey("K", Color.BLACK); // Negro
        bindKey("C", null);        // Limpiar

        // Configuración para que el JFrame escuche las teclas
        setFocusable(true);
        requestFocusInWindow();
    }

    // Asignar la tecla a una acción
    private void bindKey(String key, Color color) {
        drawPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(key), key);

        drawPanel.getActionMap().put(key, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (color == null) {
                    drawPanel.clear();
                } else {
                    drawPanel.setCurrentColor(color);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RectanglePainter painter = new RectanglePainter();
            painter.setVisible(true);
        });
    }
}

class DrawPanel extends JPanel {
    private List<ColoredRect> rectangles = new ArrayList<>();
    private Point startPoint;
    private Color currentColor = Color.BLACK;

    public DrawPanel() {
        setBackground(Color.WHITE);

        // Mouse listener para dibujar los rectángulos
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point endPoint = e.getPoint();
                int x = Math.min(startPoint.x, endPoint.x);
                int y = Math.min(startPoint.y, endPoint.y);
                int width = Math.abs(startPoint.x - endPoint.x);
                int height = Math.abs(startPoint.y - endPoint.y);
                rectangles.add(new ColoredRect(x, y, width, height, currentColor));
                repaint(); // Redibujar
            }
        });
    }

    // Cambiar el color actual
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    // Limpiar todos los rectángulos
    public void clear() {
        rectangles.clear();
        repaint(); // Redibujar
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar todos los rectángulos
        for (ColoredRect rect : rectangles) {
            g.setColor(rect.color());
            g.fillRect(rect.x(), rect.y(), rect.width(), rect.height());
            g.setColor(Color.BLACK);
            g.drawRect(rect.x(), rect.y(), rect.width(), rect.height());
        }
    }
}

// Clase que representa los rectángulos coloreados
record ColoredRect(int x, int y, int width, int height, Color color) {}
