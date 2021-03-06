package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

public class OvalTool extends AbstractDrawTool implements DrawTool {

    public OvalTool(DrawContext context, String name, String icon){
        super(context, name, icon);
    }


    @Override protected Figure newFigure(int x, int y) {
        return new Oval(x, y,0,0);
    }


}
