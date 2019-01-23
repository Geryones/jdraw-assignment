package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.Figure;

public class LineTool extends AbstractDrawTool implements DrawTool {


    private Line newLine=null;

    public LineTool(DrawContext context, String name, String icon){
        super(context, name, icon);

    }

    @Override protected Figure newFigure(int x, int y) {
        return new Line(x,y,x,y);
    }


}
