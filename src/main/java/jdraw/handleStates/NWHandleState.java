package jdraw.handleStates;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.HandleStateInterface;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 16/10/2018.
 */
public class NWHandleState extends AbstractHandleState implements HandleStateInterface {

    public NWHandleState (Figure owner){
        super(owner);
    }

    @Override public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }


    @Override public Point getCorner() {
        return getOwner().getBounds().getLocation();
    }

    @Override public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(x,y),
                new Point(r.x+r.width,r.y+r.height));
    }

    @Override public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }


}
