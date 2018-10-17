package jdraw.handleStates;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 16/10/2018.
 */
public class NHandleState extends AbstractHandleState implements FigureHandle {

    public NHandleState(Figure owner){
        super(owner);
    }

    @Override public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }

    @Override public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y);
    }

    @Override public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(r.x, y),
                new Point(r.x + r.width, r.y + r.height));
        if (y > r.y + r.height){
            getOwner().swapHorizontal();
        }
    }


}
