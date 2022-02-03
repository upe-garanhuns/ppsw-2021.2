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

import java.util.ArrayList;

/**
 * Representa uma Apresentação no sistema. Composta por um título, lista de slides e componente de
 * visualização de dados do slide.
 */
public class Presentation {

  private String title;
  private ArrayList<Slide> showList = null;
  private SlideViewerComponent slideViewComponent = null;
  private int currentSlideNumber = 0;

  /**
   * Inicializa uma apresentação.
   */
  public Presentation() {
    slideViewComponent = null;
    clear();
  }

  /**
   * Inicializa um apresentação e o componente de visualização dos slides.
   * 
   * @param slideViewerComponent A instância do {@link SlideViewerComponent} a ser utilizada na
   *        apresentação.
   */
  public Presentation(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
    clear();
  }

  /**
   * Obtém a quantidade de slides da apresentação.
   * 
   * @return um inteiro representando a quantidade.
   */
  public int getSize() {
    return showList.size();
  }

  /**
   * Obtém os dados do título do slide.
   * 
   * @return Uma {@link String} contendo os dados do título do slide
   */
  public String getTitle() {
    return title;
  }

  /**
   * Atualiza os dados do título do slide.
   * 
   * @param nt
   */
  public void setTitle(String nt) {
    title = nt;
  }

  /**
   * Atualiza o componente de visualização dos dados do slide.
   * 
   * @param slideViewerComponent A instância a ser atualizada.
   */
  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  /**
   * Obtém o número do slide atual
   * 
   * @return Um inteiro contendo o número do slide atual
   */
  public int getSlideNumber() {
    return currentSlideNumber;
  }

  /**
   * Atualiza o slide atual da apresentação e atualiza o componente de visualização de dados com as
   * informações a serem atualizadas na tela.
   * 
   * @param number O número do slide.
   */
  public void setSlideNumber(int number) {
    currentSlideNumber = number;
    if (slideViewComponent != null) {
      slideViewComponent.update(this, getCurrentSlide());
    }
  }

  /**
   * Move a apresentação para o slide anterior, caso o slide atual não seja o primeiro.
   */
  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  /**
   * Move a apresentação para o ptóximo slide, caso o slide atual não seja o último.
   */
  public void nextSlide() {
    if (currentSlideNumber < (showList.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  /**
   * Inicializa os dados da apresentação.
   */
  void clear() {
    showList = new ArrayList<Slide>();
    setSlideNumber(-1);
  }

  /**
   * Adiciona um {@link Slide} à apresentação.
   * 
   * @param slide A instância do slide a ser adicionada.
   */
  public void append(Slide slide) {
    showList.add(slide);
  }

  /**
   * Recupera um {@link Slide} a partir de sua ordem na lista.
   * 
   * @param number A ordem do slide a ser recuperada.
   * @return O {@link Slide} econtrado ou <code>null</code> caso o número do slide informado seja
   *         zero ou maior do que a quantidade de slides da apresentação.
   */
  public Slide getSlide(int number) {
    if (number < 0 || number >= getSize()) {
      return null;
    }
    return (Slide) showList.get(number);
  }

  /**
   * Recupera o slide atual.
   * 
   * @return O {@link Slide} atual ou <code>null</code> caso não esteja sendo exibida alguma
   *         apresentação.
   */
  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

  /**
   * Finaliza a execução da máquina virtual, parando a execução do sistema.
   * 
   * @param n O código de retorno da execução do sistema. Ver {@link System}
   */
  public void exit(int n) {
    System.exit(n);
  }
}
