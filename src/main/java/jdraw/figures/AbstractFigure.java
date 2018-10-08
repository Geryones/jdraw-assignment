package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractFigure implements Figure {


    private CopyOnWriteArrayList<FigureListener> myFigureListener = new CopyOnWriteArrayList<>();


    @Override
    public List<FigureHandle> getHandles() {
        return null;
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
