package com.example.favorites.web.GitHub;

import java.io.Serializable;
/**
 * Created by 羊荣毅_L on 17-5-23.
 */

public class Item implements Comparable, Serializable {

    private static final long serialVersionUID = 1L;

    String tagName;
    String id;
    String ele;
    String text;

    @Override
    public String toString() {
        return "Item{" +
                "tagName='" + tagName + '\'' +
                ", id='" + id + '\'' +
                ", ele='" + ele + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Item)) return 0;
        Item newItem = (Item) o;
        if (tagName == null || newItem.tagName == null) return 0;
        return tagName.compareTo(newItem.tagName);
    }

}
