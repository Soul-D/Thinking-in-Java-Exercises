package enumerated;

import java.util.*;

import static enumerated.Project2.MailHandler.*;
import static enumerated.ProjectMail.State.*;
import static net.mindview.util.Print.print;

public class Project2 {
    enum MailHandler {
        BINGO, GENERAL_DELIVERY, AUTOMATIC_DELIVERY, NORMAL_DELIVERY, RETURN_TO_SENDER
    }

    static EnumMap<MailHandler, EnumSet<ProjectMail.State>> handlers = new EnumMap<MailHandler, EnumSet<ProjectMail.State>>(MailHandler.class);

    static {
        handlers.put(BINGO, EnumSet.of(GeneralDelivery, Scannability, Readability, Address, ReturnAddress));
        handlers.put(GENERAL_DELIVERY, EnumSet.of(GeneralDelivery));
        handlers.put(AUTOMATIC_DELIVERY, EnumSet.of(Scannability, Address));
        handlers.put(NORMAL_DELIVERY, EnumSet.of(Readability, Address));
        handlers.put(RETURN_TO_SENDER, EnumSet.of(ReturnAddress));
    }

    static void handle(ProjectMail m) {
        for (EnumMap.Entry<MailHandler, EnumSet<ProjectMail.State>> handler : handlers.entrySet())
            if (m.states.containsAll(handler.getValue())) {
                System.out.println(handler.getKey());
                return;
            }
        print(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (ProjectMail mail : ProjectMail.generator(10)) {
            print(mail.details());
            handle(mail);
            print("*****");
        }
    }
}

class ProjectMail {
    enum State {GeneralDelivery, Scannability, Readability, Address, ReturnAddress}

    final Set<State> states = new HashSet<State>();

    static long counter = 0;
    long id = counter++;

    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return states.toString();
    }

    private static Random rand = new Random(42);

    public static ProjectMail randomMail() {
        ProjectMail m = new ProjectMail();
        if (rand.nextDouble() > 0.83)
            m.states.add(GeneralDelivery);
        if (rand.nextDouble() > 0.2)
            m.states.add(Scannability);
        if (rand.nextDouble() > 0.2)
            m.states.add(Readability);
        if (rand.nextDouble() > 0.14)
            m.states.add(Address);
        if (rand.nextDouble() > 0.17)
            m.states.add(State.ReturnAddress);
        return m;
    }

    public static Iterable<ProjectMail> generator(final int count) {
        return new Iterable<ProjectMail>() {
            int n = count;

            public Iterator<ProjectMail> iterator() {
                return new Iterator<ProjectMail>() {
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    public ProjectMail next() {
                        return randomMail();
                    }

                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}


