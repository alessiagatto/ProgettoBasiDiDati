import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class PaginaInizialeApp extends JFrame {

    private Connection connection;


    /* Inserire credenziali per loggare nel db */
    private String url = "jdbc:mysql://localhost:3306/campionato";
    private String user = "root";
    private String password = "root";


    public PaginaInizialeApp() {
        JFrame frame = new JFrame("Registrazione Di Una Vettura");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);



        Color customColor2 = new Color(79, 111, 82);
        Color frameColor = new Color(210, 227, 200);

        frame.setBackground(customColor2);


        JPanel panel = new JPanel();
        JLabel labelUrl = new JLabel("Database : Campionato");
        JLabel Admin = new JLabel("Utente :   " + user + "\n");

        panel.add(labelUrl);
        panel.add(Admin);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setLayout(new GridLayout(0,3));


        // Connessione al database
        connectToDatabase();

        // Creazione dei bottoni per ogni query
        JButton Query_1 = new JButton("Record Scuderia");
        JButton Query_2 = new JButton("Record Vettura");
        JButton Query_3 = new JButton("Rec Pilota");
        JButton Query_4 = new JButton("Rec Finanziamento");
        JButton Query_6 = new JButton("Rec risultato vettura-gara");
        JButton Query_7 = new JButton("Verifica componente");
        JButton Query_8 = new JButton("Somma totale finanziamenti");
        JButton Query_9 = new JButton("Stampa annuale scuderie");
        JButton Query_10 = new JButton("Circuito Casa");
        JButton Query_11 = new JButton("Percentuale Gentleman");
        JButton Query_12 = new JButton("Stampa mensile costruttore");
        JButton Query_13 = new JButton("Stampa classifica");
        JButton Query_14 = new JButton("Stampa classifica in base motore");
        JButton Query_15 = new JButton("Stampa report");

        Query_1.setBorder(new LineBorder(customColor2, 1));
        Query_2.setBorder(new LineBorder(customColor2, 1));
        Query_3.setBorder(new LineBorder(customColor2, 1));
        Query_4.setBorder(new LineBorder(customColor2, 1));

        Query_6.setBorder(new LineBorder(customColor2, 1));
        Query_7.setBorder(new LineBorder(customColor2, 1));
        Query_8.setBorder(new LineBorder(customColor2, 1));
        Query_9.setBorder(new LineBorder(customColor2, 1));
        Query_10.setBorder(new LineBorder(customColor2, 1));
        Query_11.setBorder(new LineBorder(customColor2, 1));
        Query_12.setBorder(new LineBorder(customColor2, 1));
        Query_13.setBorder(new LineBorder(customColor2, 1));
        Query_14.setBorder(new LineBorder(customColor2, 1));
        Query_15.setBorder(new LineBorder(customColor2, 1));

        // Aggiunta dei bottoni al frame
        panel.add(Query_1).setBackground(frameColor);
        panel.add(Query_2).setBackground(frameColor);
        panel.add(Query_3).setBackground(frameColor);
        panel.add(Query_4).setBackground(frameColor);

        panel.add(Query_6).setBackground(frameColor);
        panel.add(Query_7).setBackground(frameColor);
        panel.add(Query_8).setBackground(frameColor);
        panel.add(Query_9).setBackground(frameColor);
        panel.add(Query_10).setBackground(frameColor);
        panel.add(Query_11).setBackground(frameColor);
        panel.add(Query_12).setBackground(frameColor);
        panel.add(Query_13).setBackground(frameColor);
        panel.add(Query_14).setBackground(frameColor);
        panel.add(Query_15).setBackground(frameColor);

        // Aggiunta degli eventi ai bottoni
        Query_1.addActionListener((event) -> {WindowQuery_1();});
        Query_2.addActionListener((event) -> {WindowQuery_2();});
        Query_3.addActionListener((event) -> {WindowQuery_3();});
        Query_4.addActionListener((event) -> {WindowQuery_4();});

        Query_6.addActionListener((event) -> {WindowQuery_6();});
        Query_7.addActionListener((event) -> {WindowQuery_7();});
        Query_8.addActionListener((event) -> {WindowQuery_8();});
        Query_9.addActionListener((event) -> {WindowQuery_9();});
        Query_10.addActionListener((event) -> {WindowQuery_10();});
        Query_11.addActionListener((event) -> {WindowQuery_11();});
        Query_12.addActionListener((event) -> {WindowQuery_12();});
        Query_13.addActionListener((event) -> {WindowQuery_13();});
        Query_14.addActionListener((event) -> {WindowQuery_14();});
        Query_15.addActionListener((event) -> {WindowQuery_15();});

        JMenuBar menuBar = new JMenuBar();

        // Creazione del menu "Help"
        JMenu infoMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("Riconoscimenti");

        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });

        infoMenu.add(aboutMenuItem);

        // Aggiunta del menu "Help" alla barra dei menu
        menuBar.add(infoMenu);
        // Impostazione della barra dei menu per la finestra
        frame.setJMenuBar(menuBar);

        ImageIcon icon = new ImageIcon("src/icon/db-icon.png");
        frame.setIconImage(icon.getImage());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // Imposta l'icona anche per la finestra appena aperta
                ((JFrame)e.getSource()).setIconImage(icon.getImage());
            }
        });

        frame.getContentPane().add(panel);
    }

    private void help(){
        JOptionPane.showMessageDialog(null, "Creatori del software:\t\nNormanno Iari\t\n" +
                        "Gatto Alessia\nData: [2023/24]",
                "Credenziali", JOptionPane.INFORMATION_MESSAGE);
    }


    private void connectToDatabase() {
        try {

            String url = this.url;
            String user = this.user;
            String password = this.password;

            // Carica il driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crea la connessione al database
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante la connessione al database", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void WindowQuery_1() {
        Query_1 ok = new Query_1(this,connection);

    }
    private void WindowQuery_2() {
        Query_2 ok = new Query_2(this,connection);

    }
    private void WindowQuery_3() {
        Query_3 ok = new Query_3(this,connection);
    }
    private void WindowQuery_4() {
        Query_4 ok = new Query_4(this,connection);
    }


    private void WindowQuery_6() {
        Query_6 ok = new Query_6(this,connection);

    }

    private void WindowQuery_7() {
        Query_7 ok = new Query_7(this,connection);

    }
    private void WindowQuery_8() {
        Query_8 ok = new Query_8(this,connection);

    }
    private void WindowQuery_9() {
        Query_9 ok = new Query_9(this,connection);
    }
    private void WindowQuery_10() {
        Query_10 ok = new Query_10(this,connection);

    }
    private void WindowQuery_11() {
        Query_11 ok = new Query_11(this,connection);
    }
    private void WindowQuery_12() {
        Query_12 ok = new Query_12(this,connection);

    }
    private void WindowQuery_13() {
        Query_13 ok = new Query_13(this,connection);
    }
    private void WindowQuery_14() {
        Query_14 ok = new Query_14(this,connection);

    }
    private void WindowQuery_15() {
        Query_15 ok = new Query_15(this,connection);

    }


    private static void setUIFont(Font font) {
        UIManager.put("Button.font", font);
        UIManager.put("ToggleButton.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("ColorChooser.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("List.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("CheckBoxMenuItem.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("PopupMenu.font", font);
        UIManager.put("OptionPane.font", font);
        UIManager.put("Panel.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("ScrollPane.font", font);
        UIManager.put("Viewport.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextPane.font", font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ToolBar.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("Tree.font", font);
    }

    public static void main(String[] args) {
        JLabel label = new JLabel("Hello, Swing!");
        Font systemFont = label.getFont();
        setUIFont(new Font(systemFont.getName() ,Font.BOLD, 14));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaginaInizialeApp();
            }
        });
    }
}
