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
package br.upe.ppsw.jabberpoint.apresentacao.painter;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import br.upe.ppsw.jabberpoint.apresentacao.Style;
import br.upe.ppsw.jabberpoint.base.JabberPointException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BitmapPainter {

	private BitmapPainter() {

	}

	public static int draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer,
			byte[] imageByte) {

		try {
			int width = x + (int) (myStyle.getIndent() * scale);
			int height = y + (int) (myStyle.getLeading() * scale);

			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));

			g.drawImage(bufferedImage, width, height, (int) (bufferedImage.getWidth(observer) * scale),
					(int) (bufferedImage.getHeight(observer) * scale), observer);

		} catch (Exception e) {
			log.error("Error when read image bytes to mount SlideItem", e);
			throw new JabberPointException("Error when read image bytes to mount SlideItem", e);
		}

		return getBoundingBox(g, observer, scale, myStyle, imageByte).height;
	}

	private static Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle,
			byte[] imageByte) {

		try {
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByte));

			return new Rectangle((int) (myStyle.getIndent() * scale), 0,
					(int) (bufferedImage.getWidth(observer) * scale),
					((int) (myStyle.getLeading() * scale)) + (int) (bufferedImage.getHeight(observer) * scale));
		} catch (IOException e) {
			throw new JabberPointException("Error when read image bytes to mount SlideItem", e);
		}
	}

}
