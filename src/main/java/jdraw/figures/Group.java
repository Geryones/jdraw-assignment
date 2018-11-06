package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;
import jdraw.utils.SerializableClone;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Geryones on 23/10/2018.
 */
public class Group extends AbstractFigure implements Figure, FigureGroup {

    List<Figure> parts;
    Rectangle rect = null;

    public Group(List<Figure> parts){
        this.parts = parts;
    }

    @Override public void draw(Graphics g) {
        for (Figure f : parts){
            f.draw(g);
        }
    }

    @Override public void move(int dx, int dy) {
        for (Figure f : parts){
            f.move(dx, dy);
        }

    }

    @Override public boolean contains(int x, int y) {
       return rect.contains(x, y);
    }

    @Override public void setBounds(Point origin, Point corner) {
        rect.setFrameFromDiagonal(origin, corner);
    }

    @Override public Rectangle getBounds() {
        rect = parts.get(0).getBounds();
       for (int i = 1; i < parts.size(); i ++){
           rect.add(parts.get(i).getBounds());
       }
        return rect;
    }

    @Override public Figure clone() {
        return (Group) SerializableClone.clone(this);
    }

    @Override public Iterable<Figure> getFigureParts() {
        return parts;
    }
}
