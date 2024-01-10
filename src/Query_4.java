import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;


public class Query_4 extends JFrame {
    private JTextField textFieldNomeScuderia;
    private JComboBox<String> suggerimentiScuderie;
    private Connection  connection;

    public Query_4(JFrame parent, Connection connection) {
        this.connection = connection;
        JFrame frame = new JFrame("Update Finanziamento");

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();


        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        suggerimentiScuderie = new JComboBox<>(getNomiScuderie());
        suggerimentiScuderie.setEditable(true);
        // Aggiunta della JComboBox alla finestra
        panel.add(new JLabel("Nome Scuderia:"));
        panel.add(suggerimentiScuderie);


        JButton buttonInserisci = new JButton("Aumenta di 1");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> {
            upadateFinanziamento(suggerimentiScuderie.getSelectedItem().toString());
        });
        panel.add(buttonInserisci);
        frame.getContentPane().add(panel);

        JMenuBar menuBar = new JMenuBar();

        // Creazione del menu "Help"
        JMenu infoMenu = new JMenu("Stampa");
        JMenuItem aboutMenuItem = new JMenuItem("Update");

        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaTabella();
            }
        });

        infoMenu.add(aboutMenuItem);

        // Aggiunta del menu "Help" alla barra dei menu
        menuBar.add(infoMenu);
        // Impostazione della barra dei menu per la finestra
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    private String[] getNomiScuderie() {
        List<String> nomiScuderie = new ArrayList<>();

        try {
            // Query per ottenere i nomi delle scuderie
            String query = "SELECT NomeScuderia FROM scuderia";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Aggiungi i nomi alla lista
            while (resultSet.next()) {
                nomiScuderie.add(resultSet.getString("NomeScuderia"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dei nomi delle scuderie", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return nomiScuderie.toArray(new String[0]);
    }
    private void upadateFinanziamento(String nomeUpdateFinanziamento){
        try {
            String nomeScuderia = suggerimentiScuderie.getSelectedItem() != null ?
                    suggerimentiScuderie.getSelectedItem().toString() :
                    textFieldNomeScuderia.getText().trim();

            if (nomeScuderia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire il nome della scuderia.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "UPDATE Scuderia SET NumeroFinanziamenti = NumeroFinanziamenti + 1\n" +
                    "WHERE NomeScuderia = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomeScuderia);


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Finanziamento aumentato di 1");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'update", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento della scuderia", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void visualizzaTabella() {
        try {
            // Query per selezionare tutte le scuderie
            String query = "SELECT NumeroFinanziamenti,NomeScuderia FROM scuderia";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Eseguire la query e ottenere il risultato
            ResultSet resultSet = preparedStatement.executeQuery();

            // Creare una finestra per visualizzare la tabella
            JFrame tabellaScuderieFrame = new JFrame("Tabella Equipaggio");
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


/*TODO:
   - Controllo sul pilota che viene inserito, poiche l'equipaggio non deve contenere solamente piloti GentlemanDriver.
     Quindi fare un controllo sulla scuderia che ha quel equipaggio non solamente composto da piloti AM = GentlemanDriver
   - Fare delle modifiche
*  */
