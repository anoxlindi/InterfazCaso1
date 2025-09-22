package views;

import core.Model;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import models.SchedulerIO;
import core.View;
public class RemoveEvent extends JPanel implements View  { // JPanel es de Swing

    private JTable table; // se crea la tabla
    private JButton btnCancelar, btnRemover, btnSeleccionarTodos;
    private DefaultTableModel model; // ahora es atributo
    private Vector<Vector<Object>> eventos; // eventos cargados desde el archivo
    private SchedulerIO schedulerIO; // modelo para leer y guardar eventos

    public RemoveEvent(SchedulerIO schedulerIO) {
        this.schedulerIO = schedulerIO;
        setLayout(new BorderLayout());

        // columnas de la tabla
        String[] columnas = {"Date", "Description", "Frequency", "E-mail", "Alarm", "Boolean"};
        model = new DefaultTableModel(columnas, 0); // modelo vacío
        table = new JTable(model); // se crea la tabla con el modelo

        // scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(table);

        // botones
        btnCancelar = new JButton("Cancel");
        btnRemover = new JButton("Remover");
        btnSeleccionarTodos = new JButton("Seleccionar Todos");

        JPanel panelBotones = new JPanel(); // panel para los botones
        panelBotones.add(btnCancelar);
        panelBotones.add(btnRemover);
        panelBotones.add(btnSeleccionarTodos);

        // añadimos todo al panel
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // === CARGAR EVENTOS ===
        try {
            eventos = schedulerIO.getEvents(); // obtenemos eventos del archivo
            for (Vector<Object> evento : eventos) {
                Object[] fila = {
                    evento.get(0), evento.get(1), evento.get(2), evento.get(3), evento.get(4), false // checkbox
                };
                model.addRow(fila); // añadimos fila a la tabla
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar eventos");
            eventos = new Vector<>();
        }

        // === ACCIONES ===

        // acción para remover fila
        btnRemover.addActionListener(e -> {
            int fila = table.getSelectedRow();
            if (fila != -1) {
                eventos.remove(fila);         // elimina del vector
                model.removeRow(fila);        // elimina de la tabla
                try {
                    schedulerIO.overwriteEvents(eventos); // guarda cambios en archivo
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar cambios");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una fila para remover");
            }
        });

        // acción para seleccionar todos
        btnSeleccionarTodos.addActionListener(e -> {
            table.selectAll(); // selecciona todas las filas
        });

        // acción para cancelar
        btnCancelar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Operación cancelada");
        });
    }

    @Override
    public void update(Model model, Object data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
