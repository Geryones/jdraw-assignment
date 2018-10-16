package jdraw.handles.old;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 09/10/2018.
 */
public class NorthEastHandle extends AbstractFigureHandle implements FigureHandle {

    public NorthEastHandle(Figure owner){
        super(owner);
    }

    @Override
    public Point getLocation() {
        return new Point(owner.getBounds().x + owner.getBounds().width, owner.getBounds().y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        corner = new Point(r.x, r.y +r.height);
    }




    @Override
    public Cursor getCursor(){
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }
}
