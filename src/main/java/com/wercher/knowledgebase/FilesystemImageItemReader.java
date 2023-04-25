package com.wercher.knowledgebase;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

import java.util.List;

public class FilesystemImageItemReader implements ItemReader<Item> {

    private ListItemReader<Item> fileListReader;
    public FilesystemImageItemReader(String basePath) {
        // TODO walk fs, convert to list & provide to the ListItemReader
        fileListReader = new ListItemReader<>(List.of(new Item("a"), new Item("b"), new Item("c")));
    }

    @Override
    public Item read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return this.fileListReader.read();
    }
}
