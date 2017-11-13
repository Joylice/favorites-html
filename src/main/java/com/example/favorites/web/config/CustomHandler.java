package com.example.favorites.web.config;

import com.example.favorites.web.domain.User;
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
    private List<User> userList = new ArrayList<>();
    private User user = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("organization".equals(qName)) {
            user = new User();
            String id = attributes.getValue("id");
            user.setId(id);
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
                userList.add(user);
                user = null;
                break;
            case "name":
                user.setUserName(value);
                break;
            case "password":
                user.setPassWord(value);
                break;
            case "roles":
                user.setRoles(Arrays.asList(value.split(",")));
                break;
        }
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
