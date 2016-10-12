package unb;

public class Conway extends GameEngine {
	Conway(int height, int width, Statistics statistics) {
		super(height, width, statistics);
	}
	protected boolean shouldKeepAlive(int i, int j) {
		return (cells[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3);
	}
	protected boolean shouldRevive(int i, int j) {
		return (!(cells[i][j].isAlive()))
				&& (numberOfNeighborhoodAliveCells(i, j) == 3);
	}
}
