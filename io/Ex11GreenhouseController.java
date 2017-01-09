package io;

import innerclasses.GreenhouseControls;
import innerclasses.controller.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;

public class Ex11GreenhouseController extends GreenhouseControls {
    public class Restart extends Event {
        private Event[] eventList;

        public Restart(long delayTime) {
            super(delayTime);
        }

        public void action() {
            for (Event e : eventList) {
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }

        public String toString() {
            return "Restarting system";
        }

        public void setEventList(Event[] eventList) {
            this.eventList = eventList;
            for (Event e : eventList)
                addEvent(e);
        }
    }

    static void loadEvents(Ex11GreenhouseController gc, String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String tmp;
            while ((tmp = br.readLine()) != null) {
                String[] tmpArr = tmp.split(" ");
                if (tmpArr[0].equals("Restart")) {
                    Restart restart = gc.new Restart(Long.parseLong(tmpArr[1]));
                    Event[] events = new Event[Integer.parseInt(tmpArr[2])];
                    for (int i = 0; i < Integer.parseInt(tmpArr[2]); i++) {
                        String[] resArr = br.readLine().split(" ");
                        events[i] = createEvent(gc, resArr[0], Long.parseLong(resArr[1]));
                    }
                    restart.setEventList(events);
                    gc.addEvent(restart);
                } else {
                    gc.addEvent(createEvent(gc, tmpArr[0], Long.parseLong(tmpArr[1])));
                }
            }
            br.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static Event createEvent(Ex11GreenhouseController gc, String name, long delayTime) {
        if (name.equals("Terminate")) {
            return new GreenhouseControls.Terminate(delayTime);
        } else {
            Class<? extends Ex11GreenhouseController> clazz = gc.getClass();
            for (Class tmpClass : clazz.getClasses()) {
                if (tmpClass.getSimpleName().equals(name)) {
                    try {
                        return (Event) tmpClass.getConstructor(GreenhouseControls.class, long.class).newInstance(gc, delayTime);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Ex11GreenhouseController gc = new Ex11GreenhouseController();
        loadEvents(gc, "./src/main/java/io/Ex11Config.txt");
        gc.run();
    }
}
