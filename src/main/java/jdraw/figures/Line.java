package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Line implements Figure {

   private final Line2D line;
    private CopyOnWriteArrayList<FigureListener> myFigureListener = new CopyOnWriteArrayList<>();

    /**
     * Creates a new Line with start and end coordinates
     * @param startX x coordinate of the starting point
     * @param startY y coordinate of the starting point
     * @param endX x coordinate of the end point
     * @param endY y coordinate of the end point
     */
    public Line(int startX, int startY, int endX, int endY) {line = new Line2D.Double(startX,startY,endX,endY);}

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
       g.drawLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2());

    }

    @Override
    public void move(int dx, int dy) {
        if (dx==0 && dy==0){
            line.setLine(line.getX1()+dx,line.getY1()+dy,line.getX2()+dx,line.getY2()+dy);
            notifyFigureChangeListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return line.contains(x,y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line.setLine(origin,corner);
        notifyFigureChangeListeners();
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        myFigureListener.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        myFigureListener.remove(listener);
    }

    @Override
    public Figure clone() {
        return null;
    }

    /**
     * Notifies all the listeners
     */
    public void notifyFigureChangeListeners(){
        FigureEvent figureEvent = new FigureEvent(this);

        for (FigureListener listener : myFigureListener){
            listener.figureChanged(figureEvent);
        }

    }
}
