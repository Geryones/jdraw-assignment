package jdraw.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;

/**
 * Created by Geryones on 20/11/2018.
 */
public class AbstractDecorator implements Figure {
    private final Figure inner;

    public AbstractDecorator(Figure inner) {
        this.inner = inner;
    }

    public void draw(Graphics g) {
        inner.draw(g);
    }

    public void move(int dx, int dy) {
        inner.move(dx, dy);
    }

    public boolean contains(int x, int y) {
        return inner.contains(x, y);
    }

    public void setBounds(Point origin, Point corner) {
        inner.setBounds(origin, corner);
    }

    public Rectangle getBounds() {
        return inner.getBounds();
    }

    public List<FigureHandle> getHandles() {
        return inner.getHandles();
    }

    public void addFigureListener(FigureListener listener) {
        inner.addFigureListener(listener);
    }

    public void removeFigureListener(FigureListener listener) {
        inner.removeFigureListener(listener);
    }

    @Override public Figure clone() {
        return inner.clone();
    }

    public void swapHorizontal() {
        inner.swapHorizontal();
    }

    public void swapVertical() {
        inner.swapVertical();
    }

    public Figure getInner() {
        return inner;
    }
}
