import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;


public class Query_3 extends JFrame {

    private JTextField textFieldIDEquipaggio, textFieldNome, textFieldCognome, textFieldNumeroLicenza,
            textFieldNumeroMembro, textFieldDataNascita, textFieldNazionalita, textFieldScuderia;

    private JTextField textFieldCodice, textFieldTipo, textFieldDataPrimaLicenza, textFieldQuotaFinanziaria;
    private JTextField textFieldNumLicenzePossedute;
    private JCheckBox checkBoxGentlemanDriver;
    private JComboBox<String> comboBoxTipoPilota;
    private JPanel panelTipoPilotaAM, panelTipoPilotaPRO;
    private JComboBox<String> suggerimentiScuderie, suggerimentiEquipaggio;
    private Connection  connection;

    public Query_3(JFrame parent, Connection connection) {
        this.connection = connection;
        JFrame frame = new JFrame("Registrazione di un Pilota");

        frame.setSize(600, 900);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();


        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setLayout(new GridLayout(0,1));

        JLabel labelIDEquipaggio = new JLabel("ID Equipaggio:");
        textFieldIDEquipaggio = new JTextField(15);
        JLabel labelNome = new JLabel("Nome:");
        textFieldNome = new JTextField(15);
        JLabel labelCognome = new JLabel("Cognome:");
        textFieldCognome = new JTextField(15);
        JLabel labelNumLicenza = new JLabel("Numero Licenza:");
        textFieldNumeroLicenza= new JTextField(15);
        JLabel labelNumMembro = new JLabel("NumeroMembro:");
        textFieldNumeroMembro = new JTextField(15);
        JLabel labelDataNascita = new JLabel("Data di Nascita:");
        textFieldDataNascita = new JTextField(15);
        JLabel labelNazionalita = new JLabel("Nazionalita:");
        textFieldNazionalita = new JTextField(15);

        suggerimentiScuderie = new JComboBox<>(getNomiScuderie());
        suggerimentiScuderie.setEditable(true);
        panel.add(new JLabel("Nome Scuderia:"));
        panel.add(suggerimentiScuderie);

        panel.add(labelIDEquipaggio);
        panel.add(textFieldIDEquipaggio);
        panel.add(labelNome);
        panel.add(textFieldNome);
        panel.add(labelCognome);
        panel.add(textFieldCognome);
        panel.add(labelNumLicenza);
        panel.add(textFieldNumeroLicenza);
        panel.add(labelNumMembro);
        panel.add(textFieldNumeroMembro);
        panel.add(labelDataNascita);
        panel.add(textFieldDataNascita);
        panel.add(labelNazionalita);
        panel.add(textFieldNazionalita);






        JPanel panel_c = new JPanel();


        JButton refreshButton = new JButton("Refresh");
        panel_c.add(refreshButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel_c);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Esegui l'aggiornamento della JComboBox
                updateVetturaComboBox();
            }
        });

        // Aggiungi il JComboBox al tuo frame
        comboBoxTipoPilota = new JComboBox<>(new String[]{"AM", "PRO"});
        panel_c.add(new JLabel("Tipo Pilota:"));
        panel_c.add(comboBoxTipoPilota);


        // Aggiungi i pannelli specifici del tipo di pilota al frame, ma inizialmente li nascondi
        panelTipoPilotaAM = createPanelTipoPilotaAM();
        panelTipoPilotaPRO = createPanelTipoPilotaPRO();

        panel_c.add(panelTipoPilotaAM);
        panelTipoPilotaAM.setVisible(false);

        panel_c.add(panelTipoPilotaPRO);
        panelTipoPilotaPRO.setVisible(false);









        // Imposta l'azione per il JComboBox per gestire la visibilità dei pannelli
        comboBoxTipoPilota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTipoPilota = comboBoxTipoPilota.getSelectedItem().toString();
                panelTipoPilotaAM.setVisible("AM".equals(selectedTipoPilota));
                panelTipoPilotaPRO.setVisible("PRO".equals(selectedTipoPilota));
            }
        });


        JButton buttonInserisci = new JButton("Inserisci");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> {
            insertEqui(suggerimentiScuderie.getSelectedItem().toString());
        });
        panel.add(buttonInserisci);
        panel.add(panel_c);
        frame.getContentPane().add(panel);

        frame.setVisible(true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Aggiungi i pannelli al contentPanel
        contentPanel.add(panel_c);

        JScrollPane scrollPane = new JScrollPane(contentPanel);

        // Aggiungi lo JScrollPane al JFrame
        frame.add(scrollPane, BorderLayout.EAST);
    }

    private JPanel createPanelTipoPilotaAM() {
        JPanel panel = new JPanel(new GridLayout(0, 1));


        JLabel labelCodice = new JLabel("Codice Pilota:");
        textFieldCodice = new JTextField(15);
        checkBoxGentlemanDriver = new JCheckBox("Gentleman Driver");
        JLabel labelDataLicenza = new JLabel("Data della prima licenza:");
        textFieldDataPrimaLicenza = new JTextField(15);
        JLabel labelQuota = new JLabel("Quota che mette in caso di si:");
        textFieldQuotaFinanziaria = new JTextField(15);

        suggerimentiEquipaggio = new JComboBox<>(getNomiEquipaggio());
        suggerimentiEquipaggio.setEditable(true);
        panel.add(new JLabel("ID Equipaggio:"));
        panel.add(suggerimentiEquipaggio);

        JButton buttonInserisci = new JButton("Inserisci");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> {
            insertPilota(suggerimentiEquipaggio.getSelectedItem().toString());
        });
        panel.add(buttonInserisci);

        panel.add(labelCodice);
        panel.add(textFieldCodice);
        panel.add(checkBoxGentlemanDriver);
        panel.add(labelDataLicenza);
        panel.add(textFieldDataPrimaLicenza);
        panel.add(labelQuota);
        panel.add(textFieldQuotaFinanziaria);






        return panel;
    }

    private JPanel createPanelTipoPilotaPRO() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JLabel labelCodice = new JLabel("Codice Pilota:");
        textFieldCodice = new JTextField(15);
        JLabel labelNumLicenzePossedute = new JLabel("Numero Licenze Possedute:");
        textFieldNumLicenzePossedute = new JTextField(15);
        suggerimentiEquipaggio = new JComboBox<>(getNomiEquipaggio());
        suggerimentiEquipaggio.setEditable(true);
        panel.add(new JLabel("ID Equipaggio:"));
        panel.add(suggerimentiEquipaggio);

        JButton buttonInserisci = new JButton("Inserisci");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> {
            insertPilota(suggerimentiEquipaggio.getSelectedItem().toString());
        });
        panel.add(buttonInserisci);

        panel.add(labelCodice);
        panel.add(textFieldCodice);
        panel.add(labelNumLicenzePossedute);
        panel.add(textFieldNumLicenzePossedute);

        return panel;
    }

    private void updateVetturaComboBox() {
        // Ottieni i nomi delle vetture dalla query
        String[] vetturaNames = getNomiEquipaggio();

        // Aggiorna il modello della JComboBox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vetturaNames);
        suggerimentiEquipaggio.setModel(model);
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


    private String[] getNomiEquipaggio() {
        List<String> nomiPiloti = new ArrayList<>();

        try {
            // Query per ottenere i nomi delle scuderie
            String query = "SELECT IDEquipaggio FROM equipaggio";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Aggiungi i nomi alla lista
            while (resultSet.next()) {
                nomiPiloti.add(resultSet.getString("IDEquipaggio"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dei nomi dei Piloti", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return nomiPiloti.toArray(new String[0]);
    }

    private void insertEqui(String nomeScuderiaSuggerito){
        try {
            int IDEquipaggio = Integer.parseInt(textFieldIDEquipaggio.getText());
            String nome = textFieldNome.getText();
            String cognome = textFieldCognome.getText();
            int numeroLicenza = Integer.parseInt(textFieldNumeroLicenza.getText());
            int numeroMembro = Integer.parseInt(textFieldNumeroMembro.getText());
            String dateString = textFieldDataNascita.getText();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
            String nazionalita = textFieldNazionalita.getText();
            String scuderia = suggerimentiScuderie.getSelectedItem() != null ?
                    suggerimentiScuderie.getSelectedItem().toString() :
                    textFieldScuderia.getText();




            String query = "INSERT INTO Equipaggio (IDEquipaggio, Nome, Cognome, NumeroLicenza, NumeroMembro, DataNascita, Nazionalita, Scuderia) VALUES\n" +
                    "    (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, IDEquipaggio);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);
            preparedStatement.setInt(4, numeroLicenza);
            preparedStatement.setInt(5, numeroMembro);
            preparedStatement.setDate(6, sqlDate);
            preparedStatement.setString(7, nazionalita);
            preparedStatement.setString(8, scuderia);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Equipaggio inserito con successo");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del Equipaggio", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | NumberFormatException e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del Equipaggio", "Errore", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void insertPilota(String nomeSuggerimentiEquipaggio){

        String tipoPilota = comboBoxTipoPilota.getSelectedItem().toString();
        boolean isGentlemanDriver = checkBoxGentlemanDriver.isSelected();

        System.out.println("Tipo selezionato: " + comboBoxTipoPilota.getSelectedItem().toString());
        try{
        if ("AM".equals(tipoPilota)) {
            if(isGentlemanDriver){
                isGentlemanDriver = true;
            }
            System.out.println("AM selected");
            int codice = Integer.parseInt(textFieldCodice.getText());
            String dateString = textFieldDataPrimaLicenza.getText();
            java.sql.Date sqlDate2 = java.sql.Date.valueOf(dateString);
            String quotaFinanziaria = textFieldQuotaFinanziaria.getText().trim();
            String equipaggio = suggerimentiEquipaggio.getSelectedItem() != null ?
                    suggerimentiEquipaggio.getSelectedItem().toString() :
                    textFieldIDEquipaggio.getText();





            String query2 = "INSERT INTO PilotaAM (Codice, Tipo, Data_1a_Licenza, QuotaFinanziaria, Equipaggio) VALUES\n" +
                    "    (?,?,?,?,?)";
            PreparedStatement preparedStatement_1 = connection.prepareStatement(query2);
            preparedStatement_1.setInt(1,codice);
            preparedStatement_1.setBoolean(2,isGentlemanDriver);
            preparedStatement_1.setDate(3,sqlDate2);
            quotaFinanziaria = quotaFinanziaria.isEmpty() ? null : quotaFinanziaria;
            if (quotaFinanziaria == null) {
                preparedStatement_1.setNull(4, Types.VARCHAR); // Se NumeroFinanziamenti è di tipo VARCHAR
            } else {
                preparedStatement_1.setString(4, quotaFinanziaria);
            }
            preparedStatement_1.setString(5,equipaggio);

            int rowsAffectedPilotaAM = preparedStatement_1.executeUpdate();

            if (rowsAffectedPilotaAM > 0) {
                JOptionPane.showMessageDialog(this, "Dati del pilota AM inseriti con successo");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento dei dati del pilota AM", "Errore", JOptionPane.ERROR_MESSAGE);
            }


            panelTipoPilotaAM.setVisible(true);
            panelTipoPilotaPRO.setVisible(false);


        } else if ("PRO".equals(tipoPilota)) {
            int codice = Integer.parseInt(textFieldCodice.getText());
            int numLicenzePossedute = Integer.parseInt(textFieldNumLicenzePossedute.getText());
            String equipaggio = suggerimentiEquipaggio.getSelectedItem() != null ?
                    suggerimentiEquipaggio.getSelectedItem().toString() :
                    textFieldIDEquipaggio.getText();

            String query2 = "INSERT INTO PilotaPRO (Codice, NumLicenzePossedute, Equipaggio) VALUES\n" +
                    "    (?,?,?)";
            PreparedStatement preparedStatement_1 = connection.prepareStatement(query2);
            preparedStatement_1.setInt(1,codice);
            preparedStatement_1.setInt(2,numLicenzePossedute);
            preparedStatement_1.setString(3,equipaggio);

            int rowsAffectedPilotaPRO = preparedStatement_1.executeUpdate();

            if (rowsAffectedPilotaPRO > 0) {
                JOptionPane.showMessageDialog(this, "Dati del pilota PRO inseriti con successo");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento dei dati del pilota PRO", "Errore", JOptionPane.ERROR_MESSAGE);
            }


            panelTipoPilotaAM.setVisible(false);
            panelTipoPilotaPRO.setVisible(true);
        }
            }catch (SQLException | NumberFormatException e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del Equipaggio", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void visualizzaTabella() {
        try {
            // Query per selezionare tutte le scuderie
            String query = "SELECT * FROM Equipaggio";
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
