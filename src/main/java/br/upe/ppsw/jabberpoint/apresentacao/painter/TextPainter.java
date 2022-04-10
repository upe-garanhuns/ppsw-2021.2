/**
 * <p>
 * UPE - Campus Garanhuns Curso de Engenharia de Software Disciplina de Padrões de Projeto de
 * Software Copyright 2021 the original author or authors.
 * </p>
 * 
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * </p>
 * 
 * @author Ian F. Darwin, hbarreiros
 */
package br.upe.ppsw.jabberpoint.apresentacao.painter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.StringUtils;

import br.upe.ppsw.jabberpoint.apresentacao.Style;

public class TextPainter {

	private TextPainter() {

	}
	
	public static int draw(int x, int y, float scale, Graphics g, Style myStyle, String text) {
		if (StringUtils.hasLength(text)) {

			List<TextLayout> layouts = getLayouts(g, myStyle, scale, text);

			Point pen = new Point(x + (int) (myStyle.getIndent() * scale), y + (int) (myStyle.getLeading() * scale));

			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(myStyle.getColor());

			Iterator<TextLayout> it = layouts.iterator();

			while (it.hasNext()) {
				TextLayout layout = it.next();

				pen.y += layout.getAscent();
				layout.draw(g2d, pen.x, pen.y);

				pen.y += layout.getDescent();
			}
		}

		return getBoundingBox(g, scale, myStyle, text);
	}

	private static int getBoundingBox(Graphics g, float scale, Style myStyle, String text) {
		List<TextLayout> layouts = getLayouts(g, myStyle, scale, text);

		int xsize = 0;
		int ysize = (int) (myStyle.getLeading() * scale);

		Iterator<TextLayout> iterator = layouts.iterator();

		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();

			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}

			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}

		return new Rectangle((int) (myStyle.getIndent() * scale), 0, xsize, ysize).height;
	}

	private static List<TextLayout> getLayouts(Graphics g, Style s, float scale, String text) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();

		AttributedString attrStr = new AttributedString(text);
		attrStr.addAttribute(TextAttribute.FONT, s.getFont(scale), 0, text.length());

		Graphics2D g2d = (Graphics2D) g;

		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

		float wrappingWidth = (1200 - s.getIndent()) * scale;

		while (measurer.getPosition() < text.length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
		}

		return layouts;
	}

}
