package jdraw.handleStates;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.HandleStateInterface;

import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 16/10/2018.
 */
public abstract class AbstractHandleState implements HandleStateInterface {

    private Figure owner;
    int startX, startY;

    public AbstractHandleState(Figure owner){
        this.owner = owner;
    }


    @Override public Figure getOwner() {
        return owner;
    }


    @Override public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        startX = x;
        startY = y;
    }

}
