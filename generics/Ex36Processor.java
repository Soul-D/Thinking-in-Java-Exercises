package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Ex36Processor<T, E extends Exception, X extends Exception> {
    void process(List<T> resultCollector) throws E, X;
}

class Ex36ProcessRunner<T, E extends Exception, X extends Exception> extends ArrayList<Ex36Processor<T, E, X>> {
    List<T> processAll() throws E, X {
        List<T> resultCollector = new ArrayList<T>();
        for (Ex36Processor<T, E, X> processor : this)
            processor.process(resultCollector);
        return resultCollector;
    }
}

class Ex36Failure1 extends Exception {
}

class Ex36Failure2 extends Exception {
}

class Ex36Processor1 implements Ex36Processor<String, Ex36Failure1, Ex36Failure1> {
    static int count = 2;

    public void process(List<String> resultCollector) throws Ex36Failure1 {
        if (count-- > 1)
            resultCollector.add("Hep!");
        else
            resultCollector.add("Ho!");
        if (count < 0) {
            Random random = new Random();
            if (random.nextBoolean())
                throw new Ex36Failure1();
            else
                throw new Ex36Failure1();
        }
    }
}

class Ex36Processor2 implements Ex36Processor<Integer, Ex36Failure1, Ex36Failure2> {
    static int count = 2;

    public void process(List<Integer> resultCollector) throws Ex36Failure1, Ex36Failure2 {
        if (count-- > 1)
            resultCollector.add(47);
        else
            resultCollector.add(11);
        if (count < 0) {
            Random random = new Random();
            if (random.nextBoolean())
                throw new Ex36Failure1();
            else
                throw new Ex36Failure2();
        }
    }
}

class Ex36ThrowGenericException {
    public static void main(String[] args) {
        Ex36ProcessRunner<String, Ex36Failure1, Ex36Failure1> runner = new Ex36ProcessRunner<String, Ex36Failure1, Ex36Failure1>();
        for (int i = 0; i < 3; i++)
            runner.add(new Ex36Processor1());
        try {
            System.out.println(runner.processAll());
        } catch (Ex36Failure1 e) {
            System.out.println(e);
        }

        Ex36ProcessRunner<Integer, Ex36Failure1, Ex36Failure2> runner2 = new Ex36ProcessRunner<Integer, Ex36Failure1, Ex36Failure2>();
        for (int i = 0; i < 3; i++)
            runner2.add(new Ex36Processor2());
        try {
            System.out.println(runner2.processAll());
        } catch (Ex36Failure1 e) {
            System.out.println(e);
        } catch (Ex36Failure2 e) {
            System.out.println(e);
        }
    }
}
