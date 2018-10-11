package jdraw.handles.states;

import jdraw.framework.HandleStateInterface;

import java.awt.*;

public class AbstractHandleState implements HandleStateInterface {
    protected Point point, corner;
    protected Cursor cursor;


    public AbstractHandleState(){}

    public AbstractHandleState(AbstractHandleState state){
        this.cursor = state.cursor;
        this.point = state.point;
        this.corner = state.corner;
    }




    @Override
    public Cursor getCursor() {
        return null;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public Point getCorner() {
        return corner;
    }

    @Override
    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public void setCorner(Point corner) {
        this.corner = corner;
    }


}
