package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import javax.swing.*;
import java.awt.*;


public abstract class AbstractDrawTool implements DrawTool {

    protected static final String IMAGES ="/images/";
    protected DrawContext context;
    protected DrawView view;
    private String shapeName;
    protected Point anchor=null;

    public AbstractDrawTool(DrawContext context, String shapeName){
        this.context=context;
        this.view=context.getView();
        this.shapeName=shapeName;
    }

    /**
     * Activates the Rectangle Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Rectangle attributes
     */
    @Override
    public void activate() {
        this.context.showStatusText(shapeName + " Mode");
    }

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     * @see jdraw.framework.DrawTool#deactivate()
     */
    @Override
    public void deactivate() {
        this.context.showStatusText("");
    }



    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + shapeName+".png"));
    }

    @Override
    public String getName() {
        return shapeName;
    }
}
