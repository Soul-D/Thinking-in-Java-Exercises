package enumerated;

import net.mindview.util.Generator;
import net.mindview.util.TextFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static net.mindview.util.Print.print;

public class Project3 {
    public static void main(String[] args) {
        P3VendingMachine.P3FileInputGenerator generator = new P3VendingMachine.P3FileInputGenerator("./src/main/java/enumerated/Ex11VendingMachineInput.txt");
        P3VendingMachine.setLang(P3VendingMachine.Language.RU);
        P3VendingMachine.run(generator);
    }
}

class P3VendingMachine {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static P3Item selection = null;
    private static Language lang;
    private static Map<String, String> interMap = new HashMap<String, String>();
    private static Map<String, P3Item> itemsMap = new HashMap<String, P3Item>();

    interface P3Item {
        int amount();

        String getName();

        P3Category getCategory();
    }

    enum P3Category {
        MONEY, ITEM_SELECTION, QUIT_TRANSACTION, SHUT_DOWN
    }

    private static void loadLang() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/main/java/enumerated/Project3" + lang));
            String cur = null;
            while ((cur = br.readLine()) != null) {
                String[] split = cur.split(":");
                interMap.put(split[0], split[1]);
            }
            br.close();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to load lang " + lang);
        }
    }

    private static void loadConf() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/main/java/enumerated/Project3Conf"));
            String cur = null;
            boolean items = false;
            while ((cur = br.readLine()) != null) {
                if (cur.equals("ITEM_SELECTION"))
                    items = true;
                else if (!cur.equals("MONEY") && !items) {
                    final String[] split = cur.split(":");
                    itemsMap.put(split[0], new P3Item() {
                        public int amount() {
                            return Integer.parseInt(split[1]);
                        }

                        public String getName() {
                            return split[0];
                        }

                        public P3Category getCategory() {
                            return P3Category.MONEY;
                        }
                    });
                } else if (!cur.equals("MONEY") && items) {
                    final String[] split = cur.split(":");
                    itemsMap.put(split[0], new P3Item() {
                        public int amount() {
                            return Integer.parseInt(split[1]);
                        }

                        public String getName() {
                            return split[0];
                        }

                        public P3Category getCategory() {
                            return P3Category.ITEM_SELECTION;
                        }
                    });
                }
            }
            itemsMap.put("ABORT_TRANSACTION", new P3Item() {
                public int amount() {
                    throw new RuntimeException("ABORT.amount()");
                }

                public String getName() {
                    return null;
                }

                public P3Category getCategory() {
                    return P3Category.QUIT_TRANSACTION;
                }
            });
            itemsMap.put("STOP", new P3Item() {
                public int amount() {
                    throw new RuntimeException("SHUT_DOWN.amount()");
                }

                public String getName() {
                    return null;
                }

                public P3Category getCategory() {
                    return P3Category.SHUT_DOWN;
                }
            });
            br.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static {
        loadConf();
        lang = Language.EN;
        loadLang();
    }

    public static void setLang(Language lang) {
        P3VendingMachine.lang = lang;
        loadLang();
    }

    enum Language {EN, RU}

    enum StateDuration {TRANSIENT}

    enum State {
        RESTING {
            void next(P3Item input) {
                switch (input.getCategory()) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            void next(P3Item input) {
                switch (input.getCategory()) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            print(interMap.get("insufficient") + " " + interMap.get(selection.getName()));
                        else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            void next() {
                print(interMap.get("here") + " " + interMap.get(selection.getName()));
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next() {
                if (amount > 0) {
                    print(interMap.get("change") + ": " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            void output() {
                print(interMap.get("halted"));
            }
        };
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(P3Item input) {
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }

    static void run(Generator<P3Item> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient)
                state.next();
            state.output();
        }
    }

    static class P3FileInputGenerator implements Generator<P3Item> {
        private Iterator<String> input;

        public P3FileInputGenerator(String fileName) {
            input = new TextFile(fileName, ";\\s*").iterator();
        }

        public P3Item next() {
            if (!input.hasNext())
                return null;
            return itemsMap.get(input.next());
        }
    }
}



