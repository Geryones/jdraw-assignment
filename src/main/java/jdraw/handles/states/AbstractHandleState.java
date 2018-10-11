package jdraw.handles.states;

import jdraw.framework.Figure;
import jdraw.framework.HandleStateInterface;

import java.awt.*;

public class AbstractHandleState implements HandleStateInterface {
    protected Point point, corner;
    protected Cursor cursor;
    protected Figure owner;


    public AbstractHandleState(Figure owner){
        this.owner = owner;
    }

    public AbstractHandleState(AbstractHandleState state){
        this.cursor = state.cursor;
        this.point = state.point;
        this.corner = state.corner;
        this.owner = state.owner;
    }

    @Override
    public AbstractHandleState evalState(int x, int y) {
        return null;
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
