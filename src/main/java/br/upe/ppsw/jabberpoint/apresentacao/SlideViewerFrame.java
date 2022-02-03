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

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * Representa a janela de exibição ao usuário dos {@link Slide} de uma {@link Presentation};
 */
public class SlideViewerFrame extends JFrame {

  private static final long serialVersionUID = 3227L;

  private static final String JABTITLE = "Jabberpoint 1.6";

  public final static int WIDTH = 1200;
  public final static int HEIGHT = 800;

  public SlideViewerFrame(String title, Presentation presentation) {
    super(title);

    SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
    presentation.setShowView(slideViewerComponent);

    setupWindow(slideViewerComponent, presentation);
  }

  /**
   * Configura a interface com o usuário para a exibição de uma {@link Presentation}
   * 
   * @param slideViewerComponent A instância de {@link SlideViewerComponent}, o componente de
   *        controle da visualização e navegação dos {@link Slide} de uma {@link Presentation}
   * @param presentation A instância de {@link Presentation} que contém os dados da apresentação.
   */
  public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation) {
    setTitle(JABTITLE);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    getContentPane().add(slideViewerComponent);
    addKeyListener(new KeyController(presentation));
    setMenuBar(new MenuController(this, presentation));
    setSize(new Dimension(WIDTH, HEIGHT));

    setVisible(true);
  }

}
