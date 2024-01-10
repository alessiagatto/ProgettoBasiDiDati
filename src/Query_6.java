import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;


public class Query_6 extends JFrame {
    private JTextField puntiTextField;
    private JTextField posizioneTextField;
    private JTextField numeroGaraTextField;


    private JComboBox<String> suggerimentiVettura;
    private Connection  connection;

    public Query_6(JFrame parent, Connection connection) {
        this.connection = connection;
        JFrame frame = new JFrame("Registrazione di un Pilota");

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();


        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        suggerimentiVettura = new JComboBox<>(getNomiVettura());
        suggerimentiVettura.setEditable(true);
        // Aggiunta della JComboBox alla finestra
        panel.add(new JLabel("Numero della vettura:"));
        panel.add(suggerimentiVettura);
        JLabel labelPunti = new JLabel("Punti:");
        posizioneTextField = new JTextField(15);
        JLabel labelPosizione = new JLabel("Posizione:");
        puntiTextField = new JTextField(15);

        panel.add(labelPunti);
        panel.add(posizioneTextField);
        panel.add(labelPosizione);
        panel.add(puntiTextField);



        JButton buttonInserisci = new JButton("Inserisci");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> {
            upadateFinanziamento(suggerimentiVettura.getSelectedItem().toString());
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

    private String[] getNomiVettura() {
        List<String> nomiScuderie = new ArrayList<>();

        try {
            // Query per ottenere i nomi delle scuderie
            String query = "SELECT NumeroGara FROM vettura";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Aggiungi i nomi alla lista
            while (resultSet.next()) {
                nomiScuderie.add(resultSet.getString("NumeroGara"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dei nomi delle vetture", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return nomiScuderie.toArray(new String[0]);
    }
    private void upadateFinanziamento(String nomeUpdateFinanziamento){
        try {
            String nomeVettura = suggerimentiVettura.getSelectedItem() != null ?
                    suggerimentiVettura.getSelectedItem().toString() :
                    numeroGaraTextField.getText().trim();
            int punti = Integer.parseInt(puntiTextField.getText());
            int posizione = Integer.parseInt(posizioneTextField.getText());

            if (nomeVettura.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire il numero della vettura.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "UPDATE Vettura SET Punti = ? , Posizione = ?\n" +
                    "WHERE NumeroGara = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(3, nomeVettura);
            preparedStatement.setInt(1, punti);
            preparedStatement.setInt(2, posizione);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Aggiornamento eseguito con successo.");
            } else {
                JOptionPane.showMessageDialog(this, "Nessuna vettura trovata con i dati specificati.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento dei punti", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void visualizzaTabella() {
        try {
            // Query per selezionare tutte le scuderie
            String query = "SELECT NumeroGara,Modello,Posizione,Punti FROM vettura";
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
