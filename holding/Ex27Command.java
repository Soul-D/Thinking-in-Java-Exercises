package holding;

import java.util.LinkedList;
import java.util.Queue;

public class Ex27Command {
    String command;

    public Ex27Command(String command) {
        this.command = command;
    }

    void operation(){
        System.out.println(command);
    }
}

class Ex27Fill{
    static Queue<Ex27Command> fill(Queue<Ex27Command> queue){
        queue.add(new Ex27Command("fire"));
        queue.add(new Ex27Command("run"));
        queue.add(new Ex27Command("swim"));
        queue.add(new Ex27Command("die"));
        return queue;
    }
}

class Ex27Execute{
    static void execute(Queue<Ex27Command> queue){
        for(Ex27Command command : queue){
            command.operation();
        }
        queue.clear();
    }
}

class Ex27Test{
    public static void main(String[] args) {
        Queue<Ex27Command> queue = new LinkedList<Ex27Command>();
        Ex27Execute.execute(Ex27Fill.fill(queue));
    }
}
