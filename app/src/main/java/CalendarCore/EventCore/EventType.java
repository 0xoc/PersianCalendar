package CalendarCore.EventCore;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sina on 1/19/18.
 */

public class EventType implements Serializable{
    public static ArrayList<EventType> events = new ArrayList<>();

    private String eventTitle;

    public EventType(String eventTitle) {
        this.eventTitle = eventTitle;
        events.add(this);
    }

    public String getEventTitle() {
        return eventTitle;
    }
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public static void saveData(Context context) {
        Log.e("INFO","Saving Event type data");
        FileOutputStream dataSize = null;

        // save the size of data
        try {
            dataSize = context.openFileOutput("eventTypeSize",0);
            dataSize.write(events.size());
        } catch (FileNotFoundException e) {
            Log.e("SAVING_ERROR","Could not save event type size");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("SAVING_ERROR","Could not save event type size");
        }

        // save all the data
        for (int i = 0; i < events.size();i++){
            try {
                FileOutputStream fout =  context.openFileOutput("eventType" + i,0);
                ObjectOutputStream oout = new ObjectOutputStream(fout);
                oout.writeObject(events.get(i));
                Log.i("SAVE_DONE","DATA SAVED");
            } catch (FileNotFoundException e) {
                Log.e("SAVING_ERROR","could not save event type data");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("SAVING_ERROR","Could not save event type data");
                e.printStackTrace();
            }
        }
    }
}
