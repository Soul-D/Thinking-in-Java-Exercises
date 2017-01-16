package enumerated;

import net.mindview.util.Generator;
import net.mindview.util.TextFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static enumerated.Ex11Input.*;
import static net.mindview.util.Print.print;

interface Ex11VendingEntity {
    int amount();

    String getName();
}

enum Ex11Input implements Ex11VendingEntity {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    ABORT_TRANSACTION {
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP {
        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value;


    Ex11Input(int value) {
        this.value = value;
    }

    Ex11Input() {
    }

    public int amount() {
        return value;
    }

    public String getName() {
        return name();
    }
}

enum Ex11Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION,
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);
    private List<Ex11VendingEntity> values;

    Ex11Category(Ex11VendingEntity... types) {
        values = Arrays.asList(types);
    }

    private static Map<Ex11VendingEntity, Ex11Category> categories = new HashMap<Ex11VendingEntity, Ex11Category>();
    private static Map<String, Ex11VendingEntity> vendingEntities = new HashMap<String, Ex11VendingEntity>();

    static {
        for (Ex11Category c : Ex11Category.class.getEnumConstants()) {
            if (c == ITEM_SELECTION) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader("./src/main/java/enumerated/Ex11VendingConf"));
                    String cur = null;
                    c.values = new ArrayList<Ex11VendingEntity>();
                    while ((cur = br.readLine()) != null) {
                        final String[] split = cur.split("\\s+");
                        c.values.add(new Ex11VendingEntity() {
                            public int amount() {
                                return Integer.parseInt(split[1]);
                            }

                            public String getName() {
                                return split[0];
                            }
                        });
                    }
                    br.close();
                } catch (java.io.IOException e) {
                    throw new RuntimeException(e);
                }
            }
            for (Ex11VendingEntity type : c.values) {
                vendingEntities.put(type.getName(), type);
                categories.put(type, c);
            }
        }
    }

    public static Ex11Category categorize(Ex11VendingEntity input) {
        return categories.get(input);
    }

    public static Ex11VendingEntity getVendingItem(String name) {
        return vendingEntities.get(name);
    }
}

class Ex11VendingMachine {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static Ex11VendingEntity selection = null;

    enum StateDuration {TRANSIENT}

    enum State {
        RESTING {
            void next(Ex11VendingEntity input) {
                switch (Ex11Category.categorize(input)) {
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
            void next(Ex11VendingEntity input) {
                switch (Ex11Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            print("Insufficient money for " + selection.getName());
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
                print("here is your " + selection.getName());
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            void output() {
                print("Halted");
            }
        };
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Ex11VendingEntity input) {
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }

    static void run(Generator<Ex11VendingEntity> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient)
                state.next();
            state.output();
        }
    }

    public static void main(String[] args) {
        Ex11FileInputGenerator gen = new Ex11FileInputGenerator("./src/main/java/enumerated/Ex11VendingMachineInput.txt");
        run(gen);
    }
}

class Ex11FileInputGenerator implements Generator<Ex11VendingEntity> {
    private Iterator<String> input;

    public Ex11FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";\\s*").iterator();
    }

    public Ex11VendingEntity next() {
        if (!input.hasNext())
            return null;
        return Ex11Category.getVendingItem(input.next());
    }
}
