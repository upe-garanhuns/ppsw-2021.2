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
package br.upe.ppsw.jabberpoint.controle;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.upe.ppsw.jabberpoint.base.JabberPointException;
import br.upe.ppsw.jabberpoint.base.JabberPointUtil;
import br.upe.ppsw.jabberpoint.modelo.Presentation;
import br.upe.ppsw.jabberpoint.modelo.Slide;
import br.upe.ppsw.jabberpoint.modelo.SlideItem;
import br.upe.ppsw.jabberpoint.modelo.SlideItemType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class XMLFormat implements IFilePresentationFormat {

	protected static final String DEFAULT_API_TO_USE = "dom";

	private static final String PRESENTATION_TITLE = "showtitle";
	private static final String SLIDE_TITLE = "title";
	private static final String PRESENTATION_SLIDES = "slide";
	private static final String SLIDE_ITEM = "item";
	private static final String SLIDE_ITEM_LEVEL = "level";
	private static final String SLIDE_ITEM_KIND = "kind";
	private static final String SLIDE_ITEM_TEXT = "text";
	private static final String SLIDE_ITEM_IMAGE = "image";

	private static final String PCE = "Parser Configuration Exception";

	private static final String PCE_PRESENTATION_NOT_INFORMED = "Presentation was not informed.";
	private static final String PCE_FILE_NOT_INFORMED = "File path was not informed.";
	private static final String PCE_FILE_ELEMENT_NOT_INFORMED = "File element was not informed.";
	private static final String PCE_PRESENTATION_TITLE_NOT_INFORMED = "Presentation title was not informed.";
	private static final String PCE_SLIDES_ELEMENT_NOT_INFORMED = "File slide elements was not informed.";
	private static final String PCE_SLIDE_ELEMENTS_NOT_INFORMED = "File slide element was not informed.";
	private static final String PCE_SLIDE_LEVEL_NOT_INFORMED = "File slide level was not informed.";
	private static final String PCE_SLIDE_LEVEL_INVALID = "File slide level was not informed with one number value.";

	private static final String PCE_SLIDE_KIND_NOT_INFORMED = "File slide kind was not informed.";
	private static final String PCE_SLIDE_KIND_UNKNOWN_TYPE = "Unknown Element type";

	public final Presentation load(String filename) {
		Presentation presentation = null;

		try {
			validateObject(filename, "The file path was not informed.", PCE_FILE_NOT_INFORMED);

			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xmlDocument = builder.parse(new File(filename));

			presentation = extractPresentation(xmlDocument.getDocumentElement());

			presentation.setCurrentSlideNumber(0);

		} catch (JabberPointException e) {
			throw e;
		} catch (Exception e) {
			log.error("One unexpected error when loading presentation XML file.", e);
			throw new JabberPointException(PCE, e);
		}

		return presentation;
	}

	public void save(Presentation presentation, String filename) {
		try {
			validateObject(presentation, "Could not save presentation to XML file, the presentation is null",
					PCE_PRESENTATION_NOT_INFORMED);

			validateObject(presentation, "Could not save presentation to XML file, the presentation file name is null",
					PCE_FILE_NOT_INFORMED);

			PrintWriter xmlFile = new PrintWriter(new FileWriter(filename + ".xml"));

			xmlFile.println("<?xml version=\"1.0\"?>");
			xmlFile.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
			xmlFile.println("<presentation>");

			serializePresentation(presentation, xmlFile);
			xmlFile.close();

		} catch (JabberPointException e) {
			throw e;
		} catch (Exception e) {
			log.error("One unexpected error when saving presentation to XML file.", e);
			throw new JabberPointException(PCE, e);
		}
	}

	private Presentation extractPresentation(Element xmlPresentation) {
		Presentation presentation;
		validateObject(xmlPresentation,
				"One error occourred when loading presentation XML file, the file element was not founded",
				PCE_FILE_ELEMENT_NOT_INFORMED);

		NodeList xmlPresentationTitle = xmlPresentation.getElementsByTagName(PRESENTATION_TITLE);

		validateObject(xmlPresentationTitle,
				"One error occourred when loading presentation XML file, the presentation title was not founded",
				PCE_PRESENTATION_TITLE_NOT_INFORMED);

		validateObject(xmlPresentationTitle.item(0),
				"One error occourred when loading presentation XML file, the presentation title was not founded",
				PCE_PRESENTATION_TITLE_NOT_INFORMED);

		String title = xmlPresentationTitle.item(0).getTextContent();

		presentation = new Presentation(title);

		NodeList elementItens = xmlPresentation.getElementsByTagName(PRESENTATION_SLIDES);

		validateObject(elementItens,
				"One error occourred when loading presentation XML file, the slides element was not founded",
				PCE_SLIDES_ELEMENT_NOT_INFORMED);

		extractSlides(presentation, elementItens);
		
		return presentation;
	}

	private void extractSlides(Presentation presentation, NodeList slidesElements) {
		Slide slide = null;
		Element xmlSlide = null;
		NodeList elementItens = null;
		
		for (int slideNumber = 0; slideNumber < slidesElements.getLength(); slideNumber++) {
			xmlSlide = (Element) slidesElements.item(slideNumber);

			validateObject(xmlSlide,
					"One error occourred when loading presentation XML file, the slide element was not founded",
					PCE_SLIDE_ELEMENTS_NOT_INFORMED);

			elementItens = xmlSlide.getElementsByTagName(SLIDE_TITLE);

			validateObject(elementItens,
					"One error occourred when loading presentation XML file, the slide element itens was not founded",
					PCE_SLIDE_ELEMENTS_NOT_INFORMED);

			validateObject(elementItens.item(0),
					"One error occourred when loading presentation XML file, the slide element itens was not founded",
					PCE_SLIDE_ELEMENTS_NOT_INFORMED);

			slide = new Slide(elementItens.item(0).getTextContent());

			NodeList slideItems = xmlSlide.getElementsByTagName(SLIDE_ITEM);

			validateObject(slideItems,
					"One error occourred when loading presentation XML file, the slide element items was not founded",
					PCE_SLIDE_ELEMENTS_NOT_INFORMED);

			for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
				slide.append(extractSlideItems((Element) slideItems.item(itemNumber)));
			}

			presentation.add(slide);
		}
	}

	private SlideItem extractSlideItems(Element item) {
		SlideItem slideItem = null;

		int level = 1;

		NamedNodeMap attributes = item.getAttributes();
		validateObject(attributes, "The slide item attributes was not informed." + item,
				PCE_SLIDE_ELEMENTS_NOT_INFORMED);

		Node node = attributes.getNamedItem(SLIDE_ITEM_LEVEL);
		validateObject(node, "The slide item level was not informed." + item, PCE_SLIDE_LEVEL_NOT_INFORMED);

		try {
			level = Integer.parseInt(node.getTextContent());
		} catch (NumberFormatException x) {
			log.error("The slide item level is not an integer number." + node.getTextContent());
			throw new JabberPointException(PCE + " - " + PCE_SLIDE_LEVEL_INVALID);
		}

		node = attributes.getNamedItem(SLIDE_ITEM_KIND);
		validateObject(node, "The slide item kind was not informed." + item, PCE_SLIDE_KIND_NOT_INFORMED);

		switch (node.getTextContent()) {
		case SLIDE_ITEM_TEXT:
			slideItem = new SlideItem(level, item.getTextContent());
			break;

		case SLIDE_ITEM_IMAGE:
			byte[] imageByte = JabberPointUtil.decode(item.getTextContent());
			slideItem = new SlideItem(level, item.getTextContent(), imageByte);
			break;

		default:
			log.error("The slide item kind was not filled with valid type." + node.getTextContent());
			throw new JabberPointException(PCE + " - " + PCE_SLIDE_KIND_UNKNOWN_TYPE);
		}

		return slideItem;
	}

	private void validateObject(Object object, String logMessage, String systemMessage) {
		if (object == null || object instanceof Node && !StringUtils.hasText(((Node) object).getTextContent())) {
			throwException(logMessage, systemMessage);
		}
	}

	private void throwException(String logMessage, String systemMessage) {
		log.error(logMessage);
		throw new JabberPointException(PCE + " - " + systemMessage);
	}

	private void serializePresentation(Presentation presentation, PrintWriter xmlFile) {
		xmlFile.print("<showtitle>");
		xmlFile.print(presentation.getTitle());
		xmlFile.println("</showtitle>");
		serializeSlides(presentation, xmlFile);
		xmlFile.println("</presentation>");
	}

	private void serializeSlides(Presentation presentation, PrintWriter xmlFile) {
		for (Slide slide : presentation.getSlides()) {

			xmlFile.println("<slide>");
			xmlFile.println("<title>" + slide.getTitle() + "</title>");

			serializeSlideItems(xmlFile, slide);

			xmlFile.println("</slide>");
		}
	}

	private void serializeSlideItems(PrintWriter xmlFile, Slide slide) {
		for (SlideItem slideItem : slide.getItems()) {
			xmlFile.print("<item kind=");

			if (SlideItemType.TEXT.equals(slideItem.getType())) {
				xmlFile.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
				xmlFile.print(slideItem.getText());
			} else {
				if (SlideItemType.IMAGE.equals(slideItem.getType())) {
					xmlFile.print("\"image\" level=\"" + slideItem.getLevel() + "\" caption=\"" + slideItem.getText()
							+ "\">");
					xmlFile.print(JabberPointUtil.encode(slideItem.getMedia()));
				}
			}

			xmlFile.println("</item>");
		}
	}
}
