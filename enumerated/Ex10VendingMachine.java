package enumerated;

import net.mindview.util.Generator;

import java.util.EnumMap;

import static enumerated.Ex10VendingMachine.State.*;
import static net.mindview.util.Print.print;

public class Ex10VendingMachine {
    private State state = RESTING;
    private int amount = 0;
    private Input selection = null;

    enum StateDuration {TRANSIENT}

    enum State {
        RESTING, ADDING_MONEY, DISPENSING(StateDuration.TRANSIENT), GIVING_CHANGE(StateDuration.TRANSIENT), TERMINAL;

        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }
    }

    EnumMap<State, Ex10Next> states = new EnumMap<State, Ex10Next>(State.class);

    public Ex10VendingMachine() {
        states.put(RESTING, new Ex10Next() {
            public void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        });
        states.put(ADDING_MONEY, new Ex10Next() {
            public void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            print("Insufficient money for " + selection);
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
        });
        states.put(DISPENSING, new Ex10Next() {
            public void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        });
        states.put(GIVING_CHANGE, new Ex10Next() {
            public void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        });
        states.put(TERMINAL, new Ex10Next() {
            void output() {
                print("Halted");
            }
        });
    }

    void run(Generator<Input> gen) {
        while (state != TERMINAL) {
            states.get(state).next(gen.next());
            while (state.isTransient)
                states.get(state).next();
            states.get(state).output();
        }
    }

    public static void main(String[] args) {
        new Thread(){
            public void run() {
                Generator<Input> gen = new FileInputGenerator("./src/main/java/enumerated/VendingMachineInput.txt");
                Ex10VendingMachine vendingMachine = new Ex10VendingMachine();
                vendingMachine.run(gen);
            }
        }.start();
        new Thread(){
            public void run() {
                Generator<Input> gen = new FileInputGenerator("./src/main/java/enumerated/VendingMachineInput.txt");
                Ex10VendingMachine vendingMachine = new Ex10VendingMachine();
                vendingMachine.run(gen);
            }
        }.start();
        new Thread(){
            public void run() {
                Generator<Input> gen = new FileInputGenerator("./src/main/java/enumerated/VendingMachineInput.txt");
                Ex10VendingMachine vendingMachine = new Ex10VendingMachine();
                vendingMachine.run(gen);
            }
        }.start();
    }

    abstract class Ex10Next {
        void next(Input input) {
            throw new RuntimeException("Only call next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }
}	

