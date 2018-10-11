package jdraw.framework;


import java.awt.*;

public interface HandleStateInterface {
    Cursor getCursor();
    Point getPoint();
    Point getCorner();

    void setPoint(Point point);
    void setCorner(Point corner);
    HandleStateInterface evalState(int x, int y);
}
