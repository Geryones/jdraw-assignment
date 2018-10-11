package jdraw.handles.states;

import jdraw.framework.HandleStateInterface;

import java.awt.*;


public class NorthEastState extends AbstractHandleState implements HandleStateInterface {

    public NorthEastState(AbstractHandleState state){
        super(state);
    }

    public NorthEastState(){
        cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

}
