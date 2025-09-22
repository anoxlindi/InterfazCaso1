package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import core.Model;
import core.View;


/**
 * Responsible for reading / writing events saved.
 */
public class SchedulerIO implements Model
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private static final String DIRECTORY = ".";
	private static final String FILE = "events.txt";
	private List<View> views = new ArrayList<>();
	private String notice;

	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void attach(View view) 
	{
		views.add(view);
	}

	@Override
	public void detach(View view) 
	{
		views.remove(view);
	}

	@Override
	public void notifyViews() 
	{
		for (View v : views) {
			v.update(this, notice);
		}
	}
	
	/**
	 * Saves a {@link SchedulerEvent} in disk in {@link #DIRECTORY}.{@link #FILE}.
	 * 
	 * @param event {@link SchedulerEvent Event} to be saved
	 * @throws Exception If it can't save the event
	 */
	public void saveEvent(SchedulerEvent event) throws Exception 
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), true));
			writer.write(event.toString(), 0, event.toString().length());
			writer.newLine();
                        writer.flush();
			writer.close();
		} catch (FileNotFoundException fnfe) {
			notice = "File not found"; 
			notifyViews();
		} catch (Exception ex) {
			notice = "Error while writing the file";
			notifyViews();
		}
	}

        // aca tenemos geyEvents , obitne el event
	public Vector<Vector<Object>> getEvents() throws Exception 
	{
		Vector<Vector<Object>> response = new Vector<Vector<Object>>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE)));
			String line = reader.readLine();
			
			while (line != null) {
				Vector<Object> eventInfo = new Vector<Object>();
				String[] tokens = line.split(";");

				eventInfo.add(tokens[0]);
				eventInfo.add(tokens[1]);
				eventInfo.add(Frequency.valueOf(tokens[2]));
				eventInfo.add(tokens[3]);
				eventInfo.add(tokens[4].equals("1") ? "ON" : "OFF");

				response.add(eventInfo);
				line = reader.readLine();
			}

			reader.close();
		} catch (FileNotFoundException fnfe) {
			notice = "File not found";
			notifyViews();
		} catch (Exception ex) {
			notice = "There was a problem reading the event file";
			notifyViews();
		}
		
		return response;
        }
                public void overwriteEvents(Vector<Vector<Object>> eventos) throws Exception {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), false)); // false = sobrescribir

        for (Vector<Object> evento : eventos) {
            String linea = evento.get(0) + ";" + evento.get(1) + ";" + evento.get(2).toString() + ";" +
                           evento.get(3) + ";" + (evento.get(4).equals("ON") ? "1" : "0");
            writer.write(linea);
            writer.newLine();
        }

        writer.close();
    } catch (Exception ex) {
        notice = "Error while overwriting the file";
        notifyViews();
    }
}
                //añadimos el save guest ya que este recibe y guarda
                //si o si va aca ya que guarda y lee invitados desde el archivo
    public void saveGuest(Guest guest) throws Exception {
    BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, "guests.txt"), true));
    writer.write(guest.toString());
    writer.newLine();
    writer.flush();
    writer.close();
}
        // so añadimos el getGuest, paso 2 de GETLISTVIEW
    public Vector<Vector<Object>> getGuests() throws Exception {
    Vector<Vector<Object>> invitados = new Vector<>();
    File file = new File(DIRECTORY, "guests.txt");
    if (!file.exists()) return invitados;

    BufferedReader reader = new BufferedReader(new FileReader(file));
    String linea;
    while ((linea = reader.readLine()) != null) {
        String[] partes = linea.split(";");
        Vector<Object> fila = new Vector<>();
        for (String parte : partes) fila.add(parte);
        invitados.add(fila);
    }
    reader.close();
    return invitados;
}
	}
