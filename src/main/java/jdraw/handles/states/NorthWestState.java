package jdraw.handles.states;

import jdraw.framework.HandleStateInterface;

import java.awt.*;

public class NorthWestState extends AbstractHandleState implements HandleStateInterface {

    public NorthWestState(AbstractHandleState state) {
        super(state);
    }

    public NorthWestState(){
        cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

}
