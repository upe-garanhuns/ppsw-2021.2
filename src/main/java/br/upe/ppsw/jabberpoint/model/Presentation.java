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

import java.util.LinkedList;
import lombok.Data;

@Data
public class Presentation {

	private String title;
	private LinkedList<Slide> slides = null;
	private int currentSlideNumber;

	public Presentation() {
		clear();
	}

	public Presentation(String title) {
		this();
		this.title = title;
	}

	public int getSlidesSize() {
		return this.slides.size();
	}

	public Slide prevSlide() {
		if (this.currentSlideNumber > 0) {
			this.currentSlideNumber--;
		}
		return this.getCurrentSlide();
	}

	public Slide nextSlide() {
		if (this.currentSlideNumber < (this.slides.size() - 1)) {
			this.currentSlideNumber++;
		}

		return this.getCurrentSlide();
	}

	public Slide getCurrentSlide() {
		return getSlide(this.currentSlideNumber);
	}

	public Slide getSlide(int number) {
		Slide slide = null;

		if (number >= 0 && number <= getSlidesSize()) {
			slide = this.slides.get(number);
		}

		return slide;
	}

	public void add(Slide slide) {
		if (this.slides == null) {
			this.slides = new LinkedList<>();
		}

		this.slides.addLast(slide);
	}

	public void clear() {
		this.slides = new LinkedList<>();
		this.currentSlideNumber = -1;
	}

	public String currentStatus() {
		return "Slide " + (1 + this.currentSlideNumber) + " de " + this.slides.size();
	}

	public boolean isValid() {
		return this.currentSlideNumber >= 0 && this.slides != null && !this.slides.isEmpty();
	}

}
