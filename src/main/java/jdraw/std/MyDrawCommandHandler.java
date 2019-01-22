package jdraw.std;


import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class MyDrawCommandHandler implements DrawCommandHandler {

    private Stack<DrawCommand> undoStack = new Stack<>();
    private Stack<DrawCommand> redoStack =  new Stack<>();
    private Script script = null;

    @Override
    public void addCommand(DrawCommand cmd) {
        redoStack.clear();
        if (script == null){
            undoStack.push(cmd);
        } else {
            script.commands.add(cmd);
        }

    }

    @Override
    public void undo() {
        if (undoPossible()){
            DrawCommand temp = undoStack.pop();
            redoStack.push(temp);
            temp.undo();



        }
    }

    @Override
    public void redo() {
        if (redoPossible()){
            DrawCommand temp = redoStack.pop();
            undoStack.push(temp);
            temp.redo();
        }

    }

    @Override
    public boolean undoPossible() {
        return undoStack.size() > 0;
    }

    @Override
    public boolean redoPossible() {
        return redoStack.size() > 0;
    }

    @Override
    public void beginScript() {
        if(script != null) throw new IllegalStateException();
        script = new Script();
    }

    @Override
    public void endScript() {
        if (script == null) throw new IllegalStateException();
        Script s = script;
        script = null;
        if (s.commands.size() > 0){
            if (s.commands.size() == 1){
                addCommand(s.commands.get(0));
            } else {
                addCommand(s);
            }
        }
    }

    @Override
    public void clearHistory() {
        redoStack = null;
        undoStack = null;

    }


    private static final class Script implements DrawCommand {

        private List<DrawCommand> commands = new LinkedList<>();


        @Override
        public void redo() {
            ListIterator<DrawCommand> it = commands.listIterator();
            while(it.hasNext()){
                it.next().redo();
            }
        }

        @Override
        public void undo() {
            int size = commands.size();
            ListIterator<DrawCommand> it = commands.listIterator(size);
            while (it.hasPrevious()) {
                it.previous().undo();
            }
        }
    }

}

