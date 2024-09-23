package Level4;

import Level4.NumberGenerator4;
import Level4.ScoreCalculator4;
import Level4.StadiumDisplay4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Match4 {
    private int[] targetNumbers;  // 타겟 숫자를 저장하는 배열
    private ScoreCalculator4 scoreCalculator; // 점수를 계산하는 객체
    private final StadiumDisplay4 display; // 사용자에게 정보를 출력하는 객체
    private List<Integer> attemptCounts; // 게임 시도 횟수를 저장하는 리스트
    private int currentGameAttempts; // 현재 게임의 시도 횟수
    private int difficulty; // 난이도 설정


    // 생성자: 새로운 게임을 시작하기 위한 초기화
    public Match4() {
        this.display = new StadiumDisplay4(); // 디스플레이 초기화
        this.attemptCounts = new ArrayList<>(); // 시도 횟수 리스트 초기화
        this.difficulty = 3; // 기본 난이도
        this.scoreCalculator = new ScoreCalculator4(new int[3]); // 점수 계산기 초기화
    }

    // 게임 시작 메서드
    public void start() {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 스캐너
        boolean playAgain; // 재시작 여부 플래그

        System.out.println("           ⚾️ Welcome! 원하시는 번호를 선택하세요. ⚾️");

        while (true) {
            // 게임 시작 메뉴 출력
            System.out.println("⎧‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾⎫");
            System.out.println("⎢  0. 자리수 설정 | 1. 게임시작 | 2. 게임 기록 보기 | 3. 종료하기   ⎜");
            System.out.println("⎩⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎭");
            int choose = scanner.nextInt(); // 사용자 선택 입력
            scanner.nextLine(); // 버퍼 비우기

            // 게임 시작
            if (choose == 0) {
                setDifficulty(scanner); // 자리수 설정 메서드 호출
            } else if (choose == 1) {
                targetNumbers = NumberGenerator4.generateTargetNumbers(difficulty); // 난이도에 맞는 목표 숫자 생성
                setScoreCalculator(); // 점수 계산기 초기화
                System.out.println("// Game Start! //");
                playAgain = playGame(scanner); // 게임 시작 및 재시작 여부 반환
            } else if (choose == 2) {
                showGameRecords(); // 게임 기록 출력
            } else if (choose == 3) {
                // 게임 종료 메시지 출력
                System.out.println("----------------");
                System.out.println("게임이 종료되었습니다.");
                System.out.println("----------------");
                break; // 게임 종료
            } else {
                System.out.println("잘못된 입력입니다. 0, 1, 2 ,3 중에 하나를 입력해주세요.");
            }
        }
        scanner.close(); // 스캐너 종료
    }

    private void setDifficulty(Scanner scanner) {
        System.out.println("난이도 설정: 설정하고자 하는 자리수를 입력하세요 (3, 4, 5 중 선택): ");
        int inputDifficulty = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (inputDifficulty == 3 || inputDifficulty == 4 || inputDifficulty == 5) {
            difficulty = inputDifficulty;
            System.out.printf("%d자리수 난이도로 설정되었습니다.%n", difficulty);
        } else {
            System.out.println("올바른 값을 입력해주세요 (3, 4, 5, 중 하나).");
        }
    }

    private void setScoreCalculator() {
        scoreCalculator = new ScoreCalculator4(targetNumbers); // 타겟 숫자에 맞게 점수 계산기 설정
    }

    private boolean playGame(Scanner scanner) {
        currentGameAttempts = 0; // 현재 게임의 시도 횟수 초기화
        boolean imTheWinner = false; // 승리 여부 플래그

        // 게임 진행 루프
        while (!imTheWinner) {
            System.out.printf("[[ %d자리 숫자를 입력하세요 ]]%n", difficulty); // 자리수에 맞게 출력
            String input = scanner.nextLine(); // 사용자 입력 받기

            // 입력 유효성 검사
            if (!scoreCalculator.isValidInput(input)) {
                System.out.printf("올바른 %d자리 숫자를 입력해주세요. %n", difficulty);
                continue; // 유효하지 않으면 다시 입력 요청
            }
            currentGameAttempts++; // 시도 횟수 증가

            // 스트라이크와 볼 계산
            int strikes = scoreCalculator.calculateStrikes(input); // 스트라이크 계산
            int balls = scoreCalculator.calculateBalls(input); // 볼 계산

            // 승리 조건 검사
            if (strikes == difficulty) {
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
        return replay.equals("y"); // 'y' 입력시 true, 아니면 false
    }

    private void showGameRecords() {
        System.out.println(" [[ 개임 기록 보기 ]]");
        if (attemptCounts.isEmpty()) {
            System.out.println("게임 기록이 없습니다.");
        } else {
            for (int i = 0; i < attemptCounts.size(); i++) {
                System.out.printf("%d번째 게임: 시도 횟수 / %d\n", i + 1, attemptCounts.get(i));
            }
        }
    }
}





