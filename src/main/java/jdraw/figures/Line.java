package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import jdraw.handleStates.Handle;
import jdraw.handleStates.NWHandleState;
import jdraw.handleStates.SEHandleState;


import java.awt.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

public class Line extends AbstractFigure implements Figure {

    private final Line2D line;

    /**
     * Creates a new Line with start and end coordinates
     *
     * @param startX x coordinate of the starting point
     * @param startY y coordinate of the starting point
     * @param endX   x coordinate of the end point
     * @param endY   y coordinate of the end point
     */
    public Line(int startX, int startY, int endX, int endY) {
        line = new Line2D.Double(startX, startY, endX, endY);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());

    }

    @Override
    public void move(int dx, int dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
        notifyFigureChangeListeners();
    }

    @Override
    public boolean contains(int x, int y) {
        int hitBoxSize=10;
        int boxX = x - hitBoxSize / 2;
        int boxY = y - hitBoxSize / 2;
        return line.intersects(boxX, boxY, hitBoxSize, hitBoxSize);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line.setLine(origin, corner);
        notifyFigureChangeListeners();
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new Handle(new NWHandleState(this)));
        handles.add(new Handle(new SEHandleState(this)));
        return handles;
    }

    @Override
    public void swapHorizontal(){
        Handle NW = (Handle) handles.get(0);
        Handle SE = (Handle) handles.get(1);

        FigureHandle NWState = NW.getState();

        NW.setState(SE.getState());
        SE.setState(NWState);

    }

    @Override
    public void swapVertical(){
      swapHorizontal();
    }
}


