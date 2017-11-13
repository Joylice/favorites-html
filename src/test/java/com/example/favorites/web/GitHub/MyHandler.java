package com.example.favorites.web.GitHub;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 羊荣毅_L on 17-5-23.
 */
public class MyHandler extends DefaultHandler {

    private String currNodeName;
    private Item currItem = new Item();
    private List<Item> mList;


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        mList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        currNodeName = qName;
        if (currNodeName.equals("XX1")) return;
        currItem.tagName = qName;
        currItem.id = attributes.getValue("id");
        currItem.ele = attributes.getValue("ele");

    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (currNodeName.equals("XX1")) return;
        currItem.text = String.valueOf(ch, start, length).trim();
        if (!TextUtils.isEmpty(currItem.text))
            mList.add(currItem);
        currItem = new Item();
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endDocument() throws SAXException {
        super.endDocument();
        Collections.sort(mList);
        try {
            XmlWriter.writeToFile(mList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setmList(List<Item> mList) {
        this.mList = mList;
    }

    public List<Item> getmList() {
        return mList;
    }
}
