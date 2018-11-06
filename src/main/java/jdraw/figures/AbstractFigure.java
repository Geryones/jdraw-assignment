package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import jdraw.handleStates.*;
import jdraw.utils.SerializableClone;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractFigure implements Figure{

    public Group parent = null;

    private transient CopyOnWriteArrayList<FigureListener> myFigureListener = new CopyOnWriteArrayList<>();
    List<FigureHandle> handles = new LinkedList<>();

    private Object readResolve() {
        this.myFigureListener = new CopyOnWriteArrayList<>();
        return this;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        myFigureListener.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        myFigureListener.remove(listener);
    }

    @Override public  Figure clone() {
        return (Rect) SerializableClone.clone(this);
    }

    public void notifyFigureChangeListeners(){
        FigureEvent figureEvent = new FigureEvent(this);

        for (FigureListener listener : myFigureListener){
            listener.figureChanged(figureEvent);
        }
    }

    @Override
    public List<FigureHandle> getHandles() {
        if (handles.isEmpty()){
            initHandles();
        }

        return handles;
    }

    public void initHandles(){
        handles.add(new Handle(new NWHandleState(this)));
        handles.add(new Handle(new NEHandleState(this)));
        handles.add(new Handle(new SWHandleState(this)));
        handles.add(new Handle(new SEHandleState(this)));
        handles.add(new Handle(new WHandleState(this)));
        handles.add(new Handle(new EHandleState(this)));
        handles.add(new Handle(new NHandleState(this)));
        handles.add(new Handle(new SHandleState(this)));
    }



    public void swapVertical(){
        Handle NW = (Handle) handles.get(0);
        Handle NE = (Handle) handles.get(1);
        Handle SW = (Handle) handles.get(2);
        Handle SE = (Handle) handles.get(3);
        Handle W = (Handle) handles.get(4);
        Handle E = (Handle) handles.get(5);

        FigureHandle NWState = NW.getState();
        FigureHandle SWState = SW.getState();
        FigureHandle WState = W.getState();

        NW.setState(NE.getState());
        NE.setState(NWState);

        SW.setState(SE.getState());
        SE.setState(SWState);

        W.setState(E.getState());
        E.setState(WState);


    }

    public void swapHorizontal(){
        Handle NW = (Handle) handles.get(0);
        Handle NE = (Handle) handles.get(1);
        Handle SW = (Handle) handles.get(2);
        Handle SE = (Handle) handles.get(3);
        Handle N = (Handle) handles.get(6);
        Handle S = (Handle) handles.get(7);

        FigureHandle NWState = NW.getState();
        FigureHandle NEState = NE.getState();
        FigureHandle NState = N.getState();

        NW.setState(SW.getState());
        SW.setState(NWState);

        NE.setState(SE.getState());
        SE.setState(NEState);

        N.setState(S.getState());
        S.setState(NState);


    }




}
