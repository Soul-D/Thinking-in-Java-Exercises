package initialization;

public class Ex6_Dog {
    void bark(){
        System.out.println("Bark");
    }

    void bark(int i, float f){
        System.out.println("Howl");
    }

    void bark(float f, int i){
        System.out.println("Rrrrr");
    }

    public static void main(String[] args) {
        Ex6_Dog dog = new Ex6_Dog();
        dog.bark();
        dog.bark(3, 3f);
        dog.bark(3f, 3);
    }
}
