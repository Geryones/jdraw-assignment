package jdraw.handleStates;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by Geryones on 16/10/2018.
 */
public abstract class AbstractHandleState implements FigureHandle {

    private Figure owner;
    int startX, startY;

    public AbstractHandleState(Figure owner){
        this.owner = owner;
    }


    @Override public Figure getOwner() {
        return owner;
    }


    @Override public void draw(Graphics g) {

    }

    @Override public boolean contains(int x, int y) {
        return false;
    }

    @Override public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        startX = x;
        startY = y;
    }



    @Override public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

}
