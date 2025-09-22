
package views;
// ya que se creó registrar invitado, osea donde lo voy a ver
//al invitado su lista de invitados, por ello se crea su propia lista para que
// este en orde, PRIMERO se pone en vista creamos
import core.Model;
import core.View;
import models.SchedulerIO ; //se guarda la info en scheduleIO y lo trae

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;
import views.GuestListView;

public class GuestListView extends JPanel implements View {
//atributos
    private JTable table;
    private DefaultTableModel model;
    private SchedulerIO schedulerIO;
    
//constructor
    public GuestListView(SchedulerIO schedulerIO) {
        this.schedulerIO = schedulerIO;
        setLayout(new BorderLayout());
        
        //1. creamos la tabliña
        String[] columnas = {"Nombre", "Celular", "Género", "Nacimiento", "Dirección", "Acepta Términos"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // 2. Botón Eliminar (arriba)
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        btnEliminar.addActionListener(e -> eliminarInvitado());
        btnEliminar.setBackground(new Color(255, 182, 193)); // rosa claro
        
        // 3. Botón Actualizar (abajo)
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> updateTable());
        btnActualizar.setBackground(new Color(255, 192, 203)); // otro tono de rosa
        //3.5 panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        add(panelBotones, BorderLayout.NORTH);
        
        //4. cargar la tabla
        updateTable();
    }
    //metodo para refresh o actualizar a la tabla
    public void updateTable() {
        try {
            Vector<Vector<Object>> invitados = schedulerIO.getGuests();
            model.setRowCount(0);
            for (Vector<Object> fila : invitados) {
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar invitados");
        }
    }
    // Método para eliminar invitado en tabla
    private void eliminarInvitado() {
    int filaSeleccionada = table.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un invitado para eliminar");
        return;
    }

    // Confirmación antes de eliminar
    String nombre = model.getValueAt(filaSeleccionada, 0).toString();
    int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar a " + nombre + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;

    // Eliminar del modelo
    model.removeRow(filaSeleccionada);

    // Guardar el nuevo contenido del modelo en el archivo
    try {
        File file = new File("data/guests.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs(); // crea la carpeta si no existe
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < model.getRowCount(); i++) {
            StringBuilder linea = new StringBuilder();
            for (int j = 0; j < model.getColumnCount(); j++) {
                linea.append(model.getValueAt(i, j).toString());
                if (j < model.getColumnCount() - 1) linea.append(";");
            }
            writer.write(linea.toString());
            writer.newLine();
        }
        writer.close();

        JOptionPane.showMessageDialog(this, "Invitado eliminado correctamente");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al guardar cambios: " + e.getMessage());
    }
}  
    @Override
    public void update(Model model, Object data) {}
}
