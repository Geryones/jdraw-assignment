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
        System.out.println("get step y");
        return 1;
    }

    @Override public void activate() {
        System.out.println("SimpleGrid: activated()");

    }

    @Override public void deactivate() {
        System.out.println("SimpleGrid: deactivated()");
    }

    @Override public void mouseDown() {
        System.out.println("SimpleGrid: mouseDown()");
    }

    @Override public void mouseUp() {
        System.out.println("SimpleGrid: mouseUp()");
    }
}
