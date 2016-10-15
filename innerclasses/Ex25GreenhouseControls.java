package innerclasses;

import innerclasses.controller.Event;

public class Ex25GreenhouseControls extends GreenhouseControls {
    boolean mistGenerator = false;
    class MistGeneratorOn extends Event{
        public MistGeneratorOn(long delayTime) {
            super(delayTime);
        }

        public void action() {
            mistGenerator = true;
        }

        public String toString() {
            return "MistGenerator is on";
        }
    }
    class MistGeneratorOff extends Event{
        public MistGeneratorOff(long delayTime) {
            super(delayTime);
        }

        public void action() {
            mistGenerator = false;
        }

        public String toString() {
            return "MistGenerator is off";
        }
    }
}

class Ex25GreenhouseController {
    public static void main(String[] args) {
        Ex25GreenhouseControls gc = new Ex25GreenhouseControls();
        gc.addEvent(gc.new MistGeneratorOn(400));
        gc.addEvent(gc.new MistGeneratorOff(800));
        gc.run();
    }
}
