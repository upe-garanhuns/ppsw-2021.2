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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Implementação dos mecanismos de controle navegacional entre os {@link Slide} de uma
 * {@link Presentation} através do teclado.
 */
public class KeyController extends KeyAdapter {

  private Presentation presentation;

  /**
   * Inicializa o mecnismo de controle através de teclado associado a uma {@link Presentation}
   * 
   * @param p A instância de {@link Presentation} que será controlada.
   */
  public KeyController(Presentation p) {
    presentation = p;
  }

  /**
   * Intepreta o comando de teclado recebido e delega a exibição de itens de uma apresentação. Os
   * comandos aceitos são:
   * 
   * {@link KeyEvent#VK_PAGE_DOWN}, {@link KeyEvent#VK_DOWN}, {@link KeyEvent#VK_ENTER} para avançar
   * para o próximo slide {@link KeyEvent#VK_PAGE_UP}, {@link KeyEvent#VK_UP} e '-' para voltar ao
   * slide anterior 'Q' e 'q' para finalizar a apresentação.
   * 
   * @param a instância de {@link KeyEvent} que contém os dados da tecla pressionada pelo usuário
   */
  public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_PAGE_DOWN:
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_ENTER:
      case '+':
        presentation.nextSlide();
        break;
      case KeyEvent.VK_PAGE_UP:
      case KeyEvent.VK_UP:
      case '-':
        presentation.prevSlide();
        break;
      case 'q':
      case 'Q':
        System.exit(0);
        break; // fix?
      default:
        break;
    }
  }

}
