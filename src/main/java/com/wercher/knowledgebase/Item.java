package com.wercher.knowledgebase;

/**
 * A knowledge base item corresponds with a file. This is the source of truth for all data.
 * Other structures can be generated (db, cache) to speed up access.
 */
public class Item {
    private String relativePath;

    public Item() {
    }

    public Item(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    @Override
    public String toString() {
        return "Item{" +
                "relativePath='" + relativePath + '\'' +
                '}';
    }
}
