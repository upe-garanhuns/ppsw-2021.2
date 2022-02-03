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

import java.awt.Color;
import java.awt.Font;

/**
 * Representa uma folha de estilos que é aplicado aos {@link SlideItem} dos {@link Slide} de uma
 * {@link Presentation}
 */
public class Style {

  private static Style[] styles;

  private static final String FONTNAME = "Helvetica";
  int indent;
  Color color;
  Font font;
  int fontSize;
  int leading;

  public static void createStyles() {
    styles = new Style[5];
    // os estilos estão codificados de maneira fixa.
    styles[0] = new Style(0, Color.red, 48, 20); // nível 0
    styles[1] = new Style(20, Color.blue, 40, 10); // nível 1
    styles[2] = new Style(50, Color.black, 36, 10); // nível 2
    styles[3] = new Style(70, Color.black, 30, 10); // nivel 3
    styles[4] = new Style(90, Color.black, 24, 10); // nível 4
  }

  public static Style getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }

    return styles[level];
  }

  public Style(int indent, Color color, int points, int leading) {
    this.indent = indent;
    this.color = color;
    font = new Font(FONTNAME, Font.BOLD, fontSize = points);
    this.leading = leading;
  }

  public String toString() {
    return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }
}
