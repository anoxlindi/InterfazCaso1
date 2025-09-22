package views;

// cramos nuestra clase en view para buscar eventoss
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SearchEventView extends JPanel {
    // aaa no busca el cosito, tenemos que añadir atributos me olvidé
        private JTextField txtDescripcion;
        private JTextField txtEmail;
        private JButton btnBuscar;
        private JTable tabla;
        private DefaultTableModel modeloTabla;  
   
        public SearchEventView() {
        setLayout(new BorderLayout());

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new GridLayout(0, 2));
        panelBusqueda.add(new JLabel("Descripción:"));
            txtDescripcion = new JTextField(); // usar el atributo
            panelBusqueda.add(txtDescripcion);

            panelBusqueda.add(new JLabel("Email:"));
            txtEmail = new JTextField(); //  usar el atributo
            panelBusqueda.add(txtEmail);

            btnBuscar = new JButton("Buscar"); // usar el atributo
            panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // Tabla de resultados
        String[] columnas = {"Fecha", "Descripción", "Frecuencia", "Email", "Alarma"};
            modeloTabla = new DefaultTableModel(columnas, 0); // usar el atributo
            tabla = new JTable(modeloTabla); // usar el atributo
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        
            // Conectar el botón con el método buscarEventos
            btnBuscar.addActionListener(e -> buscarEventos());
        }
        // metodos para acceder desde el contro.
            public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public String getDescripcionBuscada() {
        return txtDescripcion.getText().trim();
    }

    public String getEmailBuscado() {
        return txtEmail.getText().trim();
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
    
    private void buscarEventos() {
    try {
        modeloTabla.setRowCount(0); // limpiar tabla

        String descripcionBuscada = txtDescripcion.getText().trim().toLowerCase();
        String emailBuscado = txtEmail.getText().trim().toLowerCase();

        File file = new File("data/events.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "No se encontró el archivo de eventos.");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length < 5) continue;

            String fecha = partes[0];
            String descripcion = partes[1];
            String frecuencia = partes[2];
            String email = partes[3];
            String alarma = partes[4];

            boolean coincideDescripcion = descripcion.toLowerCase().contains(descripcionBuscada);
            boolean coincideEmail = email.toLowerCase().contains(emailBuscado);

            if (coincideDescripcion || coincideEmail) {
                modeloTabla.addRow(new Object[]{fecha, descripcion, frecuencia, email, alarma});
            }
        }
        reader.close();

        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron eventos que coincidan.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al buscar eventos: " + e.getMessage());
    }
}

    
}
    
