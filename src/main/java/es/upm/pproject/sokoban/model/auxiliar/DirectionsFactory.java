package es.upm.pproject.sokoban.model.auxiliar;

public class DirectionsFactory {

	public Pair<Integer, Integer> nextPosition(int i, int j, Direction direction) {
		Pair<Integer, Integer> pair;
		switch (direction) {
		case RIGHT:
			pair = new Pair<>(i, j + 1);
			break;
		case LEFT:
			pair = new Pair<>(i, j - 1);
			break;
		case UP:
			pair = new Pair<>(i - 1, j);
			break;
		case DOWN:
			pair = new Pair<>(i + 1, j);
			break;
		default:
			pair = new Pair<>(i, j);
			break;
		}
		return pair;
	}
}
