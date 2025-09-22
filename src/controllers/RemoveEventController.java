package controllers;

import core.Controller;
import core.View;
import models.SchedulerIO;
import views.RemoveEvent;

public class RemoveEventController extends Controller {
    private RemoveEvent view;

    public RemoveEventController(SchedulerIO schedulerIO) {
        this.view = new RemoveEvent(schedulerIO);
    }

    @Override
    public void run() {
        addView("RemoveEvent", (View) view); // agrega la vista al sistema
        loadView("RemoveEvent");      // la muestra en pantalla
    }

    public RemoveEvent getView() {
        return view;
    }
}
