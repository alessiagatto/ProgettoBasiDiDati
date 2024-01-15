import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.MatteBorder;





public class Query_2 extends JFrame {

    private  JTextField textFieldNumeroGara, textFieldNomeScuderia, textFieldMotivoRitiro, textFieldModello
    ,textFieldPunti, textFieldPosizione, nomeGara;

    private JTextField textFieldRagioneSociale, textFieldNome, textFieldSede;

    private JTextField textFieldCp, textFieldCostruttore, textFieldTipo, textFieldCosto,
    textFieldCilindrata, textFieldDataInsta, textFieldNcilindri, textFieldNumMarce, textFieldPeso, textFieldMate,
    textFieldTipoMot, textFieldNumCompo, textFieldVettura;
    private Connection  connection;

    private JComboBox<String> suggerimentiScuderie,suggerimentiCostruttore,suggerimentiVettura,suggerimentiGara;
    private Object dateTextField;


    public Query_2(JFrame parent, Connection connection) {
        // Ricevi la connessione al database dal parametro
        this.connection = connection;
        JFrame frame = new JFrame("Registrazione Di Una Vettura");

        frame.setSize(600, 500);
        frame.setLocationRelativeTo(this);



        // Creazione di un pannello
        JPanel panel = new JPanel();

        frame.setLayout(new GridLayout(0,1));

        // Impostazione del TitledBorder al pannello

        MatteBorder shadowBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setLayout(new GridLayout(0,1));



        // Creazione dei componenti della finestra
        JLabel labelNumeroVettura = new JLabel("Numero della Vettura:");
        textFieldNumeroGara = new JTextField(15);
        suggerimentiScuderie = new JComboBox<>(getNomiScuderie());
        suggerimentiScuderie.setEditable(true);
        // Aggiunta della JComboBox alla finestra
        panel.add(new JLabel("Nome Scuderia:"));
        panel.add(suggerimentiScuderie);
        JLabel labelMotivoRitiro = new JLabel("Motivo Ritiro:");
        textFieldMotivoRitiro = new JTextField(15);
        JLabel labelModello = new JLabel("Modello Vettura:");
        textFieldModello = new JTextField(15);
        JLabel labelPunti = new JLabel("Punti Vettura:");
        textFieldPunti = new JTextField(15);
        JLabel labelPosizione = new JLabel("Posizione Vettura:");
        textFieldPosizione = new JTextField(15);

        suggerimentiGara = new JComboBox<>(getNomiGara());
        suggerimentiGara.setEditable(true);

        panel.add(new JLabel("Gara:"));
        panel.add(suggerimentiGara);



        JButton buttonInserisci = new JButton("Inserisci");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInserisci.addActionListener((event) -> {
            insertVettura(suggerimentiScuderie.getSelectedItem().toString(),suggerimentiGara.getSelectedItem().toString());
        });
        panel.add(buttonInserisci);

        // Aggiunta dei componenti alla finestra
        panel.add(labelNumeroVettura);
        panel.add(textFieldNumeroGara);
        panel.add(labelMotivoRitiro);
        panel.add(textFieldMotivoRitiro);
        panel.add(labelModello);
        panel.add(textFieldModello);
        panel.add(labelPunti);
        panel.add(textFieldPunti);
        panel.add(labelPosizione);
        panel.add(textFieldPosizione);
        panel.add(buttonInserisci);


        JPanel panel_c = new JPanel();

        JLabel labelRagioneSociale = new JLabel("Ragione Sociale Costruttore:");
        textFieldRagioneSociale = new JTextField(15);
        JLabel labelNome = new JLabel("Nome Costruttore:");
        textFieldNome = new JTextField(15);
        JLabel labelSede = new JLabel("Sede Costruttore:");
        textFieldSede = new JTextField(15);


        panel_c.add(labelRagioneSociale);
        panel_c.add(textFieldRagioneSociale);
        panel_c.add(labelNome);
        panel_c.add(textFieldNome);
        panel_c.add(labelSede);
        panel_c.add(textFieldSede);

        JButton buttonIn = new JButton("OK");
        // Aggiunta dell'evento al bottone di inserimento
        buttonIn.addActionListener((event) -> {
            insertCostruttore();
        });
        panel_c.add(buttonIn);

        panel_c.setLayout(new GridLayout(0,1));
        panel_c.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));



        JPanel panel_v = new JPanel();





        // Creazione dei componenti della finestra
        JLabel labelCodiceComponente = new JLabel("Codice Componente:");
        textFieldCp = new JTextField(5);

        suggerimentiCostruttore = new JComboBox<>(getCostruttore());
        suggerimentiCostruttore.setEditable(true);
        // Aggiunta della JComboBox alla finestra
        panel_v.add(new JLabel("Nome Costruttore:"));
        panel_v.add(suggerimentiCostruttore);

        JLabel labelTipoComponente = new JLabel("Tipo Componente:");
        textFieldTipo = new JTextField(15);
        JLabel labelCosto = new JLabel("Costo:");
        textFieldCosto = new JTextField(15);
        JLabel labelCilindrata = new JLabel("Cilindrata:");
        textFieldCilindrata = new JTextField(15);
        JLabel data = new JLabel("data:");
        textFieldDataInsta = new JTextField(15);
        JLabel labelNumCilindri = new JLabel("Numero Cilindri:");
        textFieldNcilindri = new JTextField(15);
        JLabel labelNumMarce = new JLabel("Numero Marce:");
        textFieldNumMarce = new JTextField(15);
        JLabel labelPeso = new JLabel("Peso:");
        textFieldPeso = new JTextField(15);
        JLabel labelMateriale = new JLabel("Materiale:");
        textFieldMate = new JTextField(15);
        JLabel labelTipoMot = new JLabel("TipoMotore:");
        textFieldTipoMot = new JTextField(15);
        JLabel labelNumCompo = new JLabel("Numero Componenti:");
        textFieldNumCompo = new JTextField(15);
        suggerimentiVettura = new JComboBox<>(getVettura());
        suggerimentiVettura.setEditable(true);
        // Aggiunta della JComboBox alla finestra
        panel_v.add(new JLabel("Numero Gara della Vettura:"));
        panel_v.add(suggerimentiVettura);


        panel_v.add(labelCodiceComponente);
        panel_v.add(textFieldCp);
        panel_v.add(labelTipoComponente);
        panel_v.add(textFieldTipo);
        panel_v.add(labelCosto);
        panel_v.add(textFieldCosto);
        panel_v.add(labelCilindrata);
        panel_v.add(textFieldCilindrata);
        panel_v.add(data);
        panel_v.add(textFieldDataInsta);
        panel_v.add(labelNumCilindri);
        panel_v.add(textFieldNcilindri);
        panel_v.add(labelNumMarce);
        panel_v.add(textFieldNumMarce);
        panel_v.add(labelPeso);
        panel_v.add(textFieldPeso);
        panel_v.add(labelMateriale);
        panel_v.add(textFieldMate);
        panel_v.add(labelTipoMot);
        panel_v.add(textFieldTipoMot);
        panel_v.add(labelNumCompo);
        panel_v.add(textFieldNumCompo);

        JButton buttonInp = new JButton("Inserisci");
        // Aggiunta dell'evento al bottone di inserimento
        buttonInp.addActionListener((event) -> {
            insertComponente(suggerimentiCostruttore.getSelectedItem().toString(), suggerimentiVettura.getSelectedItem().toString());
        });
        panel_v.add(buttonInp);


        panel_v.setLayout(new GridLayout(0,1));
        panel_v.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));


        frame.getContentPane().add(panel);
        frame.getContentPane().add(panel_c);
        frame.getContentPane().add(panel_v);
        frame.setVisible(true);

        JMenuBar menuBar = new JMenuBar();

        // Creazione del menu "Help"
        JMenu infoMenu = new JMenu("Registra");
        JMenuItem aboutMenuItem = new JMenuItem("Gara/Circuito");

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


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Aggiungi i pannelli al contentPanel
        contentPanel.add(panel);
        contentPanel.add(panel_c);
        contentPanel.add(panel_v);

        // Crea uno JScrollPane intorno al contentPanel
        JScrollPane scrollPane = new JScrollPane(contentPanel);

        // Aggiungi lo JScrollPane al JFrame
        frame.add(scrollPane, BorderLayout.EAST);

    }

    private String[] getCostruttore() {
        List<String> nomiCostruttore = new ArrayList<>();

        try {
            // Query per ottenere i nomi delle vetture
            String query = "SELECT RagioneSociale FROM costruttore";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Aggiungi i nomi alla lista
            while (resultSet.next()) {
                nomiCostruttore.add(resultSet.getString("RagioneSociale"));
            }

            // Chiudi le risorse
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante il recupero dei nomi del costruttore", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return nomiCostruttore.toArray(new String[0]);
    }



    private void updateVetturaComboBox() {
        // Ottieni i nomi delle vetture dalla query
        String[] vetturaNames = getVettura();

        // Aggiorna il modello della JComboBox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vetturaNames);
        suggerimentiVettura.setModel(model);


        // Ottieni i nomi delle vetture dalla query
        String[] costruttoreNames = getCostruttore();

        // Aggiorna il modello della JComboBox
        DefaultComboBoxModel<String> models = new DefaultComboBoxModel<>(costruttoreNames);
        suggerimentiCostruttore.setModel(models);
    }


    private String[] getNomiGara() {
        List<String> nomiVetture = new ArrayList<>();

        try {
            // Query per ottenere i nomi delle vetture
            String query = "SELECT NomeGara FROM Gara";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Aggiungi i nomi alla lista
            while (resultSet.next()) {
                nomiVetture.add(resultSet.getString("NomeGara"));
            }

            // Chiudi le risorse
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante il recupero dei nomi delle gare", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return nomiVetture.toArray(new String[0]);
    }

    private String[] getVettura() {
        List<String> nomiVetture = new ArrayList<>();

        try {
            // Query per ottenere i nomi delle vetture
            String query = "SELECT NumeroGara FROM vettura";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Aggiungi i nomi alla lista
            while (resultSet.next()) {
                nomiVetture.add(resultSet.getString("NumeroGara"));
            }

            // Chiudi le risorse
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante il recupero dei nomi delle vetture", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return nomiVetture.toArray(new String[0]);
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


    private void insertVettura(String nomeScuderiaSuggerito, String nomeGaraNum) {
        try {
            int numeroGara = Integer.parseInt(textFieldNumeroGara.getText());
            String nomeScuderia = suggerimentiScuderie.getSelectedItem() != null ?
                    suggerimentiScuderie.getSelectedItem().toString() :
                    textFieldNomeScuderia.getText();
            String motivoRitiro = textFieldMotivoRitiro.getText().trim();
            String modello = textFieldModello.getText();
            int punti = Integer.parseInt(textFieldPunti.getText());
            int posizione = Integer.parseInt(textFieldPosizione.getText());

            String gara = suggerimentiGara.getSelectedItem() != null ?
                    suggerimentiGara.getSelectedItem().toString() :
                    nomeGara.getText();

            motivoRitiro = motivoRitiro.isEmpty() ? null : motivoRitiro;

            String query = "INSERT INTO Vettura (NumeroGara, NomeScuderia, MotivoRitiro, Modello, Punti, Posizione,Gara) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numeroGara);
            preparedStatement.setString(2, nomeScuderia);
            if (motivoRitiro == null) {
                preparedStatement.setNull(3, Types.VARCHAR); // Se NumeroFinanziamenti è di tipo VARCHAR
            } else {
                preparedStatement.setString(3, motivoRitiro);
            }
            preparedStatement.setString(4, modello);
            preparedStatement.setInt(5, punti);
            preparedStatement.setInt(6, posizione);
            preparedStatement.setString(7, gara);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Vettura inserita con successo");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento della vettura", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento della scuderia", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertCostruttore(){
        try {

            String ragioneSociale = textFieldRagioneSociale.getText();
            String nome = textFieldNome.getText();
            String sede = textFieldSede.getText();

            String query = "INSERT INTO Costruttore (RagioneSociale, Nome, Sede) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ragioneSociale);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, sede);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Costruttore inserito con successo");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del costruttore", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del costruttore", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertComponente(String nomeCostruttore, String nomeVettura ){
        try {

            int codiceComp = Integer.parseInt(textFieldCp.getText());
            String costruttore = suggerimentiCostruttore.getSelectedItem() != null ?
                    suggerimentiCostruttore.getSelectedItem().toString() :
                    textFieldCostruttore.getText();
            String tipo = textFieldTipo.getText();
            int costo = Integer.parseInt(textFieldCosto.getText());
            int cilindrata = Integer.parseInt(textFieldCilindrata.getText());
            String dateString = textFieldDataInsta.getText();
            // Converti la data in un oggetto Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);

            int numCilindri = Integer.parseInt(textFieldNcilindri.getText());
            int numMarce = Integer.parseInt(textFieldNumMarce.getText());
            int peso = Integer.parseInt(textFieldPeso.getText());
            String materiale = textFieldMate.getText();
            String tipoMotore = textFieldTipoMot.getText();
            int numComponenti = Integer.parseInt(textFieldNumCompo.getText());
            int vettura = Integer.parseInt(suggerimentiVettura.getSelectedItem() != null ?
                    suggerimentiVettura.getSelectedItem().toString() :
                    textFieldVettura.getText());




            String query = "INSERT INTO Componente (CodiceComponente, Costruttore, Tipo, Costo, Cilindrata, DataInstallazione, NumeroCilindri, NumeroMarce, Peso, Materiale, TipoMotore, NumeroComponenti, Vettura) VALUES\n" +
                    "    (?,?,?,?,?,?,?,?,?,?,?,?,?);\n";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, codiceComp);
            preparedStatement.setString(2, costruttore);
            preparedStatement.setString(3, tipo);
            preparedStatement.setInt(4, costo);
            preparedStatement.setInt(5, cilindrata);
            preparedStatement.setDate(6, sqlDate);  // Imposta la data
            preparedStatement.setInt(7, numCilindri);
            preparedStatement.setInt(8, numMarce);
            preparedStatement.setInt(9, peso);
            preparedStatement.setString(10, materiale);
            preparedStatement.setString(11, tipoMotore);
            preparedStatement.setInt(12, numComponenti);
            preparedStatement.setInt(13, vettura);

            if (isComponenteAlreadyMounted(nomeVettura, tipo)) {
                JOptionPane.showMessageDialog(this, "Impossibile montare due componenti dello stesso tipo sulla stessa vettura", "Errore", JOptionPane.ERROR_MESSAGE);
                return;  // Esce dal metodo senza eseguire l'inserimento
            }


            int rowsAffected = preparedStatement.executeUpdate();



            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Componente inserito con successo");
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del componente", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del componente", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }


    private boolean isComponenteAlreadyMounted(String nomeVettura, String tipo ) throws SQLException {
        // Query per verificare se esiste già un componente con lo stesso tipo montato sulla stessa vettura
        String query = "SELECT COUNT(*) FROM Componente WHERE Vettura = ? AND Tipo = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(nomeVettura));
            preparedStatement.setString(2, tipo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;  // Restituisce true se esiste già un componente, altrimenti false
                }
            }
        }catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento del adadadad", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        return false;  // In caso di errore restituisce false
    }

    private void visualizzaTabella() {
        Query_5 ok = new Query_5(this,connection);
    }


}
