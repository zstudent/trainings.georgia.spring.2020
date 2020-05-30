import java.util.Random;

public class State {
    private Field field;
    private Random rand;
    private int size;

    public State(int size) {
        this.size = size;
        field = new Field(size);
        rand = new Random();
        generateRandomSpots();
    }

    private void generateRandomSpots() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field.setGridSpot(i, j, rand.nextBoolean());
            }
        }
    }

    public void checkWholeField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                checkNeighbours(i, j);
            }
        }
        updateGrid();
    }

    private void updateGrid() {
        boolean[][] tmp = field.getGrid();
        field.setGrid(field.getUpdGrid());
        field.setUpdGrid(tmp);
    }

    private void checkNeighbours(int x, int y) {
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                if (inBounds(x + i, y + j) && field.getGridSpot(x + i, y + j)) count++;
            }
        }
        checkSurvivesOrNot(count, x, y);
    }

    private void checkSurvivesOrNot(int count, int x, int y) {
        if(field.getGridSpot(x, y)){
            field.setUpdGridSpot(x, y, count == 3 || count == 2);
        } else {
            field.setUpdGridSpot(x, y, count == 3);
        }
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
