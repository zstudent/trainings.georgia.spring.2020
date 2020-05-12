package columns;

import java.util.Arrays;

public class Logic {
	
	State state;
	
	public Logic(State state) {
		this.state = state;
	}
	
	public void switchCubeDown() {
		int i =  state.fig.c[1];
		state.fig.c[1] = state.fig.c[3];
		state.fig.c[3] = state.fig.c[2];
		state.fig.c[2] = i;
	}
	
	public void switchCubeUp() {
		int i = state.fig.c[1];
		state.fig.c[1] = state.fig.c[2];
		state.fig.c[2] = state.fig.c[3];
		state.fig.c[3] = i;
	}
	
	public void moveColumn() {
		if((state.fig.y<state.HEIGHT-2) && (state.newField[state.fig.x][state.fig.y+3]==0)) {
			state.fig.y++;
		}
	}
	
	public void moveColumnRight() {
		if ((state.fig.x<state.WIDTH) && (state.newField[state.fig.x+1][state.fig.y+2]==0)) {
		    state.fig.x++;
		}
	}
	
	public void moveColumnLeft() {
		if ((state.fig.x>1) && (state.newField[state.fig.x-1][state.fig.y+2]==0)) {
		    state.fig.x--;
		}
	}
	
	public void dropColumn() {
		int zz;
        if (state.fig.y < state.HEIGHT-2) {
            zz = state.HEIGHT;
            while (state.newField[state.fig.x][zz]>0) zz--;
            state.DScore = (((state.Level+1)*(state.HEIGHT*2-state.fig.y-zz) * 2) % 5) * 5;
            state.fig.y = zz-2;
        }
	}
	
	public void packField() {
    	int i,j,n;
        for (i=1; i<=state.WIDTH; i++) {
            n = state.HEIGHT;
            for (j=state.HEIGHT; j>0; j--) {
                if (state.oldField[i][j]>0) {
                    state.newField[i][n] = state.oldField[i][j];
                    n--;
                }
            };
            for (j=n; j>0; j--) state.newField[i][j] = 0;
        }
    }
	
	public void pasteFigure() {
        state.newField[state.fig.x][state.fig.y] = state.fig.c[1];
        state.newField[state.fig.x][state.fig.y+1] = state.fig.c[2];
        state.newField[state.fig.x][state.fig.y+2] = state.fig.c[3];
    }
	
	public boolean isFigureFitTheField() {
		return (state.fig.y<state.HEIGHT-2) && (state.newField[state.fig.x][state.fig.y+3]==0);
	}
	
	void testField() {
        int i,j;
        for (i=1; i<=state.HEIGHT; i++) {
            for (j=1; j<=state.WIDTH; j++) {
                state.oldField[j][i] = state.newField[j][i];
            }
        }
        for (i=1; i<=state.HEIGHT; i++) {
            for (j=1; j<=state.WIDTH; j++) {
                if (state.newField[j][i]>0) {
                    checkNeighbours(j,i-1,j,i+1,i,j);
                    checkNeighbours(j-1,i,j+1,i,i,j);
                    checkNeighbours(j-1,i-1,j+1,i+1,i,j);
                    checkNeighbours(j+1,i-1,j-1,i+1,i,j);
                }
            }
        }
    
	}
	
	
	// TODO -> moving checkNeighbours and TestField here
	void checkNeighbours(int a, int b, int c, int d, int i, int j) {
        if ((state.newField[j][i]==state.newField[a][b]) && (state.newField[j][i]==state.newField[c][d])) {
            state.oldField[a][b] = 0;
//            view.DrawBox(a,b,8);
            state.oldField[j][i] = 0;
//            view.DrawBox(j,i,8);
            state.oldField[c][d] = 0;
//            view.DrawBox(c,d,8);
            state.NoChanges = false;
            state.Score += (state.Level+1)*10;
            state.k++;
        };
    }
	
	public void removeNeighbours() {
		if(!state.NoChanges) {
			packField();
			state.Score += state.DScore;
			if (state.k>=state.FigToDrop) {
	            state.k = 0;
	            if (state.Level<state.MaxLevel) state.Level++;
	        }
			state.NoChanges = true;
		}
	}
	
    boolean isFieldFull() {
    	int i;
        for (i=1; i<=state.WIDTH; i++) {
            if (state.newField[i][3]>0)
                return true;
        }
        return false;
    }
    
    public void increaseLevel() {
		if(state.Level < state.MaxLevel) state.Level++;
		state.k = 0;
	}
    
    public void decreaseLevel() {
		if(state.Level > 0) state.Level--;
		state.k = 0;
	}
    
    public void pauseGame() {
		if(state.paused) state.paused = false;
		else state.paused = true;
	}
    
    public long gameSpeed() {
    	return state.MaxLevel - state.Level == 0 ? 300 : 250 * (state.MaxLevel - state.Level);
    }
    
}
