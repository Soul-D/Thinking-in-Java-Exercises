package typeinfo;

interface Ex26Instrument {
    void play();
    void prepareInstrument();
}

abstract class Wind implements Ex26Instrument{
    abstract void clearSpitValve();

    public void prepareInstrument() {
        clearSpitValve();
    }
}

class Horn extends Wind{
    void clearSpitValve() {
        System.out.println("Clearing spit valve of horn");
    }

    public void play() {
        System.out.println("Playing horn");
    }
}

class Ex26Test{
    public static void main(String[] args) {
        Ex26Instrument horn = new Horn();
        horn.prepareInstrument();
        horn.play();
    }
}
