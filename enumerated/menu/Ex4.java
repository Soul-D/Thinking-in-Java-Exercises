package enumerated.menu;

import net.mindview.util.Enums;

public enum Ex4 {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class),
    POISON(Poisons.class);
    private Food[] values;

    private enum Poisons implements Food {
        HEMLOCK,
        ACONITE,
        BELLADONNA
    }

    private Ex4(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Ex4 course : Ex4.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}
