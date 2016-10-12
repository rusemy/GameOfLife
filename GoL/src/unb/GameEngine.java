package unb;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

abstract class GameEngine {
	/*private*/protected int height;
	/*private*/protected int width;
	/*private*/protected Cell[][] cells;
	/*private*/protected Statistics statistics;

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	 GameEngine(int height, int width, Statistics statistics) {
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
		
		this.statistics = statistics;
	}

	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
	 * algoritmo do Conway, ou seja:
	 * 
	 * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
	 * uma celula viva.
	 * 
	 * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
	 * viva.
	 * 
	 * c) em todos os outros casos a celula morre ou continua morta.
	 */
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (shouldRevive(i, j)) {
					mustRevive.add(cells[i][j]);
				} 
				else if ((!shouldKeepAlive(i, j)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
			}
		}
		
		for (Cell cell : mustRevive) {
			cell.revive();
			statistics.recordRevive();
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			statistics.recordKill();
		}
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			statistics.recordRevive();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/* verifica se uma celula deve ser mantida viva */
	protected abstract boolean shouldKeepAlive(int i, int j); 
	/*{
		return (cells[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3);
	}*/

	/* verifica se uma celula deve (re)nascer */
	protected abstract boolean shouldRevive(int i, int j); 
	/*{
		return (!cells[i][j].isAlive())
				&& (numberOfNeighborhoodAliveCells(i, j) == 3);
	}*/

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	/*private*/protected int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		if(i == 0 || i == getHeight()){
			if(i == 0 ){
				if(j == 0){
					if (validPosition(getHeight(), getWidth())  && cells[getHeight()][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(getHeight(), 0)  && cells[getHeight()][0].isAlive()) {
						alive++;
					}
					if (validPosition(getHeight(), 1)  && cells[getHeight()][1].isAlive()) {
						alive++;
					}
					if (validPosition(0, getWidth())  && cells[0][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(1, getWidth())  && cells[1][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(1, 0)  && cells[1][0].isAlive()) {
						alive++;
					}
					if (validPosition(0, 1)  && cells[0][1].isAlive()) {
						alive++;
					}
					if (validPosition(1, 1)  && cells[1][1].isAlive()) {
						alive++;
					}
				}
				else if(j == getWidth()){
					if (validPosition(getHeight(), getWidth())  && cells[getHeight()][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(getHeight(), (getWidth()-1))  && cells[getHeight()][(getWidth()-1)].isAlive()) {
						alive++;
					}
					if (validPosition(getHeight(), 0)  && cells[getHeight()][0].isAlive()) {
						alive++;
					}
					if (validPosition(0, (getWidth()-1))  && cells[0][(getWidth()-1)].isAlive()) {
						alive++;
					}
					if (validPosition(1, (getWidth()-1))  && cells[1][(getWidth()-1)].isAlive()) {
						alive++;
					}
					if (validPosition(1, getWidth())  && cells[1][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(0, 0)  && cells[0][0].isAlive()) {
						alive++;
					}
					if (validPosition(1, 0)  && cells[1][0].isAlive()) {
						alive++;
					}
				}
				else {
					for(int a = 0; a <= 1;a++){
						for(int b = (j-1); b <= (j+1); b++){
							if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
								alive++;
							}
						}
					}
					for(int b = (j-1); b <= (j+1); b++){
						if (validPosition(getHeight(), b)  && (!(b == j)) && cells[getHeight()][b].isAlive()) {
							alive++;
						}
					}
				}
			}
			else if(i == getHeight() ){
				if(j == 0){
					if (validPosition(getHeight(), getWidth())  && cells[getHeight()][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition((getHeight()-1), getWidth())  && cells[(getHeight()-1)][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition((getHeight()-1), 0)  && cells[(getHeight()-1)][0].isAlive()) {
						alive++;
					}
					if (validPosition(0, getWidth())  && cells[0][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(getHeight(), 1)  && cells[getHeight()][1].isAlive()) {
						alive++;
					}
					if (validPosition(0, 0)  && cells[0][0].isAlive()) {
						alive++;
					}
					if (validPosition((getHeight()-1), 1)  && cells[(getHeight()-1)][1].isAlive()) {
						alive++;
					}
					if (validPosition(0, 1)  && cells[0][1].isAlive()) {
						alive++;
					}
				}
				else if(j == getWidth()){
					if (validPosition((getHeight()-1), 0)  && cells[(getHeight()-1)][0].isAlive()) {
						alive++;
					}
					if (validPosition((getHeight()-1), (getWidth()-1))  && cells[(getHeight()-1)][(getWidth()-1)].isAlive()) {
						alive++;
					}
					if (validPosition(getHeight(), 0)  && cells[getHeight()][0].isAlive()) {
						alive++;
					}
					if (validPosition(0, getWidth())  && cells[0][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(0, (getWidth()-1))  && cells[0][(getWidth()-1)].isAlive()) {
						alive++;
					}
					if (validPosition((getHeight()-1), getWidth())  && cells[(getHeight()-1)][getWidth()].isAlive()) {
						alive++;
					}
					if (validPosition(0, 0)  && cells[0][0].isAlive()) {
						alive++;
					} 
					if (validPosition(getHeight(), (getWidth()-1))  && cells[getHeight()][(getWidth()-1)].isAlive()) {
						alive++;
					}
				}
				else {
					for(int a = (getHeight()-1); a <= getHeight();a++){
						for(int b = (j-1); b <= (j+1); b++){
							if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
								alive++;
							}
						}
					}
					for(int b = (j-1); b <= (j+1); b++){
						if (validPosition(0, b)  && (!(b == j)) && cells[0][b].isAlive()) {
							alive++;
						}
					}
				}
			}
		}
		else if(j == 0 || j == getWidth()){
			if(j == 0){
				for(int b = 0; b <= 1;b++){
					for(int a = (i-1); a <= (i+1); a++){
						if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
							alive++;
						}
					}
				}
				for(int a = (j-1); a <= (j+1); a++){
					if (validPosition(a, getWidth())  && (!(a == i)) && cells[a][getWidth()].isAlive()) {
						alive++;
					}
				}
				
			}
			else if(j == getWidth()){
				for(int b = (getWidth()-1); b <= getWidth();b++){
					for(int a = (i-1); a <= (i+1); a++){
						if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
							alive++;
						}
					}
				}
				for(int a = (j-1); a <= (j+1); a++){
					if (validPosition(a,0)  && (!(a == i)) && cells[a][0].isAlive()) {
						alive++;
					}
				}
			}
		}
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}

	/* Metodos de acesso as propriedades height e width */
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
