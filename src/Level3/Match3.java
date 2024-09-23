package Level3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Match3 {
    // 타겟 숫자를 저장하는 배열
    private int[] targetNumbers;
    // 점수를 계산하는 객체
    private final ScoreCalculator3 scoreCalculator;
    // 사용자에게 정보를 출력하는 객체
    private final StadiumDisplay3 display;
    private List<Integer> attemptCounts; // 게임 시도 횟수를 저장하는 리스트
    private int currentGameAttempts; // 현재 게임의 시도 횟수

    // 생성자: 새로운 게임을 시작하기 위한 초기화
    public Match3() {
        this.targetNumbers = NumberGenerator3.generateTargetNumbers(); // 타겟 숫자 생성
        this.scoreCalculator = new ScoreCalculator3(targetNumbers); // 점수 계산기 초기화
        this.display = new StadiumDisplay3(); // 디스플레이 초기화
        this.attemptCounts = new ArrayList<>(); // 시도 횟수 리스트 초기화
    }

    // 게임 시작 메서드
    public void start() {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 스캐너
        boolean playAgain; // 재시작 야부 플래그
        int gameCount = 0;

        System.out.println(" ⚾️ Welcome! 원하시는 번호를 선택하세요. ⚾️");

        do {
            // 게임 시작 메뉴 출력
            System.out.println("⎧‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾⎫");
            System.out.println("⎢ 1. 게임시작 | 2. 게임 기록 보기 | 3. 종료하기 ⎢");
            System.out.println("⎩⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎭");
            int choose = scanner.nextInt(); // 사용자 선택 입력
            scanner.nextLine(); // 버퍼 비우기

            // 게임 시작
            if (choose == 1) {
                gameCount++; // 게임 횟수 증가
                currentGameAttempts = 0; // 현재 게임의 시도 횟수 초기화
                System.out.println("// Game Start! //");
                boolean imTheWinner = false; // 승리 여부 플래그


                // 게임 진행 루프
                while (!imTheWinner) {
                    System.out.println("[[ 3자리 숫자를 입력하세요 ]]");
                    String input = scanner.nextLine(); // 사용자 입력 받기

                    // 입력 유효성 검사
                    if (!scoreCalculator.isValidInput(input)) {
                        System.out.println("올바른 3자리 숫자를 입력해주세요. 예: 123");
                        continue; // 유효하지 않으면 다시 입력 요청
                    }
                    currentGameAttempts++; // 시도 횟수 증가

                    // 스트라이크와 볼 계산
                    int strikes = scoreCalculator.calculateStrikes(input);
                    int balls = scoreCalculator.calculateBalls(input);

                    // 승리 조건 검사
                    if (strikes == 3) {
                        display.showVictoryMessage(currentGameAttempts); // 승리 메시지 출력
                        imTheWinner = true; // 게임 종료 플래그 설정
                        attemptCounts.add(currentGameAttempts); // 시도 횟수 저장
                    } else {
                        display.showHint(strikes, balls); // 힌트 출력

                    }
                }

                // 재시작 여부 묻기
                System.out.println(" 게임을 계속 하려면 'Y', 종료하려면 'N'을 입력하세요.)");
                String replay = scanner.nextLine().trim().toLowerCase(); // 재시작 입력 받기
                playAgain = replay.equals("y"); // 'y' 입력 시 재시작


                // 재시작 시 새로운 타겟 숫자 생성
                if (playAgain) {
                    targetNumbers = NumberGenerator3.generateTargetNumbers(); // 새로운 타겟 숫자 생성
                }
            } else if (choose == 2) {
                // 게임 기록 출력
                System.out.println("[[ 게임 기록 보기 ]]");
                if (attemptCounts.isEmpty()) {
                    System.out.println("게임 기록이 없습니다.");
                } else {
                    for (int i = 0; i < attemptCounts.size(); i++) {
                        System.out.printf("%d번째 게임 : 시도 횟수 / %d\n", i + 1, attemptCounts.get(i));
                    }
                }
                playAgain = true;
            } else if (choose == 3) {
                // 게임 종료 메시지 출력
                System.out.println("----------------");
                System.out.println("게임이 종료되었습니다.");
                System.out.println("----------------");
                playAgain = false; // 게임 종료
            } else {
                System.out.println("잘못된 입력입니다. 1이나 2를 입력해주세요.");
                playAgain = true;
            }
        } while (playAgain); // 재시작 여부에 따라 반복
        scanner.close(); // 스캐너 종료
    }
}
