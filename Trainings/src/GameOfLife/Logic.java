package GameOfLife;

import GameOfLife.ThreadUtils.ThreadUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Logic {
    private State state;
    private State stateForNewChanges;
    private CyclicBarrier waitingForThreadsToProcessCurrentGeneration;
    private GenerationWasUpdatedListener generationWasUpdatedListener;

    public Logic(State state,GenerationWasUpdatedListener generationWasUpdatedListener){
        this.state = state;
        stateForNewChanges = new State(state.getGameBoardRows(),state.getGameBoardColumns());
        waitingForThreadsToProcessCurrentGeneration = new CyclicBarrier(state.getGameBoardColumns() * state.getGameBoardRows(),
                                                                 this::refreshedToNewGameBoardState);
        this.generationWasUpdatedListener = generationWasUpdatedListener;
        setUpThreadsForIndividualCellProcessing();
    }

    private void refreshedToNewGameBoardState(){
        state.pointToANewGameBoard(stateForNewChanges);
        stateForNewChanges = new State(state.getGameBoardRows(),state.getGameBoardColumns());
        ThreadUtils.pauseThreadFor(100);
        signallGenerationUpdate();
    }

    private void signallGenerationUpdate() {
        generationWasUpdatedListener.generationWasUpdated();
    }

    private void setUpThreadsForIndividualCellProcessing(){
        for(int row=0; row<state.getGameBoardRows(); row++){
            for(int col=0; col<state.getGameBoardColumns(); col++){
                int finalRow = row;
                int finalCol = col;
                new Thread(()-> startToProcessAppropriateCellOfBoard(finalRow, finalCol)).start();
            }
        }
    }

    private void startToProcessAppropriateCellOfBoard(int finalRow, int finalCol) {
        while(true){
            processAppropriateCellOfBoard(finalRow, finalCol);
        }
    }

    private void processAppropriateCellOfBoard(int finalRow, int finalCol) {
        try {
            tryToprocessAppropriateCellOfBoard(finalRow, finalCol);
            waitingForThreadsToProcessCurrentGeneration.await();
        } catch (InterruptedException  | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void tryToprocessAppropriateCellOfBoard(int row, int col){
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
