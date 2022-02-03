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

/**
 * Representação abstrata de um item de {@link Slide} em uma {@link Presentation}
 */
public abstract class SlideItem {

  private int level = 0;

  /**
   * Cria uma nova instância de item em um slide, o {@link Style} aplicado está direatmente
   * relacionado a esta posição.
   * 
   * @param lev
   */
  public SlideItem(int lev) {
    level = lev;
  }

  /**
   * Cria um {@link Slide} aplicando o nível mais externo.
   */
  public SlideItem() {
    this(0);
  }

  /**
   * Recupera o nível associado ao {@link SlideItem}.
   * 
   * @return um int representando o nível ocupado pelo item no {@link Slide}
   */
  public int getLevel() {
    return level;
  }

  /**
   * Obriga os itens de slide a especificarem suas "fronteiras"
   * 
   * @param g A instância de {@link Graphics} que receberá o desenho do item do slide.
   * @param observer A instância de {@link ImageObserver} recebe as notificações de alterações de
   *        itens da interface com o usuário
   * @param scale A escala de proporção para desenhar os itens na tela.
   * @param style A instância de {@link Style} a ser aplicada ao item, conforme a posição de ele
   *        ocupa no {@link Slide}.
   * 
   * @return Uma instância de {@link Rectangle} contendo os itens {@link SlideItem} do {@link Slide}
   */
  public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale,
      Style style);

  /**
   * Desenha um item {@link SlideItem} de {@link Slide}
   * 
   * @param x posição horizontal do item
   * @param y posição vertical do item
   * @param scale escala do item
   * @param g a instância que receberá o desenho do item
   * @param style o estilo a ser aplicado ao item
   * @param observer o observer que receberá a notificação do item.
   */
  public abstract void draw(int x, int y, float scale, Graphics g, Style style,
      ImageObserver observer);

}
