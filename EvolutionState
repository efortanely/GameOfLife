import java.util.ArrayList;

public class EvolutionState extends ColorfulCellularAutomaton{
	private static final long serialVersionUID = 1L;
	private Cell[][] state;
	private ArrayList<Cell> aliveCells = new ArrayList<Cell>();
	private int vCells, hCells;
	
	public EvolutionState(int vCells, int hCells){
		state = new Cell[vCells][hCells];
		this.vCells = vCells;
		this.hCells = hCells;
	}
	
	//adds cell to arraylist of alive cells, corresponding r,c location based on x,y coordinate, increases number of cells
	
	public void addCell(Cell inputCell){
		boolean existingCell = false;
		for(Cell cell : getAllCells()){
			if(cell.getX() == inputCell.getX() && cell.getY() == inputCell.getY()){
				existingCell = true;
				break;
			}
		}
		
		if(!existingCell){
			aliveCells.add(inputCell);
			state[inputCell.getRow()][inputCell.getColumn()] = inputCell;
		}
	}
	
	//removes cell from arraylist of alive cells, corresponding r,c location based on x,y coordinate, decreases number of cells
	
	public void removeCell(Cell celltoRemove){
		aliveCells.remove(celltoRemove);
		state[celltoRemove.getRow()][celltoRemove.getColumn()] = null;
	}
	
	public void sizeChange(int newNumVerticalCells, int newNumHorizontalCells){
		Cell[][] newSizeState = new Cell[newNumVerticalCells][newNumHorizontalCells];		
		for(int r = 0; r < vCells; r++)
		for(int c = 0; c < hCells; c++)
			try{ newSizeState[r][c] = state[r][c]; }
			catch(ArrayIndexOutOfBoundsException cellOutOfRange){ if(state[r][c] != null) removeCell(state[r][c]); }		
		vCells = newNumVerticalCells;
		hCells = newNumHorizontalCells;
		state = newSizeState.clone();
	}
	
	//creates an evolution state containing all points of copied state
	
	@Override
	public EvolutionState clone(){
		EvolutionState state = new EvolutionState(vCells, hCells);
		for(Cell cell: getAllCells()) state.addCell(cell);
		return state;
	}
	
	//getters
	
	public Cell getCell(int cellRow, int cellColumn){ return state[cellRow][cellColumn]; }
	
	public ArrayList<Cell> getAllCells(){ return aliveCells; }
}
