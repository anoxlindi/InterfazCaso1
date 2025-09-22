package views;
//jala del view why?, para poner la info osea se crea el coso de la ventana y se obtiene
//el cual lo recibe controller y este le pasa al modelo
// osea de aca muestra el formulario y recoge datos nada mas
import controllers.RegisterGuestController;
import javax.swing.*;
import java.awt.*;

public class RegisterGuestView extends JPanel {
    private RegisterGuestController controller;

    // Campos del formulario
    private JTextField campoNombre, campoCelular, campoDireccion;
    private JRadioButton radioMasculino, radioFemenino;
    private JComboBox<String> comboDia, comboMes, comboAnio;
    private JCheckBox checkTerminos;
    private JButton btnRegistrar;

    public RegisterGuestView(RegisterGuestController controller) {
        this.controller = controller;
        setLayout(new GridLayout(0, 2, 10, 10));

        // Nombre
        add(new JLabel("Ingrese Nombre:"));
        campoNombre = new JTextField();
        add(campoNombre);

        // Celular
        add(new JLabel("Ingrese número celular:"));
        campoCelular = new JTextField();
        add(campoCelular);

        // Género
        add(new JLabel("Género:"));
        JPanel panelGenero = new JPanel();
        radioMasculino = new JRadioButton("Masculino");
        radioFemenino = new JRadioButton("Femenino");
        ButtonGroup grupoGenero = new ButtonGroup();
        grupoGenero.add(radioMasculino);
        grupoGenero.add(radioFemenino);
        panelGenero.add(radioMasculino);
        panelGenero.add(radioFemenino);
        add(panelGenero);

        // Fecha de nacimiento
        add(new JLabel("Fecha de Nacimiento:"));
        JPanel panelFecha = new JPanel();
        comboDia = new JComboBox<>();
        comboMes = new JComboBox<>();
        comboAnio = new JComboBox<>();
        for (int i = 1; i <= 31; i++) comboDia.addItem(String.valueOf(i));
        for (int i = 1; i <= 12; i++) comboMes.addItem(String.valueOf(i));
        for (int i = 2025; i >= 1900; i--) comboAnio.addItem(String.valueOf(i));
        panelFecha.add(comboDia);
        panelFecha.add(comboMes);
        panelFecha.add(comboAnio);
        add(panelFecha);

        // Dirección
        add(new JLabel("Dirección:"));
        campoDireccion = new JTextField();
        add(campoDireccion);

        // Términos
        add(new JLabel("Acepta Términos y Condiciones:"));
        checkTerminos = new JCheckBox();
        add(checkTerminos);

        // Botón registrar
        btnRegistrar = new JButton("Registrar");
        add(new JLabel()); // espacio vacío
        add(btnRegistrar);

        // Acción del botón
        btnRegistrar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String celular = campoCelular.getText();
            String genero = radioMasculino.isSelected() ? "Masculino" : "Femenino";
            String fecha = comboDia.getSelectedItem() + "/" + comboMes.getSelectedItem() + "/" + comboAnio.getSelectedItem();
            String direccion = campoDireccion.getText();
            boolean acepta = checkTerminos.isSelected();

            controller.saveGuest(nombre, celular, genero, fecha, direccion, acepta);
        });
    }

    public void showSuccess(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void showError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
