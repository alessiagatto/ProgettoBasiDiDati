import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;



public class Query_11 extends JFrame {
    private Connection  connection;

    public Query_11(JFrame parent, Connection connection) {
        this.connection = connection;
        try {
            String query = "SELECT\n" +
                    "    S.NomeScuderia,\n" +
                    "    COUNT(CASE WHEN P.Tipo = true THEN 1 END) AS NumeroGentlemanDriver,\n" +
                    "    COUNT(*) AS NumeroTotaleMembri,\n" +
                    "    ROUND(COUNT(CASE WHEN P.Tipo = true THEN 1 END) * 100 / COUNT(*), 2) AS PercentualeGentlemanDriver\n" +
                    "FROM Scuderia S\n" +
                    "JOIN Equipaggio E ON S.NomeScuderia = E.Scuderia\n" +
                    "JOIN (\n" +
                    "    SELECT Codice, Tipo, Equipaggio\n" +
                    "    FROM PilotaAM\n" +
                    "    UNION ALL\n" +
                    "    SELECT Codice, NULL AS Tipo, Equipaggio\n" +
                    "    FROM PilotaPRO\n" +
                    ") P ON E.IDEquipaggio = P.Equipaggio\n" +
                    "GROUP BY S.NomeScuderia;";
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



