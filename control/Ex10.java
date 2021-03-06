package control;

import java.util.ArrayList;
import java.util.List;

public class Ex10 {
    public static void main(String[] args) {
        getAllVampireNumbers();
    }

    public static void getAllVampireNumbers(){
        for (int i = 1001; i < 10000; i++){
            if (i % 100 != 0){
                int k = i;
                List<Integer> list = new ArrayList();
                List<Integer> indexList = new ArrayList();
                for (int j = 0; j < 4; j++){
                    list.add(k % 10);
                    indexList.add(j);
                    k /= 10;
                }
                List<List<Integer>> perm = new ArrayList<List<Integer>>();
                allPermutation(indexList,perm);
                boolean flag = false;
                for (List<Integer> l : perm){
                    if (checkMath(list.get(l.get(0)),list.get(l.get(1)),list.get(l.get(2)),list.get(l.get(3)),i)){
                        flag = true;
                    }
                }
                if (flag){
                    System.out.print(i + " ");
                }
            }
        }
    }

    private static boolean checkMath(int x1, int x2, int x3, int x4, int number){
        return ((10*x1+x2)*(10*x3+x4)) == number;
    }

    private static void allPermutation(List<Integer> allNumbers, List<List<Integer>> result){
        allPermutationRec(new ArrayList<Integer>(),allNumbers,result);
    }

    private static void allPermutationRec(List<Integer> current, List<Integer> allNumbers, List<List<Integer>> result){
        if (current.size() != allNumbers.size()) {
            for (int x : allNumbers) {
                if (!current.contains(x)){
                    List<Integer> newArr = new ArrayList();
                    newArr.addAll(current);
                    newArr.add(x);
                    allPermutationRec(newArr, allNumbers, result);
                }
            }
        }
        else{
            result.add(current);
        }
    }
}
