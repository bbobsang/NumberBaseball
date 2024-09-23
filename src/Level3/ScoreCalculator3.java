package Level3;

import java.util.HashSet;
import java.util.Set;

public class ScoreCalculator3 {
    private final int[] targetNumbers; // 타겟 숫자 배열

    public ScoreCalculator3(int[] targetNumbers) {
        this.targetNumbers = targetNumbers;
    }

    // 입력 유효성 검사
    public boolean isValidInput(String input) {
        if (input.length() != 3) return false; // 3자리 수 검사
        if (!input.matches("\\d{3}")) return false; // 숫자만 포함되는지 검사

        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : input.toCharArray()){
            uniqueDigits.add(c);
        }
return uniqueDigits.size() == 3; // 중복된 숫자 검사
    }

    // 스트라이크 계산
    public int calculateStrikes(String input) {
        int strikes = 0;
        for (int i = 0; i < 3; i++) {
            if (Character.getNumericValue(input.charAt(i)) == targetNumbers[i]) {
                strikes++;
            }
        }

        return strikes;
    }

    // 볼 계산
    public int calculateBalls(String input) {
        Set<Integer> checkedNumbers = new HashSet<>();
        int balls = 0;

        for (char c : input.toCharArray()) {
            int num = Character.getNumericValue(c);
            if (contains(targetNumbers, num)) {
                balls++;
            }
        }
        return balls - calculateStrikes(input); // 스트라이크를 제외한 볼 수 계산
    }

    private boolean contains(int[] array, int num) {
        for (int n : array) {
            if (n == num) return true;
        }
        return false;
    }
}

