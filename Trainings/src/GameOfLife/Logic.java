package GameOfLife;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Logic {
    private State state;
    private State stateForNewChanges;
    private CyclicBarrier waitingForThreadsToProcessCurrentGeneration;

    public Logic(State state){
        this.state = state;
        stateForNewChanges = new State(state.getGameBoardRows(),state.getGameBoardColumns());
        waitingForThreadsToProcessCurrentGeneration = new CyclicBarrier(state.getGameBoardColumns() * state.getGameBoardRows(),
                                                                 this::refreshedToNewGameBoardState);
        setUpThreadsForIndividualCells();
    }

    private boolean refreshedToNewGameBoardState(){
        state.pointToANewGameBoard(stateForNewChanges);
        stateForNewChanges = new State(state.getGameBoardRows(),state.getGameBoardColumns());
        return true;
    }

    private void setUpThreadsForIndividualCells(){
        for(int row=0; row<state.getGameBoardRows(); row++){
            for(int col=0; col<state.getGameBoardColumns(); col++){
                int finalRow = row;
                int finalCol = col;
                new Thread(()->{
                    try {
                        processAppropriateCellOfBoard(finalRow, finalCol);
                        waitingForThreadsToProcessCurrentGeneration.await();
                    } catch (InterruptedException  | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    private void processAppropriateCellOfBoard(int row,int col){
        int numberOfLivingNeighbours = getNumberOfLivingNeighbours(row,col);
        processCurrentCellAccordingTo(numberOfLivingNeighbours,row,col);
    }

    private void processCurrentCellAccordingTo(int numberOfLivingNeighbours, int row, int col) {
        if(state.isCellAlive(row,col)){
            currentlyAliveCellProcess(numberOfLivingNeighbours, row, col);
        }else{
            currentlyDeadCellProcess(numberOfLivingNeighbours, row, col);
        }
    }

    private void currentlyAliveCellProcess(int numberOfLivingNeighbours, int row, int col) {
        if(numberOfLivingNeighbours<2) stateForNewChanges.setNewStateOfCell(row,col,false);
        else if(numberOfLivingNeighbours ==3 || numberOfLivingNeighbours ==2) stateForNewChanges.setNewStateOfCell(row,col,true);
        else if(numberOfLivingNeighbours > 3) stateForNewChanges.setNewStateOfCell(row,col,false);
    }

    private void currentlyDeadCellProcess(int numberOfLivingNeighbours, int row, int col) {
        boolean cellNewState =  numberOfLivingNeighbours == 3;
        stateForNewChanges.setNewStateOfCell(row,col,cellNewState);
    }

    private int getNumberOfLivingNeighbours(int row,int col){
        int numberOfNeighbours = 0;
        for(int deltaRow = -1; deltaRow<=1; deltaRow++){
            for(int deltaCol = -1; deltaCol<=1; deltaCol++){
                if(state.isInBounds(row+deltaRow,col+deltaCol)
                        && state.isCellAlive(row+deltaRow,col+deltaCol)) numberOfNeighbours++;
            }
        }
        return numberOfNeighbours;
    }

}
