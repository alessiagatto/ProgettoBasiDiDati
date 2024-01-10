import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;



public class Query_10 extends JFrame {
    private Connection  connection;

    public Query_10(JFrame parent, Connection connection) {
        this.connection = connection;
        try {
            String query = "SELECT E.Nome, E.Cognome,C.NomeCircuito, C.Paese AS NazionalitaCircuito\n" +
                    "FROM Equipaggio E\n" +
                    "         JOIN Scuderia S ON S.NomeScuderia= E.Scuderia\n" +
                    "         JOIN Vettura V ON S.NomeScuderia = V.NomeScuderia\n" +
                    "         JOIN Gara G ON V.Gara = G.NomeGara\n" +
                    "         JOIN Circuito C ON G.Circuito = C.NomeCircuito\n" +
                    "WHERE E.Nazionalita = C.Paese AND V.Posizione=1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Eseguire la query e ottenere il risultato
            ResultSet resultSet = preparedStatement.executeQuery();

            // Creare una finestra per visualizzare la tabella
            JFrame tabellaScuderieFrame = new JFrame("Tabella");
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



