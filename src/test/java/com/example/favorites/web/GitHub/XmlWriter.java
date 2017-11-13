package com.example.favorites.web.GitHub;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * Created by 羊荣毅_L on 17-5-23.
 */
class XmlWriter {

    private XmlWriter() {
    }

    static void writeToFile(List<Item> list) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("new.xml"));
        writer.write("<?xml version=\"1.0\" encoding=\"gb2312\" standalone=\"yes\"?>\n" + "<XX1>\n");

        for (Item item:list){
            if (TextUtils.isEmpty(item.tagName)) return;
            StringBuilder builder = new StringBuilder();
            builder.append("\t<")
                    .append(item.tagName)
                    .append(TextUtils.isEmpty(item.id) ? "" : " id=\"" + item.id + "\" ")
                    .append(TextUtils.isEmpty(item.ele) ? "" : " ele=\"" + item.ele + "\" ");
            if ("".equals(item.text)) builder.append(" />");
            else {
                builder.append(">")
                        .append(item.text)
                        .append("</")
                        .append(item.tagName)
                        .append(">");
            }
            try {
                writer.write(builder.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.write("</XX1>");
        writer.flush();
        writer.close();
    }


}
