import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StadtSimulation extends JPanel implements ActionListener {
    private static final int BREITE = 45;
    private static final int HOEHE = 25;
    private static final int ZELL_GROESSE = 25;
    private static final int DELAY_MS = 300;

    private char[][] stadt;
    private List<Fahrzeug> fahrzeuge;
    private List<Ampel> ampeln;
    private List<Tier> tiere;
    private Random random;
    private int zeitschritt;
    private int fahrzeugChance;
    private javax.swing.Timer timer;

    static class Fahrzeug {
        int x, y, richtungX, richtungY;
        Color farbe;
        int warteZeit;

        Fahrzeug(int x, int y, int richtungX, int richtungY, Color farbe) {
            this.x = x;
            this.y = y;
            this.richtungX = richtungX;
            this.richtungY = richtungY;
            this.farbe = farbe;
            this.warteZeit = 0;
        }
    }

    static class Ampel {
        int x, y;
        boolean gruen;
        int timer, wechselZeit;

        Ampel(int x, int y, boolean gruen, int wechselZeit) {
            this.x = x;
            this.y = y;
            this.gruen = gruen;
            this.wechselZeit = wechselZeit;
            this.timer = 0;
        }
    }

    static class Tier {
        int x, y;
        char typ;
        Color farbe;
        int bewegungsCounter;

        Tier(int x, int y, char typ, Color farbe) {
            this.x = x;
            this.y = y;
            this.typ = typ;
            this.farbe = farbe;
            this.bewegungsCounter = 0;
        }
    }

    public StadtSimulation(int fahrzeugChance) {
        this.fahrzeugChance = fahrzeugChance;
        this.stadt = new char[HOEHE][BREITE];
        this.fahrzeuge = new ArrayList<Fahrzeug>();
        this.ampeln = new ArrayList<Ampel>();
        this.tiere = new ArrayList<Tier>();
        this.random = new Random();
        this.zeitschritt = 0;

        setPreferredSize(new Dimension(BREITE * ZELL_GROESSE + 250, HOEHE * ZELL_GROESSE + 100));
        setBackground(new Color(135, 185, 145));

        initStadt();

        timer = new javax.swing.Timer(DELAY_MS, this);
        timer.start();
    }

    private void initStadt() {
        for (int y = 0; y < HOEHE; y++) {
            for (int x = 0; x < BREITE; x++) {
                stadt[y][x] = '.';
            }
        }

        erstelleFluss();
        erstelleBauernhof();
        erstelleWald();
        erstelleStadt();
        erstelleStrassen();
    }

    private void erstelleFluss() {
        int flussY = 12;
        for (int x = 0; x < BREITE; x++) {
            stadt[flussY][x] = '~';
            if (x % 3 == 0 && flussY > 0) stadt[flussY - 1][x] = '~';
            if (x % 4 == 0 && flussY < HOEHE - 1) stadt[flussY + 1][x] = '~';
        }

        for (int i = 0; i < 3; i++) {
            int brueckeX = 5 + i * 15;
            for (int dy = -1; dy <= 1; dy++) {
                if (flussY + dy >= 0 && flussY + dy < HOEHE) {
                    stadt[flussY + dy][brueckeX] = 'B';
                }
            }
        }
    }

    private void erstelleBauernhof() {
        int startX = 3;
        int startY = 3;

        for (int y = startY; y < startY + 6; y++) {
            for (int x = startX; x < startX + 8; x++) {
                if (y == startY || y == startY + 5 || x == startX || x == startX + 7) {
                    stadt[y][x] = 'Z';
                } else {
                    stadt[y][x] = 'F';
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            tiere.add(new Tier(startX + 2 + i, startY + 2, 'K', new Color(139, 69, 19)));
        }

        for (int i = 0; i < 2; i++) {
            tiere.add(new Tier(startX + 2, startY + 4 + i, 'S', new Color(255, 192, 203)));
        }

        stadt[startY + 2][startX + 10] = 'H';
        stadt[startY + 3][startX + 10] = 'H';
        stadt[startY + 2][startX + 11] = 'H';
        stadt[startY + 3][startX + 11] = 'H';
    }

    private void erstelleWald() {
        for (int i = 0; i < 25; i++) {
            int x = random.nextInt(BREITE);
            int y = random.nextInt(HOEHE);

            if (stadt[y][x] == '.') {
                stadt[y][x] = 'T';
            }
        }
    }

    private void erstelleStadt() {
        int stadtStartX = 25;
        int stadtStartY = 15;

        for (int by = 0; by < 3; by++) {
            for (int bx = 0; bx < 4; bx++) {
                int baseX = stadtStartX + bx * 4;
                int baseY = stadtStartY + by * 3;

                for (int y = baseY; y < baseY + 2 && y < HOEHE; y++) {
                    for (int x = baseX; x < baseX + 3 && x < BREITE; x++) {
                        stadt[y][x] = 'G';
                    }
                }
            }
        }
    }

    private void erstelleStrassen() {
        int[] hStrassen = {10, 14};
        for (int sy : hStrassen) {
            if (sy < HOEHE) {
                for (int x = 0; x < BREITE; x++) {
                    if (stadt[sy][x] == '.') {
                        stadt[sy][x] = '=';
                    }
                }
            }
        }

        int[] vStrassen = {15, 24, 35};
        for (int sx : vStrassen) {
            if (sx < BREITE) {
                for (int y = 0; y < HOEHE; y++) {
                    if (stadt[y][sx] == '.' || stadt[y][sx] == '=') {
                        stadt[y][sx] = '|';
                    }
                }
            }
        }

        for (int sy : hStrassen) {
            for (int sx : vStrassen) {
                if (sy < HOEHE && sx < BREITE) {
                    stadt[sy][sx] = '+';
                    ampeln.add(new Ampel(sx, sy, random.nextBoolean(), 15 + random.nextInt(10)));
                }
            }
        }
    }

    private boolean istAmpelRot(int x, int y) {
        for (Ampel a : ampeln) {
            if (a.x == x && a.y == y && !a.gruen) return true;
        }
        return false;
    }

    private boolean istFahrzeugDa(int x, int y) {
        for (Fahrzeug f : fahrzeuge) {
            if (f.x == x && f.y == y) return true;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        zeitschritt++;

        for (Ampel a : ampeln) {
            a.timer++;
            if (a.timer >= a.wechselZeit) {
                a.gruen = !a.gruen;
                a.timer = 0;
            }
        }

        List<Fahrzeug> neueFahrzeuge = new ArrayList<Fahrzeug>();
        for (Fahrzeug f : fahrzeuge) {
            if (f.warteZeit > 0) {
                f.warteZeit--;
                neueFahrzeuge.add(f);
                continue;
            }

            int nx = f.x + f.richtungX;
            int ny = f.y + f.richtungY;

            if (nx >= 0 && nx < BREITE && ny >= 0 && ny < HOEHE) {
                char ziel = stadt[ny][nx];

                if (ziel == '=' || ziel == '|' || ziel == '+' || ziel == 'B') {
                    if (!istFahrzeugDa(nx, ny) && !istAmpelRot(nx, ny)) {
                        f.x = nx;
                        f.y = ny;
                    } else {
                        f.warteZeit = 2;
                    }
                    neueFahrzeuge.add(f);
                }
            }
        }
        fahrzeuge = neueFahrzeuge;

        if (random.nextInt(100) < fahrzeugChance) {
            neuesFahrzeug();
        }

        bewegeTiere();

        repaint();
    }

    private void neuesFahrzeug() {
        if (random.nextBoolean()) {
            int y = 10;
            if (!istFahrzeugDa(0, y)) {
                Color[] farben = {new Color(255, 100, 100), new Color(100, 100, 255),
                        new Color(100, 200, 100), new Color(255, 200, 100)};
                fahrzeuge.add(new Fahrzeug(0, y, 1, 0, farben[random.nextInt(farben.length)]));
            }
        }
    }

    private void bewegeTiere() {
        for (Tier t : tiere) {
            t.bewegungsCounter++;
            if (t.bewegungsCounter > 8) {
                int dx = random.nextInt(3) - 1;
                int dy = random.nextInt(3) - 1;

                int nx = t.x + dx;
                int ny = t.y + dy;

                if (nx >= 3 && nx < 11 && ny >= 3 && ny < 9) {
                    if (stadt[ny][nx] == 'F') {
                        t.x = nx;
                        t.y = ny;
                    }
                }
                t.bewegungsCounter = 0;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int y = 0; y < HOEHE; y++) {
            for (int x = 0; x < BREITE; x++) {
                int px = x * ZELL_GROESSE;
                int py = y * ZELL_GROESSE;

                switch (stadt[y][x]) {
                    case '.':
                        g2d.setColor(new Color(120, 170, 120));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        if (random.nextInt(20) == 0) {
                            g2d.setColor(new Color(100, 150, 100));
                            g2d.fillRect(px + random.nextInt(5), py + random.nextInt(5), 3, 3);
                        }
                        break;
                    case '~':
                        g2d.setColor(new Color(70, 130, 180));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        g2d.setColor(new Color(100, 160, 210, 150));
                        g2d.fillOval(px + random.nextInt(10), py + random.nextInt(10), 8, 8);
                        break;
                    case 'B':
                        g2d.setColor(new Color(139, 90, 43));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        g2d.setColor(new Color(101, 67, 33));
                        g2d.drawRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        break;
                    case 'Z':
                        g2d.setColor(new Color(139, 90, 43));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        break;
                    case 'F':
                        g2d.setColor(new Color(205, 170, 125));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        break;
                    case 'H':
                        g2d.setColor(new Color(178, 34, 34));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        g2d.setColor(new Color(139, 0, 0));
                        g2d.fillRect(px + 3, py + 3, ZELL_GROESSE - 6, ZELL_GROESSE - 6);
                        break;
                    case 'T':
                        g2d.setColor(new Color(120, 170, 120));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        g2d.setColor(new Color(34, 139, 34));
                        g2d.fillOval(px + 3, py + 2, ZELL_GROESSE - 6, ZELL_GROESSE - 8);
                        g2d.setColor(new Color(101, 67, 33));
                        g2d.fillRect(px + 10, py + 15, 5, 10);
                        break;
                    case 'G':
                        g2d.setColor(new Color(180, 180, 200));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        g2d.setColor(new Color(100, 100, 120));
                        g2d.drawRect(px, py, ZELL_GROESSE, ZELL_GROESSE);

                        for (int wy = 0; wy < 2; wy++) {
                            for (int wx = 0; wx < 2; wx++) {
                                if (random.nextInt(3) < 2) {
                                    g2d.setColor(new Color(255, 255, 150));
                                    g2d.fillRect(px + 4 + wx * 10, py + 4 + wy * 10, 6, 6);
                                }
                            }
                        }
                        break;
                    case '=':
                        g2d.setColor(new Color(60, 60, 60));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        g2d.setColor(new Color(255, 255, 100));
                        g2d.drawLine(px, py + ZELL_GROESSE / 2, px + ZELL_GROESSE, py + ZELL_GROESSE / 2);
                        break;
                    case '|':
                        g2d.setColor(new Color(60, 60, 60));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        g2d.setColor(new Color(255, 255, 100));
                        g2d.drawLine(px + ZELL_GROESSE / 2, py, px + ZELL_GROESSE / 2, py + ZELL_GROESSE);
                        break;
                    case '+':
                        g2d.setColor(new Color(70, 70, 70));
                        g2d.fillRect(px, py, ZELL_GROESSE, ZELL_GROESSE);
                        break;
                }
            }
        }

        for (Ampel a : ampeln) {
            int px = a.x * ZELL_GROESSE;
            int py = a.y * ZELL_GROESSE;

            g2d.setColor(Color.BLACK);
            g2d.fillRect(px + 8, py + 8, 10, 10);

            g2d.setColor(a.gruen ? new Color(50, 255, 50) : new Color(255, 50, 50));
            g2d.fillOval(px + 9, py + 9, 8, 8);
        }

        for (Tier t : tiere) {
            int px = t.x * ZELL_GROESSE;
            int py = t.y * ZELL_GROESSE;

            g2d.setColor(t.farbe);
            g2d.fillOval(px + 7, py + 7, 11, 11);

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 10));
            g2d.drawString(String.valueOf(t.typ), px + 10, py + 16);
        }

        for (Fahrzeug f : fahrzeuge) {
            int px = f.x * ZELL_GROESSE;
            int py = f.y * ZELL_GROESSE;

            g2d.setColor(f.farbe);
            if (f.richtungX != 0) {
                g2d.fillRect(px + 3, py + 7, 19, 11);
                g2d.setColor(new Color(200, 220, 255));
                g2d.fillRect(px + 5, py + 9, 6, 7);
                g2d.fillRect(px + 13, py + 9, 6, 7);
            } else {
                g2d.fillRect(px + 7, py + 3, 11, 19);
                g2d.setColor(new Color(200, 220, 255));
                g2d.fillRect(px + 9, py + 5, 7, 6);
                g2d.fillRect(px + 9, py + 13, 7, 6);
            }
        }

        g2d.setColor(new Color(40, 40, 60));
        g2d.fillRect(BREITE * ZELL_GROESSE, 0, 250, HOEHE * ZELL_GROESSE + 100);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 16));
        int infoX = BREITE * ZELL_GROESSE + 15;
        g2d.drawString("LANDLICHE STADT", infoX, 30);

        g2d.setFont(new Font("Monospaced", Font.PLAIN, 13));
        g2d.drawString("Zeit: " + zeitschritt, infoX, 60);
        g2d.drawString("Autos: " + fahrzeuge.size(), infoX, 85);
        g2d.drawString("Tiere: " + tiere.size(), infoX, 110);

        g2d.drawString("Legende:", infoX, 150);
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 11));

        g2d.setColor(new Color(70, 130, 180));
        g2d.fillRect(infoX, 165, 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Fluss", infoX + 20, 177);

        g2d.setColor(new Color(205, 170, 125));
        g2d.fillRect(infoX, 190, 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Bauernhof", infoX + 20, 202);

        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(infoX, 215, 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Wald", infoX + 20, 227);

        g2d.setColor(new Color(180, 180, 200));
        g2d.fillRect(infoX, 240, 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Gebaeude", infoX + 20, 252);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final JFrame frame = new JFrame("Laendliche Stadt mit Bauernhof & Fluss");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel inputPanel = new JPanel();
                inputPanel.setBackground(new Color(40, 40, 60));
                inputPanel.setLayout(new GridLayout(2, 2, 10, 10));

                JLabel label1 = new JLabel("Auto-Wahrscheinlichkeit (5-30):");
                label1.setForeground(Color.WHITE);
                final JTextField autoField = new JTextField("15");

                JButton startButton = new JButton("Simulation starten");

                inputPanel.add(label1);
                inputPanel.add(autoField);
                inputPanel.add(new JLabel(""));
                inputPanel.add(startButton);

                frame.add(inputPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                startButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int autoChance = Integer.parseInt(autoField.getText());

                            if (autoChance < 5 || autoChance > 30) {
                                JOptionPane.showMessageDialog(frame, "Bitte Zahl zwischen 5 und 30 eingeben!");
                                return;
                            }

                            frame.getContentPane().removeAll();
                            StadtSimulation simulation = new StadtSimulation(autoChance);
                            frame.add(simulation);
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.revalidate();
                            frame.repaint();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Bitte gueltige Zahl eingeben!");
                        }
                    }
                });
            }
        });
    }
}
