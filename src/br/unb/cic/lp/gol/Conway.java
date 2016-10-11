package br.unb.cic.lp.gol;

public class Conway extends GameEngine {
	protected boolean shouldKeepAlive(int i, int j) {
		return (cells[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3);
	}
	protected boolean shouldRevive(int i, int j) {
		return (!(cells[i][j].isAlive()))
				&& (numberOfNeighborhoodAliveCells(i, j) == 3);
	}
}
