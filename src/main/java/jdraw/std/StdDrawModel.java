/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import jdraw.framework.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Jurij
 *
 */
public class StdDrawModel implements DrawModel {

	private LinkedList<Figure> myFigures = new LinkedList<>();
	private CopyOnWriteArrayList<DrawModelListener> myDrawModelListener = new CopyOnWriteArrayList<>();
	private FigureListener fl = e -> notifyModelChangeListeners(
			e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);

	@Override
	public void addFigure(Figure f) {
			if (!myFigures.contains(f)){
				f.addFigureListener(fl);
				myFigures.add(f);
				notifyModelChangeListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
			}


	}

	@Override
	public Iterable<Figure> getFigures() {
		return myFigures;
	}

	@Override
	public void removeFigure(Figure f) {
		if(myFigures.contains(f)){
			f.removeFigureListener(fl);
			myFigures.remove(f);
			notifyModelChangeListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);

			if (myFigures.isEmpty()){
				notifyModelChangeListeners(null, DrawModelEvent.Type.DRAWING_CLEARED);
			}
		}

	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		myDrawModelListener.add(listener);

	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		myDrawModelListener.remove(listener);

	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		if (myFigures.contains(f)) {
			if (myFigures.size()>index) {
				myFigures.remove(f);
				myFigures.add(index, f);
				notifyModelChangeListeners(f, DrawModelEvent.Type.DRAWING_CHANGED);
			}else{
				throw  new IndexOutOfBoundsException();
			}
		}else {
			throw new IllegalArgumentException();
		}


	}

	@Override
	public void removeAllFigures() {

		Iterator<Figure> figureIterator = myFigures.iterator();
		Figure temp;
		while(figureIterator.hasNext()){
			temp=figureIterator.next();
			temp.removeFigureListener(fl);
		}
		notifyModelChangeListeners(null, DrawModelEvent.Type.DRAWING_CLEARED);
	}

	/**
	 * notifies all the drawModelListeners
	 * @param figure the changed figure
	 * @param type what happened to the figure
	 */
	private void notifyModelChangeListeners(Figure figure, DrawModelEvent.Type type){
		DrawModelEvent drawModelEvent = new DrawModelEvent(this,figure,type);

		for (DrawModelListener listener : myDrawModelListener){
			listener.modelChanged(drawModelEvent);
		}

	}

}
