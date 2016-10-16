//: innerclasses/controller/Controller.java
// The reusable framework for control systems.
package holding;

import innerclasses.controller.Event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ex13Controller {
  // A class from java.util to hold Event objects:
  private List<Event> eventList = new LinkedList<Event>();
  public void addEvent(Event c) { eventList.add(c); }
  public void run() {
    while(eventList.size()>0) {
      Iterator<Event> iterator = eventList.iterator();
      while (iterator.hasNext()) {
        Event e = iterator.next();
        if (e.ready()) {
          System.out.println(e);
          e.action();
          iterator.remove();
        }
      }
    }
  }
} ///:~
