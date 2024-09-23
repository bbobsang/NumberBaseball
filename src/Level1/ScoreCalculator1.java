package Level1;

public class ScoreCalculator1 {
    private int[] targetNumbers;

    public ScoreCalculator1(int[] targetNumbers) {
        this.targetNumbers = targetNumbers;
    }

    public void setTargetNumbers(int[] targetNumbers) {
        this.targetNumbers = targetNumbers;
    }

    public boolean isValidInput(String input) {
        return input.matches("[0-9]{3}"); // 3자리 숫자인지 체크

    }

    public int calculateStrikes(String input) {
        int strikes = 0;
        for (int i = 0; i < 3; i++) {
            if (Character.getNumericValue(input.charAt(i)) == targetNumbers[i]) {
                strikes++;
            }
        }

        return strikes;
    }

    public int calculateBalls(String input) {
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            int guess = Character.getNumericValue(input.charAt(i));
            for (int j = 0; j < 3; j++) {
                if (targetNumbers[j] == guess && i != j) {
                    balls++;
                }
            }
        }
        return balls;
    }
}
