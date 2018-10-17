package jdraw.handleStates;


import jdraw.figures.Line;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 16/10/2018.
 */
public class LineEndHandleState extends AbstractHandleState implements FigureHandle {

    public LineEndHandleState(Figure owner){
        super(owner);
    }

    @Override public Point getLocation() {
        Line l = (Line) getOwner();
        return l.getEnd();
    }

    @Override public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Line l = (Line) getOwner();
        l.setEnd(new Point(x, y));
    }
}
