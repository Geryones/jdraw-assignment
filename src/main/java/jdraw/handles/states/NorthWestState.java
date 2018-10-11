package jdraw.handles.states;

import jdraw.framework.Figure;
import jdraw.framework.HandleStateInterface;

import java.awt.*;

public class NorthWestState extends AbstractHandleState implements HandleStateInterface {

    public NorthWestState(AbstractHandleState state) {
        super(state);
    }

    public NorthWestState(Figure owner){
        super(owner);
        cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public AbstractHandleState evalState(int x, int y){
        if (x > (owner.getBounds().x + owner.getBounds().width)){
            return new NorthEastState(this);
        }
        return this;
    }

}
