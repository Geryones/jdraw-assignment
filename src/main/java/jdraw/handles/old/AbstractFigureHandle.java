package jdraw.handles.old;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.old.states.AbstractHandleState;

import java.awt.*;
import java.awt.event.MouseEvent;


/**
 * Created by Geryones on 09/10/2018.
 */
public abstract class AbstractFigureHandle implements FigureHandle {

    protected Figure owner = null;
    protected final int HANDLESIZE = 6;
    protected Point corner;

    protected AbstractHandleState state;


    public AbstractFigureHandle(Figure owner){
        this.owner = owner;

    }

    public Figure getOwner(){
        return owner;
    }

    @Override
    public void draw(Graphics g){
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - HANDLESIZE / 2, loc.y - HANDLESIZE / 2, HANDLESIZE, HANDLESIZE);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - HANDLESIZE / 2, loc.y - HANDLESIZE / 2, HANDLESIZE, HANDLESIZE);
    }

    @Override
    public boolean contains(int x, int y){
        Rectangle rect = new Rectangle(
                (int) getLocation().getX(),
                (int) getLocation().getY(),
                HANDLESIZE, HANDLESIZE);
        return rect.contains(x, y);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;

    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(new Point(x , y), corner);
    }
}
