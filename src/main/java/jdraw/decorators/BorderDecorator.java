package jdraw.decorators;

import jdraw.figures.Rect;
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

    @Override
    public Rectangle getBounds() {
       Point origin = new Point(super.getBounds().getLocation().x - 2, super.getBounds().getBounds().y - 2);
       Point diagonal = new Point(super.getBounds().getLocation().x +super.getBounds().width + 2, super.getBounds().y  + super.getBounds().height + 2);
       Rectangle rect = new Rectangle();
       rect.setFrameFromDiagonal(origin, diagonal);
       return rect;
    }


    private void drawBorder(Graphics g){
        Point top = new Point(super.getBounds().getLocation().x + super.getBounds().width, super.getBounds().y );
        Point middle = new Point(super.getBounds().getLocation().x +super.getBounds().width, super.getBounds().y  + super.getBounds().height);
        Point left = new Point(super.getBounds().getLocation().x , super.getBounds().y  + super.getBounds().height);
        g.drawLine(top.x , top.y , middle.x , middle.y );
        g.drawLine(middle.x, middle.y, left.x, left.y);

    }
}
