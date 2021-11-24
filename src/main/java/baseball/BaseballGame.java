package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.stream.IntStream;

public class BaseballGame {
    private int[] enemyNum;
    private int[] playerNum;

    public BaseballGame() {
        enemyNum = new int[3];
        playerNum = new int[3];
        setEnemyNum();
    }

    public void gameStart() {
        boolean end = false;

        do {
            takePlayerNum();
            end = checkResult();
        } while (true);
    }

    private void takePlayerNum() {
        char charPlayerNum;
        System.out.print("숫자를 입력해주세요 : ");
        String userInput = Console.readLine();
        if (userInput.length() != 3) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < 3; i++) {
            charPlayerNum = userInput.charAt(i);
            if (!Character.isDigit(charPlayerNum)) {
                throw new IllegalArgumentException();
            }
            playerNum[i] = Character.getNumericValue(charPlayerNum);
        }
    }

    private boolean checkResult() {
        int strike=0;
        int ball=0;
        for (int i = 0; i < 3; i++) {
            if (playerNum[i] == enemyNum[i]) {
                strike++;
                playerNum[i] = -1;
            }
        }
        for (Integer num : playerNum) {
            if (IntStream.of(enemyNum).anyMatch(x -> x == num)) {
                ball++;
            }
        }
        return printResult(strike, ball);
    }

    private boolean printResult(int strike, int ball) {
        if (ball != 0) {
            System.out.print(String.format("%d볼 ", ball));
        }
        if (strike != 0) {
            System.out.print(String.format("%d스트라이크", strike));
        }
        if (ball + strike == 0) {
            System.out.print("낫싱");
        }
        System.out.println("");
        if (strike == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }
        return false;
    }

    private void setEnemyNum() {
        int smallNumberIndex = 1;
        for (int i = 0; i < 3; i++) {
            enemyNum[i] = Randoms.pickNumberInRange(1, 9 - i);
        }
        if (enemyNum[0] <= enemyNum[1]) {
            enemyNum[1] += 1;
            smallNumberIndex = 0;
        }
        if (enemyNum[smallNumberIndex] <= enemyNum[2]) {
            enemyNum[2] += 1;
            if (smallNumberIndex == 1) {
                smallNumberIndex = 0;
            } else {
                smallNumberIndex = 1;
            }
        }
        if (enemyNum[smallNumberIndex] <= enemyNum[2]) {
            enemyNum[2] += 1;
        }
    }
}