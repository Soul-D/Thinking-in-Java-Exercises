package enumerated;

import java.util.EnumMap;

import static enumerated.Ex9PostOffice.MailHandler.*;
import static net.mindview.util.Print.print;

public class Ex9PostOffice {
    enum MailHandler {
        GENERAL_DELIVERY, MACHINE_SCAN, VISUAL_INSPECTION, RETURN_TO_SENDER
    }

    static EnumMap<MailHandler, Ex9Command> mailHandlers = new EnumMap<MailHandler, Ex9Command>(MailHandler.class);

    static {
        mailHandlers.put(GENERAL_DELIVERY, new Ex9Command() {
            public boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        });
        mailHandlers.put(MACHINE_SCAN, new Ex9Command() {
            public boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                print("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        });
        mailHandlers.put(VISUAL_INSPECTION, new Ex9Command() {
            public boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                print("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        });
        mailHandlers.put(RETURN_TO_SENDER, new Ex9Command() {
            public boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        });
    }

    static void handle(Mail m) {
        for (Ex9Command command : mailHandlers.values())
            if (command.handle(m))
                return;
        print(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)) {
            print(mail.details());
            handle(mail);
            print("*****");
        }
    }
}

interface Ex9Command {
    boolean handle(Mail m);
}
