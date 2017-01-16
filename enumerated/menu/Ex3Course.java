package enumerated.menu;

import net.mindview.util.Enums;

public enum Ex3Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class),
    POISON(Ex3Food.Poisons.class);
    private Food[] values;

    private Ex3Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Ex3Course course : Ex3Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}

interface Ex3Food {
    enum Poisons implements Food {
        HEMLOCK,
        ACONITE,
        BELLADONNA
    }
}
