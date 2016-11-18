package arrays;

import java.util.Arrays;

class IntClass{
    int field;

    public IntClass(int field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntClass intClass = (IntClass) o;

        return field == intClass.field;

    }

    @Override
    public int hashCode() {
        return field;
    }
}

public class Ex19 {
    public static void main(String[] args) {
        IntClass[] arr1 = new IntClass[7];
        IntClass[] arr2 = new IntClass[7];
        Arrays.fill(arr1,new IntClass(13));
        Arrays.fill(arr2,new IntClass(13));
        System.out.println(Arrays.equals(arr1,arr2));
    }
}
