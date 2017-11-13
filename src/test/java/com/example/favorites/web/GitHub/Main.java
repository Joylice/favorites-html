package com.example.favorites.web.GitHub;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
/**
 * Created by 羊荣毅_L on 17-5-23.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("old.xml");
        MyHandler handler = new MyHandler();
        SAXParserFactory.newInstance().newSAXParser().parse(file, handler);
    }


}
