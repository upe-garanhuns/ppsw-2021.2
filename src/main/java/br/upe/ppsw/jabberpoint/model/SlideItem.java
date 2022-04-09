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

import lombok.Data;

@Data
public class SlideItem {

	private int level = 0;
	private String text;
	private SlideItemType type;
	private byte[] media;

	public SlideItem(int level) {
		this.level = level;
		this.text = "";
		this.type = SlideItemType.TEXT;
	}

	public SlideItem(int level, String text) {
		this.level = level;
		this.text = text;
		this.type = SlideItemType.TEXT;
	}

	public SlideItem(int level, String caption, byte[] image) {
		this(level);

		this.text = caption;
		this.type = SlideItemType.IMAGE;
		this.media = image;
	}

}
