public class Field {
    private boolean[][] grid;
    private boolean[][] updGrid;

    public Field(int size){
        grid = new boolean[size][size];
        updGrid = new boolean[size][size];
    }

    public void setGridSpot(int x, int y, boolean b){
        grid[x][y] = b;
    }

    public void setUpdGridSpot(int x, int y, boolean b){
        updGrid[x][y] = b;
    }

    public void setGrid(boolean [][] grid){
        this.grid = grid;
    }

    public void setUpdGrid(boolean [][] updGrid){
        this.updGrid = updGrid;
    }

    public boolean getGridSpot(int x, int y){
        return grid[x][y];
    }

    public boolean[][] getGrid(){
        return grid;
    }

    public boolean[][] getUpdGrid(){
        return updGrid;
    }
}
