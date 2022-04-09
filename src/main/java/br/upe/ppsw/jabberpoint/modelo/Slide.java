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
package br.upe.ppsw.jabberpoint.modelo;

import java.util.LinkedList;
import lombok.Data;

@Data
public class Slide {

	protected String title;
	protected LinkedList<SlideItem> items;

	public Slide() {
		this.items = new LinkedList<>();
	}

	public Slide(String title) {
		this();
		this.title = title;
	}

	public void append(SlideItem item) {
		this.items.addLast(item);
	}

	public void add(int level, SlideItem item) {
		this.items.add(level, item);
	}

	public SlideItem getSlideItem(int number) {
		return items.get(number);
	}

	public int getItemsSize() {
		return this.items.size();
	}

}
