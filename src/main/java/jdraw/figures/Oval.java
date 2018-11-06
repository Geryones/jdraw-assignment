package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.utils.SerializableClone;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Oval extends AbstractFigure implements Figure{
    private final Ellipse2D oval;


    public Oval(int x, int y, int w , int h){
        oval= new Ellipse2D.Double(x,y,0,0);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int)oval.getX(),(int)oval.getY(),(int)oval.getWidth(),(int)oval.getHeight());
        g.setColor(Color.BLACK);
        g.drawOval((int)oval.getX(),(int)oval.getY(),(int)oval.getWidth(),(int)oval.getHeight());
    }

    @Override
    public void move(int dx, int dy) {
        if (dx==0 && dy==0){
            return;
        }
        oval.setFrame(oval.getX()+dx,oval.getY()+dy,oval.getWidth(),oval.getHeight());
        notifyFigureChangeListeners();
    }

    @Override
    public boolean contains(int x, int y) {
        return oval.contains(x,y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        //oval.setFrameFromCenter(origin,corner);
        oval.setFrameFromDiagonal(origin,corner);
        notifyFigureChangeListeners();
    }

    @Override
    public Rectangle getBounds() {
        return oval.getBounds();
    }

}
