package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import java.awt.event.MouseEvent;

public class OvalTool extends AbstractDrawTool implements DrawTool {
    public OvalTool(DrawContext context){
        super(context,"oval");
    }


    @Override
    public void mouseDown(int x, int y, MouseEvent e) {

    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {

    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {

    }
}
