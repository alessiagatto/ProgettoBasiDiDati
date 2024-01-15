import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Query_5 extends JFrame {
    private JTextField nomeCircuito, paese, lunghezza, numeroCurve;

    private JTextField nomeGara, data, tipoGara, durata, circuitoNome;
    private JComboBox<String> suggerimentiCircuito;
    private Connection  connection;

    public Query_5(JFrame parent, Connection connection) {
        this.connection = connection;
        JFrame frame = new JFrame("Rec Circuito/Gara");

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);
        frame.setLayout(new GridLayout(0,1));


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));


        JLabel labelNomeCircuito = new JLabel("Nome Circuito:");
        nomeCircuito = new JTextField(15);
        JLabel labelPaese = new JLabel("Paese:");
        paese = new JTextField(15);
        JLabel labelLunghezza= new JLabel("Lunghezza:");
        lunghezza = new JTextField(15);
        JLabel labelNumeroCurve = new JLabel("Numero Curve:");
        numeroCurve = new JTextField(15);


        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));




        JButton buttonInserisci = new JButton("Inserisci Circuito");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> {
            insertCircuito();
        });


        panel.add(labelNomeCircuito);
        panel.add(nomeCircuito);
        panel.add(labelPaese);
        panel.add(paese);
        panel.add(labelLunghezza);
        panel.add(lunghezza);
        panel.add(labelNumeroCurve);
        panel.add(numeroCurve);
        panel.add(buttonInserisci);


        JPanel panel_c = new JPanel();
        panel_c.setLayout(new GridLayout(0,1));

        panel_c.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));



        JLabel labelNomeGara = new JLabel("Nome Gara:");
        nomeGara = new JTextField(15);
        JLabel labelData = new JLabel("Data:");
        data = new JTextField(15);
        JLabel labelTipoGara = new JLabel("Tipo Gara:");
        tipoGara = new JTextField(15);
        JLabel labelDurata = new JLabel("Durata:");
        durata = new JTextField(15);

        suggerimentiCircuito = new JComboBox<>(getNomiCircuito());
        suggerimentiCircuito.setEditable(true);
        JButton refreshButton = new JButton("Refresh");
        panel_c.add(refreshButton);

        panel_c.add(labelNomeGara);
        panel_c.add(nomeGara);
        panel_c.add(labelData);
        panel_c.add(data);
        panel_c.add(labelTipoGara);
        panel_c.add(tipoGara);
        panel_c.add(labelDurata);
        panel_c.add(durata);
        // Aggiunta della JComboBox alla finestra
        panel_c.add(new JLabel("Circuito:"));
        panel_c.add(suggerimentiCircuito);

        JButton button = new JButton("Inserisci Gara");
        // Aggiunta dell'evento al bottone di inserimento
        button.addActionListener((event) -> {
            insertGara(suggerimentiCircuito.getSelectedItem().toString());
        });
        panel_c.add(button);



        frame.getContentPane().add(BorderLayout.CENTER, panel_c);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Esegui l'aggiornamento della JComboBox
                updateCircuito();
            }
        });

        frame.getContentPane().add(panel);
        frame.getContentPane().add(panel_c);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Aggiungi i pannelli al contentPanel
        contentPanel.add(panel);
        contentPanel.add(panel_c);


        // Crea uno JScrollPane intorno al contentPanel
        JScrollPane scrollPane = new JScrollPane(contentPanel);

        // Aggiungi lo JScrollPane al JFrame
        frame.add(scrollPane, BorderLayout.EAST);




        frame.setVisible(true);
    }

    private void updateCircuito() {
        // Ottieni i nomi delle vetture dalla query
        String[] vetturaNames = getNomiCircuito();

        // Aggiorna il modello della JComboBox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vetturaNames);
        suggerimentiCircuito.setModel(model);
    }
    private String[] getNomiCircuito() {
        List<String> nomiScuderie = new ArrayList<>();

        try {
            // Query per ottenere i nomi delle scuderie
            String query = "SELECT NomeCircuito FROM circuito";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Aggiungi i nomi alla lista
            while (resultSet.next()) {
                nomiScuderie.add(resultSet.getString("NomeCircuito"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dei nomi dei circuiti", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return nomiScuderie.toArray(new String[0]);
    }
    private void insertCircuito(){
        try {
            String circuito = nomeCircuito.getText();
            String pa = paese.getText();
            int lengh = Integer.parseInt(lunghezza.getText());
            int curve = Integer.parseInt(numeroCurve.getText());

            if (circuito.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire il nome del circuito.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO Circuito (NomeCircuito, Paese, Lunghezza, NumeroCurve) VALUES\n" +
                    "    (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, circuito);
            preparedStatement.setString(2, pa);
            preparedStatement.setInt(3, lengh);
            preparedStatement.setInt(4, curve);



            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Inserimento andato a buon fine!");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del circuito", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertGara(String nomeGaraS){
        try {
            String gara = nomeGara.getText();
            String dateString = data.getText();
            // Converti la data in un oggetto Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
            String tipoGaraS = tipoGara.getText();
            int durataS = Integer.parseInt(durata.getText());

            String circuito = suggerimentiCircuito.getSelectedItem() != null ?
                    suggerimentiCircuito.getSelectedItem().toString() :
                    circuitoNome.getText();




            if (gara.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire il nome della gara.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO Gara (NomeGara, Data, TipoGara, Durata, Circuito) VALUES\n" +
                    "    (?,?,?,?,?)";


            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if (tipoGaraS == null) {
                preparedStatement.setNull(3, Types.VARCHAR);
            } else {
                preparedStatement.setString(3, tipoGaraS);
            }

            preparedStatement.setString(1, gara);
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3,tipoGaraS );
            preparedStatement.setInt(4, durataS);
            preparedStatement.setString(5, circuito);



            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Inserimento andato a buon fine!");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del circuito", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

}

