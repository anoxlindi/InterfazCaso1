
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
import java.util.Vector;
import views.GuestListView;

public class GuestListView extends JPanel implements View {

    private JTable table;
    private DefaultTableModel model;
    private SchedulerIO schedulerIO;

    public GuestListView(SchedulerIO schedulerIO) {
        this.schedulerIO = schedulerIO;
        setLayout(new BorderLayout());

        String[] columnas = {"Nombre", "Celular", "Género", "Nacimiento", "Dirección", "Acepta Términos"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        updateTable();
    }

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

    @Override
    public void update(Model model, Object data) {}
}
    

