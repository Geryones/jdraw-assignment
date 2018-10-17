package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.handleStates.*;

import java.awt.*;
import java.awt.geom.Line2D;


public class Line extends AbstractFigure implements Figure {

    private final Line2D line;
    private Point start, end;

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
        updatePoints();
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
        updatePoints();
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
        updatePoints();
        notifyFigureChangeListeners();
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        handles.clear();
        handles.add(new Handle(new LineStartHandleState(this)));
        handles.add(new Handle(new LineEndHandleState(this)));
        return handles;
    }



    private void updatePoints() {
        start = new Point((int) line.getX1(),(int) line.getY1());
        end = new Point((int) line.getX2(),(int) line.getY2());

    }

    public void setStart(Point start) {
        this.start = start;
        line.setLine(start, end);
        notifyFigureChangeListeners();

    }
    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
        line.setLine(start, end);
        notifyFigureChangeListeners();
    }

    @Override
    public void swapHorizontal(){

    }

    @Override
    public void swapVertical(){

    }
}


