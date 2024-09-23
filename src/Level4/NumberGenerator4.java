package Level4;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class NumberGenerator4 {

    public static int[] generateTargetNumbers(int digits) {
        Random random = new Random();
        int[] numbers = new int[digits];
        Set<Integer> generatedNumbers = new HashSet<>(); // 중복 방지를 위한 Set


        for (int i = 0; i < digits; i++) {
            int num;
            do {
                num = random.nextInt(9) + 1; // 1부터 9까지 숫자 생성
            } while (generatedNumbers.contains(num));
            numbers[i] = num;
            generatedNumbers.add(num);
        }

        return numbers;
    }
}
