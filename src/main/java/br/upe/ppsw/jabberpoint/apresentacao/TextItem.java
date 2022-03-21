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
package br.upe.ppsw.jabberpoint.apresentacao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa a informação de texto de um {@link Slide} em um {@link Slide}
 */
public class TextItem extends SlideItem {

  private String text;

  private static final String EMPTYTEXT = "No Text Given";

  /**
   * Cria uma nova instância de item de slide do tipo texto, indicando sua posição em nível no
   * slide.
   * 
   * @param level o nível ocupado pelo item
   * @param string o texto associado
   */
  public TextItem(int level, String string) {
    super(level);
    text = string;
  }

  /**
   * Inicializa um item do tipo texto no nível mais externo.
   */
  public TextItem() {
    this(0, EMPTYTEXT);
  }

  /**
   * Recupera o texto de um item de slide.
   * 
   * @return Uma {@link String} contendo o conteúdo de texto do item.
   */
  public String getText() {
    return text == null ? "" : text;
  }

  /**
   * Cria uma instãncia de {@link AttributedString} para a interface do usuário de acordo com o
   * texto e estilo correspondente ao item no {@link Slide}
   * 
   * @param style A instância do {@link Style} associado a posição do item no slide.
   * @param scale A escala de propoção do item
   * 
   * @return A instância de {@link AttributedString}
   */
  public AttributedString getAttributedString(Style style, float scale) {
    AttributedString attrStr = new AttributedString(getText());

    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());

    return attrStr;
  }

  /**
   * @see SlideItem#getBoundingBox(Graphics, ImageObserver, float, Style)
   */
  public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
    List<TextLayout> layouts = getLayouts(g, myStyle, scale);

    int xsize = 0, ysize = (int) (myStyle.leading * scale);

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

    return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
  }

  /**
   * @see SlideItem#draw(int, int, float, Graphics, Style, ImageObserver)
   */
  public void draw(int x, int y, float scale, Graphics graphics, Style myStyle, ImageObserver o) {
    if (text == null || text.length() == 0) {
      return;
    }

    List<TextLayout> layouts = getLayouts(graphics, myStyle, scale);
    Point pen = new Point(x + (int) (myStyle.indent * scale), y + (int) (myStyle.leading * scale));

    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setColor(myStyle.color);

    Iterator<TextLayout> it = layouts.iterator();

    while (it.hasNext()) {
      TextLayout layout = it.next();

      pen.y += layout.getAscent();
      layout.draw(graphics2d, pen.x, pen.y);

      pen.y += layout.getDescent();
    }
  }

  private List<TextLayout> getLayouts(Graphics graphics, Style s, float scale) {
    List<TextLayout> layouts = new ArrayList<TextLayout>();

    AttributedString attrStr = getAttributedString(s, scale);
    Graphics2D graphics2d = (Graphics2D) graphics;

    FontRenderContext frc = graphics2d.getFontRenderContext();
    LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

    float wrappingWidth = (Slide.WIDTH - s.indent) * scale;

    while (measurer.getPosition() < getText().length()) {
      TextLayout layout = measurer.nextLayout(wrappingWidth);
      layouts.add(layout);
    }

    return layouts;
  }

  public String toString() {
    return "TextItem[" + getLevel() + "," + getText() + "]";
  }

}
