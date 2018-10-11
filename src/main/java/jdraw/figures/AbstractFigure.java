package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import jdraw.handles.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractFigure implements Figure {



    private CopyOnWriteArrayList<FigureListener> myFigureListener = new CopyOnWriteArrayList<>();

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new WestHandle(this));
        handles.add(new EastHandle(this));
        handles.add(new NorthHandle(this));

        handles.add(new NorthEastHandle(this));
        handles.add(new SouthWestHandle(this));
        handles.add(new SouthEastHandle(this));
        handles.add(new SouthHandle(this));

        handles.add(new NorthWestHandle(this));
        return handles;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        myFigureListener.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        myFigureListener.remove(listener);
    }

    @Override
    public Figure clone() {
        return null;
    }

    public void notifyFigureChangeListeners(){
        FigureEvent figureEvent = new FigureEvent(this);

        for (FigureListener listener : myFigureListener){
            listener.figureChanged(figureEvent);
        }
    }




}
