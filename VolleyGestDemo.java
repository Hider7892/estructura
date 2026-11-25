import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * VOLLEYGEST - Prototipo visual e interactivo
 * Proyecto demostrativo para presentar el sistema de gestion de una escuela de voleibol.
 *
 * Requisitos: Java 17 o superior. No requiere librerias externas ni base de datos.
 * Ejecucion: javac VolleyGestDemo.java && java VolleyGestDemo
 *
 * NOTA: utiliza datos de ejemplo en memoria. Los cambios se pierden al cerrar la aplicacion.
 */
public class VolleyGestDemo extends JFrame {
    private static final Color NAVY = new Color(13, 42, 75);
    private static final Color NAVY_LIGHT = new Color(22, 62, 105);
    private static final Color BLUE = new Color(32, 111, 207);
    private static final Color BLUE_DARK = new Color(23, 82, 153);
    private static final Color BG = new Color(241, 246, 252);
    private static final Color TEXT = new Color(29, 43, 60);
    private static final Color MUTED = new Color(112, 128, 146);
    private static final Color GREEN = new Color(24, 161, 101);
    private static final Color ORANGE = new Color(239, 133, 34);
    private static final Color PURPLE = new Color(119, 81, 190);
    private static final Color TEAL = new Color(15, 151, 155);

    private final JPanel content = new JPanel(new BorderLayout());
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel pages = new JPanel(cardLayout);
    private final Map<String, JButton> navButtons = new LinkedHashMap<>();

    private final DefaultTableModel deportistasModel = model(
            new String[]{"Documento", "Nombre", "Edad", "Categoria", "Estado"},
            new Object[][]{
                    {"1023456789", "Juan Perez", 14, "Infantil", "Activo"},
                    {"1002345678", "Maria Lopez", 12, "Mini", "Activo"},
                    {"1019876543", "Carlos Ruiz", 16, "Juvenil", "Activo"},
                    {"1007654321", "Sofia Martinez", 10, "Mini", "Activo"},
                    {"1034567890", "Daniel Torres", 17, "Juvenil", "Inactivo"}
            });

    private final DefaultTableModel inscripcionesModel = model(
            new String[]{"Codigo", "Deportista", "Categoria", "Fecha", "Estado"},
            new Object[][]{
                    {"INS-001", "Juan Perez", "Infantil", "05/11/2026", "Activa"},
                    {"INS-002", "Maria Lopez", "Mini", "08/11/2026", "Activa"},
                    {"INS-003", "Carlos Ruiz", "Juvenil", "12/11/2026", "Activa"},
                    {"INS-004", "Sofia Martinez", "Mini", "15/11/2026", "Pendiente"}
            });

    private final DefaultTableModel pagosModel = model(
            new String[]{"Recibo", "Deportista", "Concepto", "Fecha", "Valor", "Estado"},
            new Object[][]{
                    {"PAG-001", "Juan Perez", "Mensualidad", "05/11/2026", "$120.000", "Pagado"},
                    {"PAG-002", "Maria Lopez", "Mensualidad", "08/11/2026", "$120.000", "Pagado"},
                    {"PAG-003", "Carlos Ruiz", "Inscripcion", "12/11/2026", "$80.000", "Pendiente"},
                    {"PAG-004", "Sofia Martinez", "Mensualidad", "15/11/2026", "$120.000", "Pagado"}
            });

    private final DefaultTableModel entrenadoresModel = model(
            new String[]{"Documento", "Nombre", "Especialidad", "Contacto", "Estado"},
            new Object[][]{
                    {"79876543", "Andres Gomez", "Mini e infantil", "300 123 4567", "Activo"},
                    {"80123456", "Laura Sanchez", "Juvenil", "310 555 0198", "Activo"},
                    {"79987654", "Diego Ramirez", "Preparacion fisica", "320 444 2211", "Activo"}
            });

    public VolleyGestDemo() {
        super("VOLLEYGEST | Sistema de Gestion para Escuelas de Voleibol");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setSize(1280, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);
        setLayout(new BorderLayout());
        add(buildSidebar(), BorderLayout.WEST);
        add(buildMain(), BorderLayout.CENTER);
        showPage("Inicio");
    }

