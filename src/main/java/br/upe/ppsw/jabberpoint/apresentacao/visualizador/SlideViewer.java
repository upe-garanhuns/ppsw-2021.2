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
package br.upe.ppsw.jabberpoint.apresentacao.visualizador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import br.upe.ppsw.jabberpoint.JabberPointApplication;
import br.upe.ppsw.jabberpoint.apresentacao.painter.SlidePainter;
import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.Slide;

/**
 * Representa o componente de apresentação dos {@link Slide} de uma {@link Presentation}.
 */
public class SlideViewer extends JComponent {
  private static final long serialVersionUID = 227L;

  private static final Color BGCOLOR = Color.white;
  private static final Color COLOR = Color.black;
  private static final String FONTNAME = "Dialog";
  private static final int FONTSTYLE = Font.BOLD;
  private static final int FONTHEIGHT = 10;
  private static final int XPOS = 1100;
  private static final int YPOS = 20;

  private Slide slide;
  private String status;
  private Font labelFont = null;

  public SlideViewer() {
    setBackground(BGCOLOR);
    labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
  }

  public void update(Slide slide, String status) {
    this.slide = slide;
    this.status = status;
    repaint();
  }

  /**
   * Renderiza os elementos do componente com os dados do slide atual.
   * 
   * @param g A instância que receberá os itens do slide a serem exibidos na tela.
   */
  @Override
  public void paintComponent(Graphics g) {
    g.setColor(BGCOLOR);
    g.fillRect(0, 0, getSize().width, getSize().height);

    if (this.slide != null) {
      g.setFont(labelFont);
      g.setColor(COLOR);
      g.drawString(this.status, XPOS, YPOS);

      Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
      new SlidePainter(this.slide).draw(g, area, this);
    }
    
  }
  
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(JabberPointApplication.WIDTH, JabberPointApplication.HEIGHT);
}

}
