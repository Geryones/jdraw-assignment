package jdraw.grid;

import jdraw.framework.DrawGrid;

import java.awt.*;

/**
 * Created by Geryones on 22/10/2018.
 */
public class SimpleGrid implements DrawGrid {

    @Override public Point constrainPoint(Point p) {
        int x = p.x;
        int y = p.y;

        x = (x / 10) * 10;
        y = (y / 10) * 10;

        return new Point(x,y);
    }

    @Override public int getStepX(boolean right) {
        System.out.println("get step x");
        return 1;
    }

    @Override public int getStepY(boolean down) {

        return 1;
    }

    @Override public void activate() {


    }

    @Override public void deactivate() {
    }

    @Override public void mouseDown() {

    }

    @Override public void mouseUp() {

    }
}
