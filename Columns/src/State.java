public class State {

    private int score;
    private int level;
    private int k;
    long dScore;
    private Figure figure;
    private Painter painter;
    static final int DEPTH =15;
    static final int WIDTH =7;
    static final int FigToDrop=33;
    static final int MaxLevel=7;
    boolean noChanges;
    private int[][] fNew;
    private int[][] fOld;

    public State(Painter painter){
        this.painter = painter;
        fNew = new int[WIDTH+2][DEPTH+2];
        fOld = new int[WIDTH+2][DEPTH+2];
        figure = new Figure();
        noChanges = true;
        level = 0;
        score = 0;
        k = 0;
    }

    public void initialPaint(){
        painter.paintAll(level, score, fNew, figure);
    }

    private int getFieldValue(){
        return fNew[figure.getX()][figure.getY()+3];
    }

    public void generateFigure(){
        figure = new Figure();
    }

    public Figure getFigure() {
        return figure;
    }

    public int getLevel() {
        return level;
    }

    public void changeLevel(int sign){
        if (level < MaxLevel) level +=sign;
        k=0;
        painter.showLevel(level);
    }

    public void PasteFigure() {
        for (int i = 0; i < 3; i++) {
            fNew[figure.getX()][figure.getY() + i] = figure.getColumn(i + 1);
        }
    }

    public boolean inRange(){
        return (figure.getY()<DEPTH-2) && (getFieldValue()==0);
    }

    public void drop(){
        painter.hideFigure(figure);
        dropFigure(figure);
        painter.drawFigure(figure);
    }

    public boolean getChangeStatus(){
        return noChanges;
    }

    public void setDsToZero(){
        dScore = 0;
    }

    private void CheckNeighbours(int a, int b, int c, int d, int i, int j) {
        if ((fNew[j][i]== fNew[a][b]) && (fNew[j][i]== fNew[c][d])) {
            fOld[a][b] = 0;
            painter.drawBox(a,b,8);
            fOld[j][i] = 0;
            painter.drawBox(j,i,8);
            fOld[c][d] = 0;
            painter.drawBox(c,d,8);
            noChanges = false;
            score += (level +1)*10;
            k++;
        };
    }

    private void TestField() {
        for (int i = 1; i<= DEPTH; i++) {
            for (int j = 1; j<= WIDTH; j++) {
                fOld[j][i] = fNew[j][i];
            }
        }
        for (int i = 1; i<= DEPTH; i++) {
            for (int j = 1; j<= WIDTH; j++) {
                if (fNew[j][i]>0) {
                    CheckNeighbours(j,i-1,j,i+1,i,j);
                    CheckNeighbours(j-1,i,j+1,i,i,j);
                    CheckNeighbours(j-1,i-1,j+1,i+1,i,j);
                    CheckNeighbours(j+1,i-1,j-1,i+1,i,j);
                }
            }
        }
    }

    public void checkForChange(){
        noChanges = true;
        TestField();
        if (!noChanges) {
            updateInfo();
        }
    }

    private void updateInfo(){
        Columns.delay(500);
        PackField();
        painter.drawField(fNew);
        score += dScore;
        painter.showScore(score);
        if (k >= FigToDrop) {
            k = 0;
            if (level <MaxLevel) level++;
            painter.showLevel(level);
        }
    }

    private void PackField() {
        int n;
        for (int i = 1; i<= WIDTH; i++) {
            n = DEPTH;
            for (int j = DEPTH; j>0; j--) {
                if (fOld[i][j]>0) {
                    fNew[i][n] = fOld[i][j];
                    n--;
                }
            };
            for (int j = n; j>0; j--) fNew[i][j] = 0;
        }
    }

    public boolean fullField() {
        int i;
        for (i=1; i<= WIDTH; i++) {
            if (fNew[i][3]>0)
                return true;
        }
        return false;
    }

    public void dropFigure(Figure f) {
        if (f.getY() < DEPTH - 2) {
            int tmp = DEPTH;
            while (fNew[f.getX()][tmp] > 0) tmp--;
            dScore = (((level + 1) * (DEPTH * 2 - f.getY() - tmp) * 2) % 5) * 5;
            f.setY(tmp - 2);
        }
    }

    public void moveRight(){
        if ((figure.getX()< WIDTH) && (fNew[figure.getX()+1][figure.getY()+2]==0)) {
            painter.hideFigure(figure);
            figure.setX(figure.getX()+1);
            painter.drawFigure(figure);
        }
    }

    public void moveLeft(){
        if ((figure.getX()>1) && (fNew[figure.getX()-1][figure.getY()+2]==0)) {
            painter.hideFigure(figure);
            figure.setX(figure.getX()-1);
            painter.drawFigure(figure);
        }
    }
}
