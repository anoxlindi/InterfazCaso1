package controllers;

import core.Controller;
import views.EventListView;
import views.HomeView;
import views.NewEventView;
import views.RemoveEvent;
import controllers.RemoveEventController;
import views.RegisterGuestView ; // se importa de la vista para que vaya al homeview
import models.SchedulerIO;



/**
 * Main controller. It will be responsible for program's main screen behavior.
 */
public class HomeController extends Controller 
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private HomeView homeView;
        private SchedulerIO schedulerIO = new SchedulerIO();
	private EventListController eventListController = new EventListController();
	private NewEventController newEventController = new NewEventController(eventListController);
	// no se como explicarlo, pero todo es una cadena, osea si dice en uno
        // tiene que estar en el otro, todo se conecta, like spaiderman
        private RemoveEventController removeEventController = new RemoveEventController(schedulerIO);
        private RegisterGuestController registerGuestController = new RegisterGuestController(schedulerIO);


	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void run()
	{
		// Initializes others controllers
		eventListController.run();
		newEventController.run();
		removeEventController.run();

		// Initializes HomeView
		homeView = new HomeView(this, mainFrame);
		addView("HomeView", homeView);
		
		// Displays the program window
		mainFrame.setVisible(true);
                
	}
	
	
	//-----------------------------------------------------------------------
	//		Getters
	//-----------------------------------------------------------------------
	public EventListView getEventListView()
	{
		return eventListController.getView();
	}
	
	public NewEventView getNewEventView()
	{
		return newEventController.getView();
        }
        // se añadio del remove
        public RemoveEvent getRemoveEventView()
        {
                 return removeEventController.getView();
}
        // se añade del registrar
        public RegisterGuestView getRegisterGuestView() {
                 return registerGuestController.getView();
}

}

	
