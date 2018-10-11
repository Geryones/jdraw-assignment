package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 09/10/2018.
 */
public class NorthHandle extends AbstractHandles implements FigureHandle {

    public NorthHandle(Figure owner){
        super(owner);
    }

    @Override
    public Point getLocation() {
        return new Point(owner.getBounds().x + owner.getBounds().width / 2, owner.getBounds().y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override
    public Cursor getCursor(){
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }
}
