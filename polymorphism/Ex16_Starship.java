package polymorphism;

public class Ex16_Starship {
    AlertStatus state = new OkStatus();
    void setOkStatus(){
        state = new OkStatus();
    }
    void setDangerStatus(){
        state = new DangerStatus();
    }
    void setImmediateThreatStatus(){
        state = new ImmediateThreatStatus();
    }
    void report(){
        state.warning();
    }
}

class AlertStatus {
    void warning(){}
}

class OkStatus extends AlertStatus {
    void warning(){
        System.out.println("Relax");
    }
}

class DangerStatus extends AlertStatus {
    void warning(){
        System.out.println("Focus");
    }
}

class ImmediateThreatStatus extends AlertStatus {
    void warning(){
        System.out.println("Fight");
    }
}

class Ex16_Test{
    public static void main(String[] args) {
        Ex16_Starship starship = new Ex16_Starship();
        starship.report();
        starship.setDangerStatus();
        starship.report();
        starship.setImmediateThreatStatus();
        starship.report();
    }
}