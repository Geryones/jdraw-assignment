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
public class Handle implements FigureHandle {

    FigureHandle state;

    protected final int HANDLESIZE = 10;

    public Handle(FigureHandle state){
        this.state=state;
    }

    @Override public Figure getOwner() {
        return state.getOwner();
    }

    @Override public Point getLocation() {
        return state.getLocation();
    }

    @Override
    public void draw(Graphics g){
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - HANDLESIZE / 2, loc.y - HANDLESIZE / 2, HANDLESIZE, HANDLESIZE);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - HANDLESIZE / 2, loc.y - HANDLESIZE / 2, HANDLESIZE, HANDLESIZE);
    }

    @Override public Cursor getCursor() {
        return state.getCursor();
    }

    @Override
    public boolean contains(int x, int y){
        Rectangle rect = new Rectangle(
                (int) getLocation().getX(),
                (int) getLocation().getY(),
                HANDLESIZE, HANDLESIZE);
        return rect.contains(x, y);
    }

    @Override public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.startInteraction(x, y, e, v);
    }

    @Override public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.dragInteraction(x, y, e, v);
    }

    @Override public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.stopInteraction(x, y, e, v);
    }

    public FigureHandle getState() {
        return state;
    }

    public void setState(FigureHandle state) {
        this.state = state;
    }
}
