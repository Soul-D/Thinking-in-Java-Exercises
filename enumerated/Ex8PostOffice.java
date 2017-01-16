package enumerated;

import net.mindview.util.Enums;

import java.util.Iterator;

import static net.mindview.util.Print.print;

class Ex8Mail {

    enum GeneralDelivery {YES, NO1, NO2, NO3, NO4, NO5}

    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    enum ForwardAdress {MISSING, OK1, OK2, OK3, OK4, OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    ForwardAdress forwardAdress;

    static long counter = 0;
    long id = counter++;

    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ", General Delivery: " + generalDelivery +
                ", Address Scanability: " + scannability +
                ", Address Readability: " + readability +
                ", Address Address: " + address +
                ", Return address: " + returnAddress +
                ", Forward address: " + forwardAdress;
    }

    public static Ex8Mail randomMail() {
        Ex8Mail m = new Ex8Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        m.forwardAdress = Enums.random(ForwardAdress.class);
        return m;
    }

    public static Iterable<Ex8Mail> generator(final int count) {
        return new Iterable<Ex8Mail>() {
            int n = count;

            public Iterator<Ex8Mail> iterator() {
                return new Iterator<Ex8Mail>() {
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    public Ex8Mail next() {
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

public class Ex8PostOffice {
    enum MailHandler {
        GENERAL_DELIVERY {
            boolean handle(Ex8Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            boolean handle(Ex8Mail m) {
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
        },
        VISUAL_INSPECTION {
            boolean handle(Ex8Mail m) {
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
        },
        FORWARD_MAIL {
            boolean handle(Ex8Mail m) {
                switch (m.forwardAdress) {
                    case MISSING:
                        return false;
                    default:
                        print("Forwarding " + m);
                        return true;
                }
            }
        },
        RETURN_TO_SENDER {
            boolean handle(Ex8Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        };

        abstract boolean handle(Ex8Mail m);
    }

    static void handle(Ex8Mail m) {
        for (MailHandler handler : MailHandler.values())
            if (handler.handle(m))
                return;
        print(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (Ex8Mail mail : Ex8Mail.generator(10)) {
            print(mail.details());
            handle(mail);
            print("*****");
        }
    }
}