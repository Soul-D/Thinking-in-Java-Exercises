package initialization;

public class Ex5_Dog {
    void bark(){
        System.out.println("Bark");
    }

    void bark(int i){
        System.out.println("Howl");
    }

    void bark(Float f){
        System.out.println("Rrrrr");
    }

    public static void main(String[] args) {
        Ex5_Dog dog = new Ex5_Dog();
        dog.bark();
        dog.bark(3);
        dog.bark(1.f);
    }
}
