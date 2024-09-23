package Level1;

import java.util.Random;


public class NumberGenerator1 {

    public static int[] generateTargetNumbers() {
        Random random = new Random();
        int[] numbers = new int[3];

        // 3자리 숫자 생성 ( 서로 다른 숫자)
        for(int i = 0; i < 3; i++){
            int num;
            do {
                num = random.nextInt(10); // 0부터 9까지 숫자 생성
            } while (contains(numbers,num));
            numbers[i] = num;
        }

       return numbers;
    }

    private static boolean contains(int[] array, int num){
        for (int n : array){
            if (n == num) return true;
        }
    return false;
    }
}
