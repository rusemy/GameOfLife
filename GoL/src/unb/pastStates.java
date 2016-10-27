package unb;

class pastState{
	
	private Cell[][] state;
	private pastState nextState;
	int height, width;
	
	public pastState(int height, int width){
		Cell[][] state = new Cell[height][width];
		this.height = height;
		this.width = width;
		this.state = state;
		this.nextState = null;
	}
	
	public pastState getNext(){
		return nextState;
	}
	
	public void setNext(pastState nextState){
		this.nextState = nextState;
	}

	public Cell[][] getState(){
		return state;
	}
	
	public void setState(Cell[][] state){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				this.state[i][j] = state[i][j];
			}
		}
	}
}

public class pastStates{
	pastState lastState;
	int size;
	int height, width, maxStates;
	
	public void baseParameters(int height, int width, int maxStates){
		this.height = height;
		this.width = width;
		this.maxStates = maxStates;
	}
	
	public void add(Cell[][] state){		
		if(size < maxStates){
			if(size == 0){
				pastState newState = new pastState(height, width);
				newState.setState(state);
				lastState = newState;
			}else{
				pastState newState = new pastState(height, width);
				newState.setState(state);
				newState.setNext(lastState);
				lastState = newState;
			}
			
			size++;
		}else{
			pastState newState = new pastState(height, width);
			newState.setState(state);
			newState.setNext(lastState);
			pastState temp = lastState;
			lastState = newState;
			
			while(temp.getNext().getNext() != null){
				temp = temp.getNext();
			}
			
			temp.setNext(null);
		}
	}

	public Cell[][] getPast(){
		if(size > 0){
			pastState temp = lastState;
			lastState = lastState.getNext();
			size--;
			return temp.getState();
		}else{
			return null;
		}
	}
	
	public int size(){
		return size;
	}
}
