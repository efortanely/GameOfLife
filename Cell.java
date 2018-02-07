import java.awt.Graphics;

public class Cell {
	private int x, y;
	private int row, column;
	private int cellWidth;

	// defines coordinate in terms of ints, row/column determined relative to
	// x,y coordinate
	public Cell(int x, int y, int cellWidth) {
		this.x = x;
		this.y = y;
		this.cellWidth = cellWidth;
		this.row = y / this.cellWidth;
		this.column = x / this.cellWidth;
	}

	public void draw(Graphics g) {
		g.fillRect(this.x, this.y, this.cellWidth, this.cellWidth);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Cell))
			return false;
		else if (obj == this)
			return true;
		else {
			Cell other = (Cell) obj;
			return this.x == other.getX() && this.y == other.getY();
		}
	}
}
