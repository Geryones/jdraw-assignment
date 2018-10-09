package jdraw.figures;

import jdraw.framework.Figure;
import java.awt.*;
import java.awt.geom.Line2D;

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
            line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
            notifyFigureChangeListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        int hitBoxSize=10;
        int boxX= x-hitBoxSize/2;
        int boxY = y-hitBoxSize/2;
        return line.intersects(boxX,boxY,hitBoxSize,hitBoxSize);
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
}


