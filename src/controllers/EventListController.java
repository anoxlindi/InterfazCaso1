package controllers;

import views.EventListView;

public class EventListController {

    // Vista asociada a este controlador
    private EventListView view;

    // Constructor
    public EventListController() {
        this.view = new EventListView();  // Se crea la vista al instanciar el controlador
    }

    // Método para iniciar el controlador y mostrar la vista
    public void run() {
        view.setVisible(true);  // Muestra la ventana
    }

    // Retorna la vista asociada
    public EventListView getView() {
        return view;
    }

    // Agrega una nueva fila en la vista (ejemplo genérico)
    public void addNewRow(Object[] metadata) {
        view.addRow(metadata);  // Llama al método de la vista para agregar la fila
    }}
