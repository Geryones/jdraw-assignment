package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.states.NorthEastState;
import jdraw.handles.states.NorthWestState;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 09/10/2018.
 */
public class NorthWestHandle extends AbstractFigureHandle implements FigureHandle {

    public NorthWestHandle(Figure owner){
        super(owner);
        state= new NorthWestState(owner);
    }

    public Point getLocation(){
        return owner.getBounds().getLocation();
    }

    @Override public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        corner = new Point(r.x + r.width, r.y + r.height);
        //state.setCorner(new Point(r.x + r.width, r.y + r.height));
    }



    @Override
    public Cursor getCursor(){

        //return state.getCursor();
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        //state = state.evalState(x, y);
       // owner.setBounds(new Point(x , y), state.getCorner());
        owner.setBounds(new Point(x, y),corner);
    }
}
