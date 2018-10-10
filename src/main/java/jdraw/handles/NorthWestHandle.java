package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 09/10/2018.
 */
public class NorthWestHandle extends AbstractHandles implements FigureHandle {

    public NorthWestHandle(Figure owner){
        super(owner);
    }

    public Point getLocation(){
        return owner.getBounds().getLocation();
    }

    @Override public void startInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }
}
