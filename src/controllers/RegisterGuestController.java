package controllers;
// este toma los datos de la vista y los pasa directamente al modelo
import models.Guest;
import models.SchedulerIO;
import views.RegisterGuestView;

public class RegisterGuestController {
    private SchedulerIO schedulerIO;
    private RegisterGuestView view;

    public RegisterGuestController(SchedulerIO schedulerIO) {
        this.schedulerIO = schedulerIO;
        this.view = new RegisterGuestView(this);
    }

    public void saveGuest(String nombre, String celular, String genero, String fechaNacimiento, String direccion, boolean acepta) {
        Guest guest = new Guest(nombre, celular, genero, fechaNacimiento, direccion, acepta);
        try {
            schedulerIO.saveGuest(guest);
            view.showSuccess("Invitado registrado correctamente");
        } catch (Exception e) {
            view.showError("Error al registrar invitado");
        }
    }

    public RegisterGuestView getView() {
        return view;
    }
}
