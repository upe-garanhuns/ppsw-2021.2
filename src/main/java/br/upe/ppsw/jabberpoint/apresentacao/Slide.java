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
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * Representa os dados de um slide de uma {@link Presentation} composta por uma lista de
 * {@link SlideItem} e um {@link TextItem}.
 */
public class Slide {

  public final static int WIDTH = 1200;
  public final static int HEIGHT = 800;

  protected TextItem title;
  protected Vector<SlideItem> items;

  /**
   * Cria uma nova instância do slide inicializando a lista de itens.
   */
  public Slide() {
    items = new Vector<SlideItem>();
  }

  /**
   * Adiciona um item ao slide.
   * 
   * @param anItem A instância de {@link SlideItem} contendo os dados do item de slide.
   */
  public void append(SlideItem anItem) {
    items.addElement(anItem);
  }

  /**
   * Recupera o texto do título do Slide.
   * 
   * @return Uma {@link String} contendo o título do slide.
   */
  public String getTitle() {
    return title.getText();
  }

  /**
   * Altera o título do {@link Slide}.
   * 
   * @param newTitle A instância contedo o novo título do {@link Slide}
   */
  public void setTitle(String newTitle) {
    title = new TextItem(0, newTitle);
  }

  /**
   * Adiciona um texto ao slide como {@link SlideItem}.
   * 
   * @param level O nível de identação do texto
   * @param message O conteúdo do texto
   */
  public void append(int level, String message) {
    append(new TextItem(level, message));
  }

  /**
   * Recupera um item de slide de acordo com a sua posição.
   * 
   * @param number A posição do item no {@link Slide}
   * @return O {@link SlideItem} na posição informada.
   * @throws ArrayIndexOutOfBoundsException caso a posição informada não seja válida.
   */
  public SlideItem getSlideItem(int number) {
    return (SlideItem) items.elementAt(number);
  }

  /**
   * Recupera todos os {@link SlideItem} do {@link Slide}
   * 
   * @return um {@link Vector} contendo todos os itens do slide.
   */
  public Vector<SlideItem> getSlideItems() {
    return items;
  }

  /**
   * Obtém o quantitativo de {@link SlideItem} do {@link Slide}.
   * 
   * @return Um int contendo a quantidade de itens.
   */
  public int getSize() {
    return items.size();
  }

  /**
   * Desenha todos os itens do slide na interface com o usuário.
   * 
   * @param g A instância de {@link Graphics} que vai receber o desenho slide.
   * @param area a instância de {@link Rectangle} que irá receber os {@link SlideItem}
   * @param view A instância de {@link ImageObserver}, o observer que recebe as notificações dos
   *        itens que são desenhados na interface.
   */
  public void draw(Graphics g, Rectangle area, ImageObserver view) {
    float scale = getScale(area);

    int y = area.y;

    SlideItem slideItem = this.title;
    Style style = Style.getStyle(slideItem.getLevel());
    slideItem.draw(area.x, y, scale, g, style, view);

    y += slideItem.getBoundingBox(g, view, scale, style).height;

    for (int number = 0; number < getSize(); number++) {
      slideItem = (SlideItem) getSlideItems().elementAt(number);

      style = Style.getStyle(slideItem.getLevel());
      slideItem.draw(area.x, y, scale, g, style, view);

      y += slideItem.getBoundingBox(g, view, scale, style).height;
    }
  }

  /**
   * Obtém uma escala de proporção em função da altura, largura e tamanho da apresentação para
   * exibição dos slides.
   * 
   * @param area um {@link Rectangle} contendo os dados de tamanho da apresentação.
   * @return um float com a proporção calculada.
   */
  private float getScale(Rectangle area) {
    return Math.min(((float) area.width) / ((float) WIDTH),
        ((float) area.height) / ((float) HEIGHT));
  }
}
