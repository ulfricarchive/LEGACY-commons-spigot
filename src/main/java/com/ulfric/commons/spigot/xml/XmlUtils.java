package com.ulfric.commons.spigot.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ulfric.commons.exception.Try;

// TODO move to commons
public enum XmlUtils {

	;

	private static final DocumentBuilder DOCUMENTS = Try.to(DocumentBuilderFactory.newInstance()::newDocumentBuilder);

	public static Document parseIncompleteDocument(String xml)
	{
		return XmlUtils.parseDocument("<doc>" + xml + "</doc>");
	}

	public static Document parseDocument(String xml)
	{
		InputSource source = new InputSource(new StringReader(xml));
		return Try.to(() -> XmlUtils.DOCUMENTS.parse(source));
	}

}