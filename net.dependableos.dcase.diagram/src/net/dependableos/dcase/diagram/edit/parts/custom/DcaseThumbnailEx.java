/******************************************************************************
 * Copyright (c) 2008, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 ****************************************************************************/

/*
 * copy from org.eclipse.gmf.runtime.draw2d.ui.internal.parts.ThumbnailEx.java
 */

package net.dependableos.dcase.diagram.edit.parts.custom;

import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.UpdateListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.ui.util.DisplayUtils;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.internal.graphics.ScaledGraphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * A ThumbnailEx is a Figure that displays an image of its source Figure at a 
 * smaller size. The ThumbnailEx will maintain the aspect ratio of the source 
 * Figure.<br>
 * Code modified to resolve {@link https://bugs.eclipse.org/bugs/show_bug.cgi?id=230056}
 * 
 * @author Eric Bordeau
 */
public class DcaseThumbnailEx 
	extends Figure 
	implements UpdateListener
{

/**
 * This updates the ThumbnailEx by breaking the thumbnail {@link Image} into
 * several tiles and updating each tile individually.  
 */
class ThumbnailUpdater implements Runnable {
	private static final int NUMBER_OF_TILES = 16;
	int maxHBufferSize = 256, maxVBufferSize = 256;
	private int currentHTile, currentVTile;
	private int hTiles, vTiles;
	private boolean isActive = true;

	private boolean isRunning = false;
	private GC thumbnailGC;
	private ScaledGraphics thumbnailGraphics;
	private Dimension tileSize;
	
	/**
	 * Stops the updater and disposes of any resources.
	 */
	public void deactivate() {
		setActive(false);
		stop();
		if (thumbnailImage != null) {
			thumbnailImage.dispose();
			thumbnailImage = null;
			thumbnailImageSize = null;
		}
	}
	
	/**
	 * Returns the current horizontal tile index.
	 * @return current horizontal tile index.
	 */
	protected int getCurrentHTile() {
		return currentHTile;
	}

	/**
	 * Returns the current vertical tile index.
	 * @return current vertical tile index.
	 */
	protected int getCurrentVTile() {
		return currentVTile;
	}
	
	/**
	 * Returns <code>true</code> if this ThumbnailUpdater is active.  An inactive
	 * updater has disposed of its {@link Image}.  The updater may be active and 
	 * not currently running.  
	 * @return <code>true</code> if this ThumbnailUpdater is active
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Returns <code>true</code> if this is currently running and updating at
	 * least one tile on the thumbnail {@link Image}.  
	 * @return <code>true</code> if this is currently running
	 */
	public boolean isRunning() {
		return isRunning;
	}
	
	/**
	 * Resets the number of vertical and horizontal tiles, as well as the tile
	 * size and current tile index.
	 */
	public void resetTileValues() {
		maxHBufferSize = Math.max(1, getSourceRectangle().width / NUMBER_OF_TILES); 
		maxVBufferSize = Math.max(1, getSourceRectangle().width / NUMBER_OF_TILES); 
		
		hTiles = (int)Math.ceil((float)getSourceRectangle().width 
									/ (float)maxHBufferSize);
		vTiles = (int)Math.ceil((float)getSourceRectangle().height 
									/ (float)maxVBufferSize);
		
		tileSize = new Dimension((int)Math.ceil((float)getSourceRectangle().width 
									/ (float)hTiles),
								(int)Math.ceil((float)getSourceRectangle().height 
									/ (float)vTiles));
		
		currentHTile = 0;
		currentVTile = 0;
	}
	
	/**
	 * Restarts the updater.
	 */
	public void restart() {
		stop();
		start();
	}
	
	/**
	 * Updates the current tile on the ThumbnailEx.  An area of the source Figure
	 * is painted to an {@link Image}.  That Image is then drawn on the 
	 * ThumbnailEx.  Scaling of the source Image is done inside
	 * {@link GC#drawImage(Image, int, int, int, int, int, int, int, int)} since
	 * the source and target sizes are different.  The current tile indexes are
	 * incremented and if more updating is necesary, this {@link Runnable} is 
	 * called again in a {@link Display#timerExec(int, Runnable)}.  If no more
	 * updating is required, {@link #stop()} is called.
	 */
	public void run() {
		if (!isActive() || !isRunning())
			return;
		int v = getCurrentVTile();
		int sy1 = v * tileSize.height;
		int sy2 = Math.min((v + 1) * tileSize.height, getSourceRectangle().height);
		
		int h = getCurrentHTile();
		int sx1 = h * tileSize.width;
		int sx2 = Math.min((h + 1) * tileSize.width, getSourceRectangle().width);
		org.eclipse.draw2d.geometry.Point p = getSourceRectangle().getLocation();

		Rectangle rect = new Rectangle(sx1 + p.x, sy1 + p.y, sx2 - sx1, sy2 - sy1);
		thumbnailGraphics.pushState();
		thumbnailGraphics.setClip(rect);
		thumbnailGraphics.fillRectangle(rect);
		paintChildren(thumbnailGraphics);
		thumbnailGraphics.popState();
		
		if (getCurrentHTile() < (hTiles - 1))
			setCurrentHTile(getCurrentHTile() + 1);
		else {
			setCurrentHTile(0);
			if (getCurrentVTile() < (vTiles - 1))
				setCurrentVTile(getCurrentVTile() + 1);
			else
				setCurrentVTile(0);
		}
		
		if (getCurrentHTile() != 0 || getCurrentVTile() != 0)
			Display.getCurrent().asyncExec(this);
		else if (isDirty()) {
			setDirty(false);
			Display.getCurrent().asyncExec(this);
			repaint();
		} else {
			stop();
			repaint();
		}
	}
	
	/**
	 * Sets the active flag.
	 * @param value The active value
	 */
	public void setActive(boolean value) {
		isActive = value;
	}
	
	/**
	 * Sets the current horizontal tile index.
	 * @param count current horizontal tile index
	 */
	protected void setCurrentHTile(int count) {
		currentHTile = count;
	}
	
	/**
	 * Sets the current vertical tile index.
	 * @param count current vertical tile index
	 */
	protected void setCurrentVTile(int count) {
		currentVTile = count;
	}
	
	/**
	 * Starts this updater.  This method initializes all the necessary resources
	 * and puts this {@link Runnable} on the asynch queue.  If this updater is
	 * not active or is already running, this method just returns.
	 */
	public void start() {
		if (!isActive() || isRunning())
			return;
		
		isRunning = true;
		setDirty(false);		
		resetTileValues();
		
		if (!targetSize.equals(thumbnailImageSize)) {
			resetThumbnailImage();
		}
		
		if (targetSize.isEmpty())
			return;
		
		thumbnailGC = new GC(thumbnailImage, 
				sourceFigure.isMirrored() ? SWT.RIGHT_TO_LEFT : SWT.NONE);
		thumbnailGraphics = new ScaledGraphics(new SWTGraphics(thumbnailGC));
		thumbnailGraphics.scale(getScaleX());
		thumbnailGraphics.translate(getSourceRectangle().getLocation().negate());
		
		Color color = sourceFigure.getForegroundColor();
		if (color != null)
			thumbnailGraphics.setForegroundColor(color);
		color = sourceFigure.getBackgroundColor();
		if (color != null)
			thumbnailGraphics.setBackgroundColor(color);
		thumbnailGraphics.setFont(sourceFigure.getFont());
	
		setScales(targetSize.width / (float)getSourceRectangle().width,
			     targetSize.height / (float)getSourceRectangle().height);

		Display.getCurrent().asyncExec(this);
	}

	/**
	 * 
	 * @since 3.2
	 */
	private void resetThumbnailImage() {
		if (thumbnailImage != null)
			thumbnailImage.dispose();
		
		if (!targetSize.isEmpty()) {
			thumbnailImage = new Image(DisplayUtils.getDisplay(), 
					targetSize.width, 
					targetSize.height);
			thumbnailImageSize = new Dimension(targetSize);
		}
		else {
			thumbnailImage = null;
			thumbnailImageSize = new Dimension(0, 0);
		}
	}
	
	/**
	 * Stops this updater.  Also disposes of resources (except the thumbnail
	 * image which is still needed for painting).
	 */
	public void stop() {
		isRunning = false;
		if (thumbnailGC != null) {
			thumbnailGC.dispose();
			thumbnailGC = null;
		}
		if (thumbnailGraphics != null) {
			thumbnailGraphics.dispose();
			thumbnailGraphics = null;
		}
		// Don't dispose of the thumbnail image since it is needed to paint the 
		// figure when the source is not dirty (i.e. showing/hiding the dock).
	}
}
private boolean isDirty;
private float scaleX;
private float scaleY;

private IFigure sourceFigure; // the figure of argument (used for compatibility)
private List<IFigure> sourceChildren; // source figures of argument
Dimension targetSize = new Dimension(0, 0);
private Image thumbnailImage;
private Dimension thumbnailImageSize;
private ThumbnailUpdater updater = new ThumbnailUpdater();

private static int margin_x = 8;
private static int margin_y = 8;

/**
 * Creates a new ThumbnailEx.  The source Figure must be set separately if you
 * use this constructor.
 */
public DcaseThumbnailEx() {
	super();	
}

/**
 * Creates a new ThumbnailEx with the given IFigure as its source figure.
 * @param fig The source figure
 */
public DcaseThumbnailEx(IFigure fig) {
	this();
	setSource(fig);
}

private Dimension adjustToAspectRatio(Dimension size, boolean adjustToMaxDimension) {
	Dimension sourceSize = getSourceRectangle().getSize();
	Dimension borderSize = new Dimension(getInsets().getWidth(), getInsets().getHeight());
	size.expand(borderSize.getNegated());
	int width, height;
	if (adjustToMaxDimension) {
		width  = Math.max(size.width, (int)(size.height * sourceSize.width 
											/ (float)sourceSize.height + 0.5));
		height = Math.max(size.height, (int)(size.width * sourceSize.height 
											/ (float)sourceSize.width + 0.5));
	} else {
		width  = Math.min(size.width,  (int)(size.height * sourceSize.width 
											/ (float)sourceSize.height + 0.5));
		height = Math.min(size.height, (int)(size.width * sourceSize.height 
											/ (float)sourceSize.width + 0.5));
	}
	size.width  = width;
	size.height = height;
	return size.expand(borderSize);
}

/**
 * Deactivates this ThumbnailEx.
 */
public void deactivate() {
	sourceFigure.getUpdateManager().removeUpdateListener(this);
	updater.deactivate();
}

/**
 * Returns the preferred size of this ThumbnailEx.  The preferred size will be 
 * calculated in a way that maintains the source Figure's aspect ratio.
 * 
 * @param wHint The width hint
 * @param hHint The height hint
 * @return The preferred size
 */
public Dimension getPreferredSize(int wHint, int hHint) {
	if (prefSize == null)
		return adjustToAspectRatio(getBounds().getSize(), false);
		
	Dimension preferredSize = adjustToAspectRatio(prefSize.getCopy(), true);
	
	if (maxSize == null)
		return preferredSize;
	
	Dimension maximumSize = adjustToAspectRatio(maxSize.getCopy(), true);
	if (preferredSize.contains(maximumSize))
		return maximumSize;
	else
		return preferredSize;
}

/**
 * Returns the scale factor on the X-axis.
 * @return X scale
 */
protected float getScaleX() {
	return scaleX;
}

/**
 * Returns the scale factor on the Y-axis.
 * @return Y scale
 */
protected float getScaleY() {
	return scaleY;
}

/**
 * Returns the source figure being used to generate a thumbnail.
 * @return the source figure
 */
protected IFigure getSource() {
	return sourceFigure;
}

/**
 * Returns the rectangular region relative to the source figure which will be the basis of
 * the thumbnail.  The value may be returned by reference and should not be modified by
 * the caller.
 * @since 3.1
 * @return the region of the source figure being used for the thumbnail
 */
protected Rectangle getSourceRectangle() {
	return getChildRectangle(sourceChildren);
}

/**
 * Returns the rectanglar region of source figures.
 * @param list the source figures.
 * @return the region of the source figures.
 */
public static Rectangle getChildRectangle(List<IFigure> list) {
	Rectangle ret = null;
	if(list != null) {
		for(IFigure child : list) {
			if(ret == null) {
				ret = child.getBounds().getCopy();
			} else {
				ret.union(child.getBounds());
			}
		}
	}
	if(ret != null) {
		return ret.expand(margin_x*2, margin_y*2);
	} else {
		return new Rectangle(0, 0, 0, 0);
	}
}

/**
 * Paints the source figures.
 */
protected void paintChildren(Graphics graphics) {
	for(IFigure child : sourceChildren) {
		child.paint(graphics);
	}
}

/**
 * Returns the scaled Image of the source Figure.  If the Image needs to be 
 * updated, the ThumbnailUpdater will notified.
 * 
 * @return The thumbnail image
 */
protected Image getThumbnailImage() {
	Dimension oldSize = targetSize;
	targetSize = getPreferredSize();
	targetSize.expand(new Dimension(getInsets().getWidth(), 
									getInsets().getHeight()).negate());
	setScales(targetSize.width / (float)getSourceRectangle().width,
		     targetSize.height / (float)getSourceRectangle().height);
	if ((isDirty()) && !updater.isRunning())
		updater.start();
	else if (oldSize != null && !targetSize.equals(oldSize)) {
		revalidate();
		updater.restart();
	}
	
	return thumbnailImage;
}

/**
 * Returns <code>true</code> if the source figure has changed.
 * @return <code>true</code> if the source figure has changed
 */
protected boolean isDirty() {
	return isDirty;
}

/**
 * @see org.eclipse.draw2d.UpdateListener#notifyPainting(Rectangle, Map)
 */
public void notifyPainting(Rectangle damage, Map dirtyRegions) {
	Iterator dirtyFigures = dirtyRegions.keySet().iterator();
	while (dirtyFigures.hasNext()) {
		IFigure current = (IFigure)dirtyFigures.next();
		while (current != null) {
			if (current == getSource()) {
				setDirty(true);
				repaint();
				return;
			}
			current = current.getParent();
		}
	}
}

/**
 * @see org.eclipse.draw2d.UpdateListener#notifyValidating()
 */
public void notifyValidating() {
//	setDirty(true);
//	revalidate();
}

/**
 * @see org.eclipse.draw2d.Figure#paintFigure(Graphics)
 */
protected void paintFigure(Graphics graphics) {
	Image thumbnail = getThumbnailImage();
	if (thumbnail == null)
		return;
	graphics.drawImage(thumbnail, getClientArea().getLocation());
}

/**
 * Sets the dirty flag.
 * @param value The dirty value
 */
public void setDirty(boolean value) {
	isDirty = value;
}

/**
 * Sets the X and Y scales for the ThumbnailEx.  These scales represent the ratio
 * between the source figure and the ThumbnailEx.
 * @param x The X scale
 * @param y The Y scale
 */
protected void setScales(float x, float y) {
	scaleX = x;
	scaleY = y;
}

/**
 * Sets the source Figure.  Also sets the scales and creates the necessary
 * update manager.
 * @param fig The source figure
 */
public void setSource(IFigure fig) {
	if (sourceFigure == fig)
		return;
	if (sourceFigure != null)
		sourceFigure.getUpdateManager().removeUpdateListener(this);
	sourceFigure = fig;
	if (sourceFigure != null) {
		setScales((float)getSize().width / (float)getSourceRectangle().width,
				(float)getSize().height / (float)getSourceRectangle().height);
		sourceFigure.getUpdateManager().addUpdateListener(this);
		repaint();
	}
}

/**
 * Sets the source figures.
 * @param list the source figures.
 */
public void setSourceChildren(List<IFigure> list) {
	sourceChildren = list;
}

public static int getMarginX() {
	return margin_x;
}

public static int getMarginY() {
	return margin_y;
}

public static int setMarginX(int x) {
	int old_x = margin_x;
	margin_x = x;
	return old_x;
}

public static int setMarginY(int y) {
	int old_y = margin_y;
	margin_y = y;
	return old_y;
}
}
