import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class Query_1 extends JFrame {

    private final JTextField textFieldNomeScuderia, textFieldSedePrincipale;
    private final Connection  connection;

    public Query_1(JFrame parent, Connection connection) {
        super("Registrazione Scuderia");
        setSize(300, 200);
        setVisible(true);
        setLocationRelativeTo(parent);
        setLayout(new FlowLayout());
        
        JMenuBar menuBar = new JMenuBar();

        // Creazione del menu "Help"
        JMenu infoMenu = new JMenu("Stampa");
        JMenuItem aboutMenuItem = new JMenuItem("Scuderia");

        // Ricevi la connessione al database dal parametro
        this.connection = connection;

        // Creazione dei componenti della finestra
        JLabel labelNomeScuderia = new JLabel("Nome Scuderia:");
        textFieldNomeScuderia = new JTextField(15);

        JLabel labelSedePrincipale = new JLabel("Sede Principale:");
        textFieldSedePrincipale = new JTextField(15);

        JButton buttonInserisci = new JButton("Inserisci");

        // Aggiunta dei componenti alla finestra
        add(labelNomeScuderia);
        add(textFieldNomeScuderia);
        add(labelSedePrincipale);
        add(textFieldSedePrincipale);
        add(buttonInserisci);

        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> insertScuderia());

        aboutMenuItem.addActionListener(e -> visualizzaTabellaScuderie());

        infoMenu.add(aboutMenuItem);

        // Aggiunta del menu "Help" alla barra dei menu
        menuBar.add(infoMenu);

        // Impostazione della barra dei menu per la finestra
        setJMenuBar(menuBar);
    }

    private void insertScuderia() {
        try {
            String nomeScuderia = textFieldNomeScuderia.getText();
            String sedePrincipale = textFieldSedePrincipale.getText();


            String query = "INSERT INTO scuderia (NomeScuderia, SedePrincipale) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomeScuderia);
            preparedStatement.setString(2, sedePrincipale);


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Scuderia inserita con successo");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento della scuderia", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento della scuderia", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void visualizzaTabellaScuderie() {
        try {
            // Query per selezionare tutte le scuderie
            String query = "SELECT * FROM scuderia";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Eseguire la query e ottenere il risultato
            ResultSet resultSet = preparedStatement.executeQuery();

            // Creare una finestra per visualizzare la tabella
            JFrame tabellaScuderieFrame = new JFrame("Tabella Scuderie");
            tabellaScuderieFrame.setSize(500, 300);
            tabellaScuderieFrame.setLocationRelativeTo(this);
            tabellaScuderieFrame.setLayout(new BorderLayout());

            // Creare un JTable per visualizzare i dati della tabella
            JTable table = new JTable(buildTableModel(resultSet));
            JScrollPane scrollPane = new JScrollPane(table);

            // Aggiungere il JTable al frame
            tabellaScuderieFrame.add(scrollPane, BorderLayout.CENTER);

            // Mostrare la finestra
            tabellaScuderieFrame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante la visualizzazione della tabella", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo di utilità per costruire un TableModel da un ResultSet
    private DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        // Nome delle colonne
        int columnCount = metaData.getColumnCount();
        Vector<String> columnNames = new Vector<>();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Dati della tabella
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(resultSet.getObject(columnIndex));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }
}
