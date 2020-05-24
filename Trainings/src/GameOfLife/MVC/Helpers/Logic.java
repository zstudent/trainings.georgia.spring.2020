package GameOfLife.MVC.Helpers;

import GameOfLife.ThreadUtils.ThreadUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Logic {
    private State state;
    private State stateForNewChanges;
    private CyclicBarrier waitingToProcessAllCurrentGenerationCells;
    private GenerationWasUpdatedListener generationWasUpdatedListener;

    public Logic(State state){
        setUpMainStateAndStateForNewChanged(state);
        waitingToProcessAllCurrentGenerationCells = new CyclicBarrier(state.getGameBoardColumns() * state.getGameBoardRows(),
                                                                            this::refreshedToNewGameBoardState);
    }

    public void setGenerationUpdatedListener(GenerationWasUpdatedListener generationWasUpdatedListener){
        this.generationWasUpdatedListener = generationWasUpdatedListener;
    }

    public void startProcessingCells(){
        setUpThreadsForIndividualCellProcessing();
    }

    private void refreshedToNewGameBoardState(){
        updateStatePointers();
        signallGenerationUpdate();
        ThreadUtils.pauseThreadFor(1000);
    }

    private void updateStatePointers() {
        state.pointToANewGameBoard(stateForNewChanges);
        stateForNewChanges = new State(30,30);
    }

    private void setUpMainStateAndStateForNewChanged(State state) {
        this.state = state;
        state.fillStateWithRandomCells();
        stateForNewChanges = new State(state.getGameBoardRows(),state.getGameBoardColumns());
    }

    private void signallGenerationUpdate() {
        generationWasUpdatedListener.generationWasUpdated(state);
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
            waitingToProcessAllCurrentGenerationCells.await();
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
        stateForNewChanges.setNewStateOfCell(row,col,numberOfLivingNeighbours ==3 || numberOfLivingNeighbours ==2);
    }

    private void currentlyDeadCellProcess(int numberOfLivingNeighbours, int row, int col) {
        stateForNewChanges.setNewStateOfCell(row,col,numberOfLivingNeighbours == 3);
    }

    private int getNumberOfLivingNeighbours(int row,int col){
        int numberOfNeighbours = 0;
        for(int deltaRow = -1; deltaRow<=1; deltaRow++){
            for(int deltaCol = -1; deltaCol<=1; deltaCol++){
                if(state.isInBounds(row+deltaRow,col+deltaCol)
                        && state.isCellAlive(row+deltaRow,col+deltaCol)
                            && !(deltaCol==deltaRow && deltaRow==0)) numberOfNeighbours++;
            }
        }
        return numberOfNeighbours;
    }
}
