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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Representa o componente de apresentação dos {@link Slide} de uma {@link Presentation}.
 */
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

  /**
   * Inicializa o mecanismo de visualização dos {@link Slide} de uma {@link Presentation}.
   * 
   * @param pres a instância de {@link Presentation} contendo os dados da apresentação
   * @param frame A instância de {@link JFrame} que ira receber os itens do {@link Slide}
   */
  public SlideViewerComponent(Presentation pres, JFrame frame) {
    setBackground(BGCOLOR);
    presentation = pres;
    labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
    this.frame = frame;
  }

  /**
   * Realiza o ajuste de exibição do componente de acordo com as dimensões especificadas.
   * 
   * @return {@link Dimension} A instãncia com as dimensões preferidas de exibição.
   */
  public Dimension getPreferredSize() {
    return new Dimension(Slide.WIDTH, Slide.HEIGHT);
  }

  /**
   * Atualiza os dados de visualização de um {@link Slide} de uma {@link Presentation}. Caso o slide
   * atual não seja informado a apresentação é inicializada.
   * 
   * @param presentation A instância de {@link Presentation} que contém os dados da apresentação
   * @param data A instância de {@link Slide} que representa o slide atual.
   */
  public void update(Presentation presentation, Slide data) {
    if (data == null) {
      repaint();
      return;
    }

    this.presentation = presentation;
    this.slide = data;
    repaint();
    frame.setTitle(presentation.getTitle());
  }

  /**
   * Renderiza os elementos do componente com os dados do slide atual.
   * 
   * @param g A instância que receberá os itens do slide a serem exibidos na tela.
   */
  public void paintComponent(Graphics g) {
    g.setColor(BGCOLOR);
    g.fillRect(0, 0, getSize().width, getSize().height);

    if (presentation.getSlideNumber() < 0 || slide == null) {
      return;
    }

    g.setFont(labelFont);
    g.setColor(COLOR);
    g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(),
        XPOS, YPOS);

    Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));

    slide.draw(g, area, this);
  }

}
