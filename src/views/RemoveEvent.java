package views;

import core.Model;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
import models.SchedulerIO;
import core.View;

public class RemoveEvent extends JPanel implements View  { // JPanel es de Swing
//añadimos el botoncillo actualizar y lo nombramos al inicio
    private JTable table; // se crea la tabla
    private JButton btnCancelar, btnRemover, btnSeleccionarTodos , btnActualizar;
    private DefaultTableModel model; // ahora es atributo
    private Vector<Vector<Object>> eventos; // eventos cargados desde el archivo
    private SchedulerIO schedulerIO; // modelo para leer y guardar eventos

    public RemoveEvent(SchedulerIO schedulerIO) {
        this.schedulerIO = schedulerIO;
        setLayout(new BorderLayout());

        // columnas de la tabla
        String[] columnas = {"Date", "Description", "Frequency", "E-mail", "Alarm", "Boolean"};
         // modelo para que salga checkbox en boolean
        model = new DefaultTableModel(columnas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // solo la columna "Boolean" es editable
            }
        };
        table = new JTable(model); // se crea la tabla con el modelo

        // scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(table);

        // botones
        btnCancelar = new JButton("Cancel");
        btnRemover = new JButton("Remover");
        btnSeleccionarTodos = new JButton("Seleccionar Todos");
        //AÑADIMOS ACTUALIZAR YA QUE LA INFO QUE AÑADIMOS SE AGREGA EN REMOVE SOLO
        //CERRAMOS EL PROGRAMA Y NO DEBERÍA DE SER ASÍ
        btnActualizar = new JButton("Actualizar");
     
        JPanel panelBotones = new JPanel(); // panel para los botones
        panelBotones.add(btnCancelar);
        panelBotones.add(btnRemover);
        panelBotones.add(btnSeleccionarTodos);
        panelBotones.add (btnActualizar);

        // añadimos todo al panel
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // === CARGAR EVENTOS ===
            updateTable ();
        // === ACCIONES ===

        // acción para remover fila
        btnRemover.addActionListener(e -> {
            int fila = table.getSelectedRow();
            if (fila != -1) {
                eventos.remove(fila);         // elimina del vector
                model.removeRow(fila);        // elimina de la tabla
                try {
                    schedulerIO.overwriteEvents(eventos); // guarda cambios en archivo
                    updateTable ();
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
         // actualizar tabla manualmente
        btnActualizar.addActionListener(e -> updateTable());
    }
    //deberia ir arriba pero por espacio y me dio pereza
    public void updateTable() {
        try {
            eventos = schedulerIO.getEvents();
            model.setRowCount(0); // limpia la tabla

            for (Vector<Object> evento : eventos) {
                Object[] fila = {
                    evento.get(0), evento.get(1), evento.get(2), evento.get(3), evento.get(4), false
                };
                model.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar eventos");
        }
    }


    @Override
    public void update(Model model, Object data) {
    }
}
