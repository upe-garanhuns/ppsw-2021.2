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


import java.util.Vector;

import br.upe.ppsw.jabberpoint.view.SlideViewerFrame;
import br.upe.ppsw.jabberpoint.view.Style;


public class Slide {

  

  protected TextItem title;
  protected Vector<SlideItem> items;
  

  public Slide() {
    items = new Vector<SlideItem>();
  }

 
  public void append(SlideItem anItem) {
    items.addElement(anItem);
  }

 
  public String getTitleText() {
    return title.getText();
  }
  public TextItem getTitle() {
		return this.title;
	}
  
  public void setTitle(String newTitle) {
    title = new TextItem(0, newTitle);
  }

  public void append(int level, String message) {
    append(new TextItem(level, message));
  }

  
  public SlideItem getSlideItem(int number) {
    return (SlideItem) items.elementAt(number);
  }

  
  public Vector<SlideItem> getSlideItems() {
    return items;
  }

 
  public int getSize() {
    return items.size();
  }

}