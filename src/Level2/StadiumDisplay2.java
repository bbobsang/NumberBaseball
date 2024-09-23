package Level2;

public class StadiumDisplay2 {

    // 스트라이크와 볼 정보를 출력하는 메서드
    public void showHint(int strikes, int balls) {
        System.out.printf("스트라이크: %d, 볼: %d\n", strikes, balls);
    }

    // 게임 승리시 메시지를 출력하는 메서드
    public void showVictoryMessage(){
        System.out.println("[[ Baaaam! 정답을 맞혔어요! ]]");
    }
}
