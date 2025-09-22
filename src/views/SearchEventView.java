package views;

// cramos nuestra clase en view para buscar eventoss
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class SearchEventView extends JPanel {
    public SearchEventView() {
        setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new GridLayout(0, 2));
        panelBusqueda.add(new JLabel("Descripción:"));
        JTextField txtDescripcion = new JTextField();
        panelBusqueda.add(txtDescripcion);

        panelBusqueda.add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField();
        panelBusqueda.add(txtEmail);

        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // Tabla de resultados
        String[] columnas = {"Fecha", "Descripción", "Frecuencia", "Email", "Alarma"};
        JTable tabla = new JTable(new DefaultTableModel(columnas, 0));
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