    private JPanel buildSidebar() {
        JPanel side = new JPanel(new BorderLayout());
        side.setPreferredSize(new Dimension(225, 0));
        side.setBackground(NAVY);

        JPanel brand = new JPanel();
        brand.setBackground(NAVY);
        brand.setBorder(new EmptyBorder(28, 18, 26, 18));
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));
        JLabel ball = new JLabel("🏐");
        ball.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        ball.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel name = new JLabel("VOLLEYGEST");
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Segoe UI", Font.BOLD, 22));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel tagline = new JLabel("Organizacion  •  Control  •  Gestion");
        tagline.setForeground(new Color(195, 214, 235));
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);
        brand.add(ball);
        brand.add(Box.createVerticalStrut(8));
        brand.add(name);
        brand.add(Box.createVerticalStrut(5));
        brand.add(tagline);
        side.add(brand, BorderLayout.NORTH);

        JPanel menu = new JPanel();
        menu.setBackground(NAVY);
        menu.setBorder(new EmptyBorder(8, 10, 8, 10));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        String[][] items = {
                {"Inicio", "⌂"}, {"Deportistas", "♟"}, {"Inscripciones", "▦"},
                {"Pagos", "$"}, {"Entrenadores", "⚑"}, {"Reportes", "▥"}
        };
        for (String[] item : items) {
            JButton b = navButton(item[1] + "   " + item[0]);
            b.addActionListener(e -> showPage(item[0]));
            navButtons.put(item[0], b);
            menu.add(b);
            menu.add(Box.createVerticalStrut(7));
        }
        side.add(menu, BorderLayout.CENTER);

        JPanel profile = new JPanel();
        profile.setBackground(new Color(9, 33, 61));
        profile.setBorder(new EmptyBorder(17, 18, 17, 18));
        profile.setLayout(new BoxLayout(profile, BoxLayout.Y_AXIS));
        JLabel admin = new JLabel("●  Administrador");
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JLabel version = new JLabel("Prototipo demostrativo  |  v1.0");
        version.setForeground(new Color(166, 190, 216));
        version.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        profile.add(admin);
        profile.add(Box.createVerticalStrut(7));
        profile.add(version);
        side.add(profile, BorderLayout.SOUTH);
        return side;
    }

    private JButton navButton(String text) {
        JButton b = new JButton(text);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setForeground(new Color(225, 237, 249));
        b.setBackground(NAVY);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(13, 13, 13, 8));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JPanel buildMain() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BG);
        main.setBorder(new EmptyBorder(18, 22, 12, 22));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JLabel heading = new JLabel("Panel de administracion");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 23));
        heading.setForeground(TEXT);
        JLabel date = new JLabel("  " + LocalDate.now() + "    |    Sesion: Administrador  ");
        date.setForeground(MUTED);
        date.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        top.add(heading, BorderLayout.WEST);
        top.add(date, BorderLayout.EAST);
        top.setBorder(new EmptyBorder(0, 2, 15, 2));

        content.setOpaque(false);
        content.add(pages, BorderLayout.CENTER);
        pages.add(buildDashboard(), "Inicio");
        pages.add(buildTablePage("Deportistas", "Registro y consulta de deportistas", deportistasModel, "Nuevo deportista", "deportista"), "Deportistas");
        pages.add(buildTablePage("Inscripciones", "Control de inscripciones de la escuela", inscripcionesModel, "Nueva inscripcion", "inscripcion"), "Inscripciones");
        pages.add(buildTablePage("Pagos", "Registro y seguimiento de pagos", pagosModel, "Registrar pago", "pago"), "Pagos");
        pages.add(buildTablePage("Entrenadores", "Informacion del equipo tecnico", entrenadoresModel, "Nuevo entrenador", "entrenador"), "Entrenadores");
        pages.add(buildReports(), "Reportes");

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.add(top, BorderLayout.NORTH);
        center.add(content, BorderLayout.CENTER);

        JLabel status = new JLabel("●  Prototipo local activo     |     Datos de demostracion");
        status.setForeground(new Color(43, 126, 83));
        status.setFont(new Font("Segoe UI", Font.BOLD, 11));
        status.setBorder(new EmptyBorder(12, 3, 0, 0));
        center.add(status, BorderLayout.SOUTH);
        main.add(center, BorderLayout.CENTER);
        return main;
    }

    private JPanel buildDashboard() {
        JPanel p = new JPanel(new BorderLayout(0, 16));
        p.setOpaque(false);

        JPanel hero = new JPanel(new BorderLayout());
        hero.setBackground(NAVY_LIGHT);
        hero.setBorder(new EmptyBorder(24, 28, 24, 28));
        JLabel welcome = new JLabel("<html><div style='color:white;font-size:27px;font-weight:bold'>Bienvenido a VOLLEYGEST</div>"
                + "<div style='color:#D9E9FA;font-size:15px'><i>Sistema de Gestion para Escuelas de Voleibol</i></div>"
                + "<div style='color:#F5C542;font-size:12px'>Tecnologia al servicio del deporte</div></html>");
        JLabel ball = new JLabel("🏐");
        ball.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 62));
        hero.add(welcome, BorderLayout.CENTER);
        hero.add(ball, BorderLayout.EAST);

        JPanel metrics = new JPanel(new GridLayout(1, 4, 12, 0));
        metrics.setOpaque(false);
        metrics.add(metric("45", "Deportistas", BLUE, "♟"));
        metrics.add(metric("38", "Inscripciones", GREEN, "▦"));
        metrics.add(metric("$2.850.000", "Pagos registrados", ORANGE, "$"));
        metrics.add(metric("5", "Entrenadores", PURPLE, "⚑"));

        JPanel lower = new JPanel(new GridLayout(1, 2, 14, 0));
        lower.setOpaque(false);
        lower.add(summaryPanel());
        lower.add(recentPanel());

        JPanel quick = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        quick.setOpaque(false);
        quick.add(quickButton("Deportistas", BLUE, "Deportistas"));
        quick.add(quickButton("Inscripciones", GREEN, "Inscripciones"));
        quick.add(quickButton("Pagos", ORANGE, "Pagos"));
        quick.add(quickButton("Entrenadores", PURPLE, "Entrenadores"));
        quick.add(quickButton("Reportes", TEAL, "Reportes"));

        JPanel body = new JPanel();
        body.setOpaque(false);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.add(hero);
        body.add(Box.createVerticalStrut(14));
        body.add(metrics);
        body.add(Box.createVerticalStrut(14));
        body.add(lower);
        body.add(Box.createVerticalStrut(14));
        body.add(sectionTitle("Accesos rapidos"));
        body.add(Box.createVerticalStrut(8));
        body.add(quick);
        p.add(body, BorderLayout.CENTER);
        return p;
    }

    private JPanel metric(String value, String label, Color accent, String symbol) {
        JPanel card = whiteCard();
        card.setLayout(new BorderLayout(10, 4));
        card.setBorder(new CompoundBorder(new LineBorder(new Color(220, 230, 241)), new EmptyBorder(14, 15, 14, 15)));
        JLabel icon = new JLabel(symbol);
        icon.setForeground(accent);
        icon.setFont(new Font("Segoe UI", Font.BOLD, 30));
        JLabel number = new JLabel(value);
        number.setForeground(TEXT);
        number.setFont(new Font("Segoe UI", Font.BOLD, 22));
        JLabel caption = new JLabel(label);
        caption.setForeground(MUTED);
        caption.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JPanel words = new JPanel();
        words.setOpaque(false);
        words.setLayout(new BoxLayout(words, BoxLayout.Y_AXIS));
        words.add(number);
        words.add(caption);
        card.add(icon, BorderLayout.WEST);
        card.add(words, BorderLayout.CENTER);
        return card;
    }

    private JPanel summaryPanel() {
        JPanel box = whiteCard();
        box.setLayout(new BorderLayout(8, 12));
        box.add(sectionTitle("Resumen general"), BorderLayout.NORTH);
        JPanel bars = new JPanel(new GridLayout(4, 1, 5, 5));
        bars.setOpaque(false);
        bars.add(progressRow("Deportistas", 45, 50, BLUE));
        bars.add(progressRow("Inscripciones", 38, 50, GREEN));
        bars.add(progressRow("Pagos al dia", 28, 50, ORANGE));
        bars.add(progressRow("Entrenadores", 5, 10, PURPLE));
        box.add(bars, BorderLayout.CENTER);
        return box;
    }

    private JPanel progressRow(String name, int value, int max, Color color) {
        JPanel row = new JPanel(new BorderLayout(8, 3));
        row.setOpaque(false);
        JLabel label = new JLabel(name + "  (" + value + ")");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(TEXT);
        JProgressBar bar = new JProgressBar(0, max);
        bar.setValue(value);
        bar.setForeground(color);
        bar.setBackground(new Color(232, 238, 245));
        bar.setBorderPainted(false);
        row.add(label, BorderLayout.NORTH);
        row.add(bar, BorderLayout.CENTER);
        return row;
    }

    private JPanel recentPanel() {
        JPanel box = whiteCard();
        box.setLayout(new BorderLayout(8, 10));
        box.add(sectionTitle("Actividad reciente"), BorderLayout.NORTH);
        String[][] rows = {
                {"25/11", "Pago registrado", "$120.000"},
                {"24/11", "Nueva inscripcion", "Categoria Mini"},
                {"23/11", "Deportista registrado", "Nuevo ingreso"},
                {"22/11", "Datos actualizados", "Entrenador"}
        };
        String[] cols = {"Fecha", "Actividad", "Detalle"};
        JTable table = new JTable(rows, cols);
        styleTable(table);
        box.add(new JScrollPane(table), BorderLayout.CENTER);
        return box;
    }

    private JPanel buildTablePage(String title, String subtitle, DefaultTableModel data, String action, String type) {
        JPanel p = new JPanel(new BorderLayout(0, 14));
        p.setOpaque(false);
        JPanel heading = new JPanel(new BorderLayout());
        heading.setOpaque(false);
        JPanel labels = new JPanel();
        labels.setOpaque(false);
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        JLabel h = new JLabel(title);
        h.setFont(new Font("Segoe UI", Font.BOLD, 23));
        h.setForeground(TEXT);
        JLabel sub = new JLabel(subtitle);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(MUTED);
        labels.add(h);
        labels.add(Box.createVerticalStrut(4));
        labels.add(sub);
        JButton add = primaryButton("+  " + action);
        add.addActionListener(e -> addDemoRecord(type, data));
        heading.add(labels, BorderLayout.WEST);
        heading.add(add, BorderLayout.EAST);

        JPanel tableCard = whiteCard();
        tableCard.setLayout(new BorderLayout(0, 10));
        JTextField search = new JTextField();
        search.setPreferredSize(new Dimension(220, 34));
        search.setBorder(new CompoundBorder(new LineBorder(new Color(215, 225, 237)), new EmptyBorder(5, 9, 5, 9)));
        search.setToolTipText("Escriba para filtrar la tabla");
        JLabel hint = new JLabel("  Buscar en los registros");
        hint.setForeground(MUTED);
        JPanel searchRow = new JPanel(new BorderLayout(8, 0));
        searchRow.setOpaque(false);
        searchRow.add(hint, BorderLayout.WEST);
        searchRow.add(search, BorderLayout.EAST);

        JTable table = new JTable(data);
        styleTable(table);
        search.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void filter() {
                String query = search.getText().trim().toLowerCase();
                table.setRowSorter(new javax.swing.table.TableRowSorter<>(data));
                @SuppressWarnings("unchecked")
                javax.swing.table.TableRowSorter<DefaultTableModel> sorter =
                        (javax.swing.table.TableRowSorter<DefaultTableModel>) table.getRowSorter();
                sorter.setRowFilter(query.isEmpty() ? null : javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(query)));
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        });
        tableCard.add(searchRow, BorderLayout.NORTH);
        tableCard.add(new JScrollPane(table), BorderLayout.CENTER);
        JLabel count = new JLabel("Registros de ejemplo: " + data.getRowCount());
        count.setForeground(MUTED);
        count.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tableCard.add(count, BorderLayout.SOUTH);

        p.add(heading, BorderLayout.NORTH);
        p.add(tableCard, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildReports() {
        JPanel p = new JPanel(new BorderLayout(0, 15));
        p.setOpaque(false);
        p.add(sectionTitle("Reportes y estadisticas"), BorderLayout.NORTH);
        JPanel grid = new JPanel(new GridLayout(2, 2, 14, 14));
        grid.setOpaque(false);
        grid.add(reportCard("Total de deportistas", "45", "Distribuidos en categorias Mini, Infantil y Juvenil", BLUE));
        grid.add(reportCard("Inscripciones activas", "38", "Seguimiento de deportistas inscritos", GREEN));
        grid.add(reportCard("Recaudo registrado", "$2.850.000", "Consolidado de pagos de demostracion", ORANGE));
        grid.add(reportCard("Equipo tecnico", "5", "Entrenadores vinculados a la escuela", PURPLE));
        p.add(grid, BorderLayout.CENTER);
        JLabel note = new JLabel("Los indicadores son ilustrativos y se utilizan para la presentacion del prototipo.");
        note.setForeground(MUTED);
        note.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        p.add(note, BorderLayout.SOUTH);
        return p;
    }

    private JPanel reportCard(String title, String value, String detail, Color color) {
        JPanel c = whiteCard();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        JLabel t = new JLabel(title);
        t.setForeground(MUTED);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JLabel v = new JLabel(value);
        v.setForeground(color);
        v.setFont(new Font("Segoe UI", Font.BOLD, 28));
        JLabel d = new JLabel("<html><div style='width:220px'>" + detail + "</div></html>");
        d.setForeground(TEXT);
        d.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        c.add(t);
        c.add(Box.createVerticalStrut(12));
        c.add(v);
        c.add(Box.createVerticalStrut(8));
        c.add(d);
        return c;
    }

    private JPanel sectionTitle(String text) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel l = new JLabel(text);
        l.setForeground(NAVY);
        l.setFont(new Font("Segoe UI", Font.BOLD, 15));
        p.add(l, BorderLayout.WEST);
        return p;
    }

    private JPanel whiteCard() {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(new CompoundBorder(new LineBorder(new Color(222, 231, 241)), new EmptyBorder(14, 15, 14, 15)));
        return p;
    }

    private JButton primaryButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(BLUE);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(10, 15, 10, 15));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JButton quickButton(String text, Color color, String page) {
        JButton b = new JButton(text);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 11));
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(12, 13, 12, 13));
        b.addActionListener(e -> showPage(page));
        return b;
    }

    private void addDemoRecord(String type, DefaultTableModel data) {
        switch (type) {
            case "deportista" -> {
                JTextField doc = new JTextField();
                JTextField name = new JTextField();
                JTextField age = new JTextField();
                JTextField category = new JTextField("Mini");
                Object[] fields = {"Documento:", doc, "Nombre:", name, "Edad:", age, "Categoria:", category};
                if (JOptionPane.showConfirmDialog(this, fields, "Agregar deportista de demostracion",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    if (doc.getText().isBlank() || name.getText().isBlank()) {
                        message("Complete el documento y el nombre.");
                        return;
                    }
                    data.addRow(new Object[]{doc.getText(), name.getText(), age.getText(), category.getText(), "Activo"});
                }
            }
            case "inscripcion" -> {
                JTextField code = new JTextField("INS-" + String.format("%03d", data.getRowCount() + 1));
                JTextField athlete = new JTextField();
                JTextField category = new JTextField("Mini");
                Object[] fields = {"Codigo:", code, "Deportista:", athlete, "Categoria:", category};
                if (JOptionPane.showConfirmDialog(this, fields, "Nueva inscripcion de demostracion",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION && !athlete.getText().isBlank()) {
                    data.addRow(new Object[]{code.getText(), athlete.getText(), category.getText(), LocalDate.now().toString(), "Activa"});
                }
            }
            case "pago" -> {
                JTextField athlete = new JTextField();
                JTextField value = new JTextField("120000");
                JTextField concept = new JTextField("Mensualidad");
                Object[] fields = {"Deportista:", athlete, "Valor:", value, "Concepto:", concept};
                if (JOptionPane.showConfirmDialog(this, fields, "Registrar pago de demostracion",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION && !athlete.getText().isBlank()) {
                    data.addRow(new Object[]{"PAG-" + String.format("%03d", data.getRowCount() + 1),
                            athlete.getText(), concept.getText(), LocalDate.now().toString(), "$" + value.getText(), "Pagado"});
                }
            }
            case "entrenador" -> {
                JTextField doc = new JTextField();
                JTextField name = new JTextField();
                JTextField specialty = new JTextField();
                JTextField phone = new JTextField();
                Object[] fields = {"Documento:", doc, "Nombre:", name, "Especialidad:", specialty, "Contacto:", phone};
                if (JOptionPane.showConfirmDialog(this, fields, "Agregar entrenador de demostracion",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION && !name.getText().isBlank()) {
                    data.addRow(new Object[]{doc.getText(), name.getText(), specialty.getText(), phone.getText(), "Activo"});
                }
            }
        }
    }

    private void message(String text) {
        JOptionPane.showMessageDialog(this, text, "VOLLEYGEST", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showPage(String page) {
        cardLayout.show(pages, page);
        navButtons.forEach((name, button) -> {
            boolean selected = name.equals(page);
            button.setBackground(selected ? BLUE : NAVY);
            button.setForeground(Color.WHITE);
        });
    }

    private static DefaultTableModel model(String[] columns, Object[][] rows) {
        return new DefaultTableModel(rows, columns) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
    }

    private static void styleTable(JTable table) {
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setForeground(TEXT);
        table.setGridColor(new Color(231, 237, 244));
        table.setSelectionBackground(new Color(220, 235, 252));
        table.setSelectionForeground(TEXT);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(231, 239, 248));
        table.getTableHeader().setForeground(NAVY);
        table.setFillsViewportHeight(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        SwingUtilities.invokeLater(() -> new VolleyGestDemo().setVisible(true));
    }
}
