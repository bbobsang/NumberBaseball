package Level3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class NumberGenerator3 {

    public static int[] generateTargetNumbers() {
        Random random = new Random();
        int[] numbers = new int[3];
        Set<Integer> generatedNumbers = new HashSet<>(); // 중복 방지를 위한 Set


        for (int i = 0; i < 3; i++) {
            int num;
            do {
                num = random.nextInt(9) + 1; // 0부터 9까지 숫자 생성
            } while (generatedNumbers.contains(num));
            numbers[i] = num;
            generatedNumbers.add(num);
        }

        return numbers;
    }

//    private static boolean contains(int[] array, int num) {
//        for (int n : array) {
//            if (n == num) return true;
//        }
//        return false;
//    }
}
