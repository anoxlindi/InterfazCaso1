package views;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.EventListController;
import core.Model;
import core.View;

/**
 * View responsible for the list of events.
 */
@SuppressWarnings("serial")
public class EventListView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //      Attributes
    //-----------------------------------------------------------------------
    private EventListController eventListController;
    private JTable table;
    private DefaultTableModel tableModel;

    //-----------------------------------------------------------------------
    //      Constructors
    //-----------------------------------------------------------------------
    /**
     * It will show the list of saved events.
     * 
     * @param eventListController Controller responsible for this view
     * @param table JTable with saved events
     */
    public EventListView(EventListController eventListController, JTable table)
    {
        this.eventListController = eventListController;
        this.table = table;
        make_frame();
    }

    /**
     * Constructor por defecto (si no se pasa JTable).
     * Se crea un JTable vacío con columnas básicas.
     */
    public EventListView() {
        this.tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Fecha"}, 0);
        this.table = new JTable(tableModel);
        make_frame();
    }

    //-----------------------------------------------------------------------
    //      Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data) 
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }

    /**
     * Creates view's frame.
     */
    private void make_frame()
    {
        setLayout(new BorderLayout()); // MUY IMPORTANTE para BorderLayout.CENTER
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Método para agregar una fila a la tabla desde el controlador.
     */
    public void addRow(Object[] metadata) {
        if (table.getModel() instanceof DefaultTableModel) {
            ((DefaultTableModel) table.getModel()).addRow(metadata);
        }
    }
}
