package initialization;

import static initialization.Ex21.*;

public class Ex22 {
    Ex21 ex21;

    public Ex22(Ex21 ex21) {
        this.ex21 = ex21;
    }

    public static void main(String[] args) {
        for (Ex21 e : Ex21.values()) {
            switch (e) {
                case CENT:
                    System.out.println("Cent");
                    break;
                case FIFTY_CENTS:
                    System.out.println("Fifty cents");
                    break;
                case DOLLAR:
                    System.out.println("1$");
                    break;
                case TEN_DOLLARS:
                    System.out.println("10$");
                    break;
                case FIFTY_DOLLARS:
                    System.out.println("50$");
                    break;
                case HUNDRED_DOLLARS:
                    System.out.println("100$");
                    break;
            }
        }
    }
}
