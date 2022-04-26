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
package br.upe.ppsw.jabberpoint.apresentacao.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.upe.ppsw.jabberpoint.apresentacao.model.BitmapItem;
import br.upe.ppsw.jabberpoint.apresentacao.model.IData;
import br.upe.ppsw.jabberpoint.apresentacao.model.Presentation;
import br.upe.ppsw.jabberpoint.apresentacao.model.Slide;
import br.upe.ppsw.jabberpoint.apresentacao.model.SlideItem;
import br.upe.ppsw.jabberpoint.apresentacao.model.TextItem;

/**
 * Representação XML de um arquivo de {@link Presentation}
 */
public class XMLPresentation implements IData {

  protected static final String DEFAULT_API_TO_USE = "dom";

  /** nomeclatura dos itens de um slide */
  protected static final String SHOWTITLE = "showtitle";
  protected static final String SLIDETITLE = "title";
  protected static final String SLIDE = "slide";
  protected static final String ITEM = "item";
  protected static final String LEVEL = "level";
  protected static final String KIND = "kind";
  protected static final String TEXT = "text";
  protected static final String IMAGE = "image";

  protected static final String PCE = "Parser Configuration Exception";
  protected static final String UNKNOWNTYPE = "Unknown Element type";
  protected static final String NFE = "Number Format Exception";


  private String getTitle(Element element, String tagName) {
    NodeList titles = element.getElementsByTagName(tagName);
    return titles.item(0).getTextContent();

  }

  /**
   * @see Accessor#loadFile(Presentation, String)
   */
  public void load(Presentation presentation, String filename) throws IOException {
    int slideNumber, itemNumber, max = 0, maxItems = 0;

    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

      Document document = builder.parse(new File(filename));

      Element doc = document.getDocumentElement();
      presentation.setTitle(getTitle(doc, SHOWTITLE));

      NodeList slides = doc.getElementsByTagName(SLIDE);
      max = slides.getLength();

      for (slideNumber = 0; slideNumber < max; slideNumber++) {
        Element xmlSlide = (Element) slides.item(slideNumber);

        Slide slide = new Slide();
        slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
        presentation.append(slide);

        NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
        maxItems = slideItems.getLength();

        for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
          Element item = (Element) slideItems.item(itemNumber);
          loadSlideItem(slide, item);
        }
      }

    } catch (IOException iox) {
      System.err.println(iox.toString());
    } catch (SAXException sax) {
      System.err.println(sax.getMessage());
    } catch (ParserConfigurationException pcx) {
      System.err.println(PCE);
    }

  }

  protected void loadSlideItem(Slide slide, Element item) {
    int level = 1;

    NamedNodeMap attributes = item.getAttributes();

    String leveltext = attributes.getNamedItem(LEVEL).getTextContent();

    if (leveltext != null) {
      try {
        level = Integer.parseInt(leveltext);
      } catch (NumberFormatException x) {
        System.err.println(NFE);
      }
    }

    String type = attributes.getNamedItem(KIND).getTextContent();
    if (TEXT.equals(type)) {
      slide.append(new TextItem(level, item.getTextContent()));
    } else {
      if (IMAGE.equals(type)) {
        slide.append(new BitmapItem(level, item.getTextContent()));
      } else {
        System.err.println(UNKNOWNTYPE);
      }
    }
  }

  /**
   * @see Accessor#saveFile(Presentation, String)
   */
  public void save(Presentation presentation, String filename) throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter(filename));

    out.println("<?xml version=\"1.0\"?>");
    out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
    out.println("<presentation>");

    out.print("<showtitle>");
    out.print(presentation.getTitle());
    out.println("</showtitle>");

    for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
      Slide slide = presentation.getSlide(slideNumber);

      out.println("<slide>");
      out.println("<title>" + slide.getTitle() + "</title>");

      Vector<SlideItem> slideItems = slide.getSlideItems();
      for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
        SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
        out.print("<item kind=");

        if (slideItem instanceof TextItem) {
          out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
          out.print(((TextItem) slideItem).getText());
        } else {
          if (slideItem instanceof BitmapItem) {
            out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
            out.print(((BitmapItem) slideItem).getName());
          } else {
            System.out.println("Ignoring " + slideItem);
          }
        }

        out.println("</item>");
      }

      out.println("</slide>");
    }

    out.println("</presentation>");

    out.close();
  }
  public String getExtension() {
	  return "xml";
  }
}
