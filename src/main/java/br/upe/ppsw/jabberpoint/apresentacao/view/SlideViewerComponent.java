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
package br.upe.ppsw.jabberpoint.apresentacao.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.JComponent;
import javax.swing.JFrame;

import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;
import br.upe.ppsw.jabberpoint.apresentacao.model.Slide;
import br.upe.ppsw.jabberpoint.apresentacao.model.SlideItem;

public class SlideViewerComponent extends JComponent {
	private static final long serialVersionUID = 227L;

	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	private Slide slide;
	private Font labelFont = null;
	private Presentation presentation = null;
	private JFrame frame = null;

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		setBackground(BGCOLOR);
		presentation = pres;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize() {
		return new Dimension(1200, 800);
	}

	public void update() {
		this.slide = presentation.getCurrentSlide();
		repaint();
		frame.setTitle(presentation.getTitle());
	}

	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);

		if (presentation.getSlideNumber() < 0 || slide == null) {
			return;
		}

		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(), XPOS, YPOS);

		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));

		drawSlide(g, area, this);
	}
	
	public void drawSlide(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);

		int y = area.y;

		SlideItem slideItem = slide.getTitle();
		Style style = Style.getStyle(slideItem.getLevel());
		slideItem.draw(area.x, y, scale, g, style, view);

		y += slideItem.getBoundingBox(g, view, scale, style).height;

		for (int number = 0; number < slide.getSize(); number++) {
			slideItem = (SlideItem) slide.getSlideItems().elementAt(number);

			style = Style.getStyle(slideItem.getLevel());
			slideItem.draw(area.x, y, scale, g, style, view);

			y += slideItem.getBoundingBox(g, view, scale, style).height;
		}
	}
	
	private float getScale(Rectangle area) {
		return Math.min(((float) area.width) / ((float) 1200), ((float) area.height) / ((float) 800));
	}

}
