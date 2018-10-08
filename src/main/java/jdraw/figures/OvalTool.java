package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

import java.awt.event.MouseEvent;

public class OvalTool extends AbstractDrawTool implements DrawTool {
    public OvalTool(DrawContext context){
        super(context,"oval");
    }


    @Override protected Figure newFigure(int x, int y) {
        return new Oval(x, y,0,0);
    }


}
