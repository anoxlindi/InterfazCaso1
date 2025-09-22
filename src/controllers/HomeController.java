package controllers;

import core.Controller;
import views.EventListView;
import views.HomeView;
import views.NewEventView;
import views.RemoveEvent;
import controllers.RemoveEventController;
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
	private RemoveEventController removeEventController = new RemoveEventController(schedulerIO);


	
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
        public RemoveEvent getRemoveEventView()
        {
                 return removeEventController.getView();
}

	}
