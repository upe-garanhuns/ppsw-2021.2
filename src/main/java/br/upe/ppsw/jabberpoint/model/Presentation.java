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
package br.upe.ppsw.jabberpoint.model;

import java.util.ArrayList;

import br.upe.ppsw.jabberpoint.view.SlideViewerComponent;


public class Presentation {

  private String title;
  private ArrayList<Slide> showList = null;
  private int currentSlideNumber = 0;

 
  public Presentation() {
        clear();
  }

 
  public int getSize() {
    return showList.size();
  }

 
  public String getTitle() {
    return title;
  }

  
  public void setTitle(String nt) {
    title = nt;
  }


  public int getSlideNumber() {
    return currentSlideNumber;
  }

 
  public void setSlideNumber(int number) {
    currentSlideNumber = number;
   
  }

  
  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  public void nextSlide() {
    if (currentSlideNumber < (showList.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

 
  public void clear() {
    showList = new ArrayList<Slide>();
    setSlideNumber(-1);
  }

  public void append(Slide slide) {
    showList.add(slide);
  }

 
  public Slide getSlide(int number) {
    if (number < 0 || number >= getSize()) {
      return null;
    }
    return (Slide) showList.get(number);
  }


  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

}
