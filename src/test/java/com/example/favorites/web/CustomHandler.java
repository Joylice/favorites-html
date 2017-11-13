package com.example.favorites.web;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cuiyy on 2017/11/13.
 */
public class CustomHandler extends DefaultHandler {
    private String value = null;
    private List<UserItem> UserItemList = null;
    private UserItem UserItem = null;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        UserItemList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("organization".equals(qName)) {
            UserItem = new UserItem();
            String id = attributes.getValue("id");
            UserItem.setId(id);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        value = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName) {
            case "organization":
                UserItemList.add(UserItem);
                UserItem = null;
                break;
            case "name":
                UserItem.setUserName(value);
                break;
            case "password":
                UserItem.setPassWord(value);
                break;
            case "roles":
                UserItem.setRoles(Arrays.asList(value.split(",")));
                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public void setUserItemList(List<UserItem> UserItemList) {
        this.UserItemList = UserItemList;
    }

    public List<UserItem> getUserItemList() {
        return UserItemList;
    }
}
