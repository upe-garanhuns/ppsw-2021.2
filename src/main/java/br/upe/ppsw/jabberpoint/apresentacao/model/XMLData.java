package br.upe.ppsw.jabberpoint.apresentacao.model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLData implements IData {
	
	protected static final String DEFAULT_API_TO_USE = "dom";

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

	@Override
	public String supportedExtension() {
		return "xml";
	}

	@Override
	public Presentation load(String file) {
		Presentation presentation = new Presentation();
		int slideNumber, itemNumber, max = 0, maxItems = 0;
		
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document document = builder.parse(new File(file));

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
		
		return presentation;
	}

	@Override
	public void save(Presentation presentation, String file) {
		// TODO Auto-generated method stub
		
	}
	
	private String getTitle(Element element, String tagName) {
		NodeList titles = element.getElementsByTagName(tagName);
		return titles.item(0).getTextContent();

	}
	
	private void loadSlideItem(Slide slide, Element item) {
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
			slide.appendSlideItem(new TextItem(level, item.getTextContent()));
		} else {
			if (IMAGE.equals(type)) {
				slide.appendSlideItem(new BitmapItem(level, item.getTextContent()));
			} else {
				System.err.println(UNKNOWNTYPE);
			}
		}
	}

}
