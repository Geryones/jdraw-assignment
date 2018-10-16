package jdraw.handles.old.states;

import jdraw.framework.Figure;
import jdraw.framework.HandleStateInterface;

import java.awt.*;

public abstract class AbstractHandleState implements HandleStateInterface {
    protected Point point, corner;
    protected Cursor cursor;
    protected Figure owner;


    public AbstractHandleState(Figure owner){
        this.owner = owner;
    }



    @Override
    public abstract Cursor getCursor();


    @Override
    public Point getCorner() {
        return corner;
    }




}
