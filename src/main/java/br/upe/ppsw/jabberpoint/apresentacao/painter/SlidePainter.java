package br.upe.ppsw.jabberpoint.apresentacao.painter;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.JabberPointApplication;
import br.upe.ppsw.jabberpoint.apresentacao.Style;
import br.upe.ppsw.jabberpoint.modelo.Slide;
import br.upe.ppsw.jabberpoint.modelo.SlideItem;
import br.upe.ppsw.jabberpoint.modelo.SlideItemType;

public class SlidePainter {

	private Slide slide;

	public SlidePainter(Slide slide) {
		this.slide = slide;
	}

	public final void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
		int y = area.y;

		Style style = Style.getStyle(0);

		SlideItem slideTitle = new SlideItem(0, this.slide.getTitle());

		y += TextPainter.draw(area.x, area.y, scale, g, style, slideTitle);

		for (SlideItem item : this.slide.getItems()) {
			style = Style.getStyle(item.getLevel());

			if (SlideItemType.TEXT.equals(item.getType())) {
				y += TextPainter.draw(area.x, y, scale, g, style, item);
			} else {
				y += BitmapPainter.draw(area.x, y, scale, g, style, view, item);
			}
		}
	}

	private float getScale(Rectangle area) {
		return Math.min(((float) area.width) / ((float) JabberPointApplication.WIDTH),
				((float) area.height) / ((float) JabberPointApplication.HEIGHT));
	}
}
