package jdraw.decorators;

import jdraw.framework.Figure;

import java.awt.*;

/**
 * Created by Geryones on 20/11/2018.
 */
public class BorderDecorator extends AbstractDecorator implements Figure {

    public BorderDecorator(Figure inner) {
        super(inner);
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        drawBorder(g);
    }

    private void drawBorder(Graphics g){
        Point top = new Point(super.getBounds().getLocation().x+super.getBounds().width, super.getBounds().y);
        Point middle = new Point(super.getBounds().getLocation().x+super.getBounds().width, super.getBounds().y+super.getBounds().height);
        Point left = new Point(super.getBounds().getLocation().x, super.getBounds().y+super.getBounds().height);
        g.drawLine(top.x, top.y, middle.x, middle.y);
        g.drawLine(middle.x, middle.y, left.x, left.y);
    }
}
