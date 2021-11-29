package baseball;

import static util.Constants.*;

import camp.nextstep.edu.missionutils.Console;

public class BaseballGameController {
	private final Enemy enemy;
	private final Player player;
	private final BaseballGameModel baseballGameModel;
	private final HintSentenceGenerator hintSentenceGenerator;

	public BaseballGameController() {
		enemy = Enemy.getEnemy();
		player = Player.getPlayer();
		baseballGameModel = BaseballGameModel.getBaseballGameModel();
		hintSentenceGenerator = HintSentenceGenerator.getHintSentenceGenerator();
	}

	public void startGame() {
		int strikeCount;
		int ballCount;
		enemy.setEnemyNum();
		do {
			player.takePlayerNum();
			strikeCount = baseballGameModel.getStrike(player.getPlayerNum(), enemy.getEnemyNum());
			ballCount = baseballGameModel.getBall(player.getPlayerNum(), enemy.getEnemyNum());
			printResult(strikeCount, ballCount);
		} while (!baseballGameModel.checkResult(strikeCount));
	}

	private void printResult(int strikeCount, int ballCount) {
		String hintSentence = hintSentenceGenerator.makeHintSentence(strikeCount, ballCount);
		System.out.println(hintSentence);
	}

	public boolean decideToRestart() {
		String stringRestartValue;
		char charRestartValue;
		System.out.println(DECIDING_RESTART_MESSAGE);
		stringRestartValue = Console.readLine();
		if (stringRestartValue.length() != 1) {
			throw new IllegalArgumentException();
		}
		charRestartValue = stringRestartValue.charAt(0);
		if (charRestartValue == GAME_RESUME) {
			return true;
		} else if (charRestartValue == GAME_OVER) {
			return false;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
