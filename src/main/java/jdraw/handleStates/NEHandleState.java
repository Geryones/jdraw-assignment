package jdraw.handleStates;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.HandleStateInterface;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 16/10/2018.
 */
public class NEHandleState extends AbstractHandleState implements FigureHandle {

    public NEHandleState(Figure owner){
        super(owner);
    }

    @Override public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width, r.y );
    }

    @Override public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(x,y),
                new Point(r.x,r.y + r.height));

        if (y > r.y + r.height ){
            getOwner().swapHorizontal();
        }


        if (x < r.x){
            getOwner().swapVertical();
        }


    }

    @Override public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }
}
