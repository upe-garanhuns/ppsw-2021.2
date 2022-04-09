package br.upe.ppsw.jabberpoint.presentation.painter;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.JabberPointApplication;
import br.upe.ppsw.jabberpoint.model.Slide;
import br.upe.ppsw.jabberpoint.model.SlideItem;
import br.upe.ppsw.jabberpoint.model.SlideItemType;
import br.upe.ppsw.jabberpoint.presentation.Style;

public class SlidePainter {

	private Slide slide;

	public SlidePainter(Slide slide) {
		this.slide = slide;
	}

	public final void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
		int y = area.y;

		Style style = Style.getStyle(0);

		y += TextPainter.draw(area.x, area.y, scale, g, style, this.slide.getTitle());

		for (SlideItem item : this.slide.getItems()) {
			style = Style.getStyle(item.getLevel());

			if (SlideItemType.TEXT.equals(item.getType())) {
				y += TextPainter.draw(area.x, y, scale, g, style, item.getText());
			} else {
				y += BitmapPainter.draw(area.x, y, scale, g, style, view, item.getMedia());
			}
		}
	}

	private float getScale(Rectangle area) {
		return Math.min(((float) area.width) / ((float) JabberPointApplication.WIDTH),
				((float) area.height) / ((float) JabberPointApplication.HEIGHT));
	}
}
