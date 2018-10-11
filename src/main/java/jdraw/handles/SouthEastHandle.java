package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 09/10/2018.
 */
public class SouthEastHandle extends AbstractFigureHandle implements FigureHandle {

    public SouthEastHandle(Figure owner){
        super(owner);
    }

    @Override
    public Point getLocation() {
        return new Point( owner.getBounds().x + owner.getBounds().width, owner.getBounds().y + owner.getBounds().height );
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        corner = r.getLocation();
    }




    @Override
    public Cursor getCursor(){
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }
}
