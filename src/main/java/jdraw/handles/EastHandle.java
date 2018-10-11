package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Geryones on 09/10/2018.
 */
public class EastHandle extends AbstractFigureHandle implements FigureHandle {

    public EastHandle(Figure owner){
        super(owner);
    }

    @Override
    public Point getLocation(){
       return new Point(owner.getBounds().x + owner.getBounds().width, owner.getBounds().y + owner.getBounds().height / 2);

    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
       corner = new Point(owner.getBounds().getLocation());
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(new Point(x, owner.getBounds().y +owner.getBounds().height), corner);
    }

    @Override
    public Cursor getCursor(){
        return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
    }
}