package jdraw.framework;


import java.awt.*;
import java.awt.event.MouseEvent;

public interface HandleStateInterface {
    Cursor getCursor();
    Point getCorner();
    Figure getOwner();

    void dragInteraction(int x, int y, MouseEvent e, DrawView v);
    void startInteraction(int x, int y, MouseEvent e, DrawView v);
    void stopInteraction(int x, int y, MouseEvent e, DrawView v);
}
