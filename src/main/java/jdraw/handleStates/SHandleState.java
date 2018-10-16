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
public class SHandleState extends AbstractHandleState implements FigureHandle {

   public SHandleState(Figure owner){
       super(owner);
   }

    @Override public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);

    }

    @Override public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y + r.height);
    }

    @Override public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(r.x, y),
                new Point(r.x + r.width, r.y));

        if (y < r.y){
            getOwner().swapHorizontal();
        }
    }

    @Override public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }
}
