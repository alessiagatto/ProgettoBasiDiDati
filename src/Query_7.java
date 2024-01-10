import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;


public class Query_7 extends JFrame {
    private JTextField textFieldNumeroGara, textFieldNomeScuderia, textFieldCodiceComponente;
    private JComboBox<String> suggerimentiVettura;
    private Connection  connection;

    public Query_7(JFrame parent, Connection connection) {
        this.connection = connection;
        JFrame frame = new JFrame("Verifica installazione componente");

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();


        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        suggerimentiVettura = new JComboBox<>(getNomiVettura());
        suggerimentiVettura.setEditable(true);
        // Aggiunta della JComboBox alla finestra
        panel.add(new JLabel("Numero della vettura:"));
        panel.add(suggerimentiVettura);
        JLabel labelCodiceComponente = new JLabel("Codice del componente");
        textFieldCodiceComponente = new JTextField(15);

        panel.add(labelCodiceComponente);
        panel.add(textFieldCodiceComponente);


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
                    textFieldNumeroGara.getText().trim();

            int codiceComponente = Integer.parseInt(textFieldCodiceComponente.getText());



            if (nomeVettura.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire il numero della vettura.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT COUNT(*) AS 'ComponenteMontato'\n" +
                    "FROM Componente C\n" +
                    "WHERE C.CodiceComponente = ?\n" +
                    "  AND C.Vettura = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomeVettura);
            preparedStatement.setInt(2, codiceComponente);


            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int componentePresente = resultSet.getInt(1);

                if (componentePresente == 1) {
                    JOptionPane.showMessageDialog(this, "Il componente è presente sulla vettura.");
                } else {
                    JOptionPane.showMessageDialog(this, "Il componente non è presente sulla vettura.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Errore durante la verifica del componente sulla vettura.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante la verifica", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void visualizzaTabella() {
        try {
            // Query per selezionare tutte le scuderie
            String query = "SELECT\n" +
                    "    V.NumeroGara,\n" +
                    "    V.NomeScuderia,\n" +
                    "    C.CodiceComponente,\n" +
                    "    C.Costruttore,\n" +
                    "    C.Tipo,\n" +
                    "    C.Costo,\n" +
                    "    C.Cilindrata,\n" +
                    "    C.DataInstallazione,\n" +
                    "    C.NumeroCilindri,\n" +
                    "    C.NumeroMarce,\n" +
                    "    C.Peso,\n" +
                    "    C.Materiale,\n" +
                    "    C.TipoMotore,\n" +
                    "    C.NumeroComponenti\n" +
                    "FROM\n" +
                    "    Vettura V\n" +
                    "JOIN\n" +
                    "    Componente C ON V.NumeroGara = C.Vettura\n" +
                    "ORDER BY\n" +
                    "    V.NumeroGara, V.NomeScuderia, C.CodiceComponente;";
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




