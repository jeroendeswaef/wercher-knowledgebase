package com.wercher.knowledgebase;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.ListItemReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FilesystemImageItemReader implements ItemReader<Item> {

    private ListItemReader<Item> fileListReader;
    public FilesystemImageItemReader(String basePath) throws IOException {
        List lst = Files.find(Paths.get(basePath), 999, (p, bfa) -> bfa.isRegularFile()
                && p.getFileName().toString().matches(".*\\.jpeg")).map(fileName -> new Item(fileName.toString())).collect(Collectors.toList());
        fileListReader = new ListItemReader<>(lst);
    }

    @Override
    public Item read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return this.fileListReader.read();
    }
}
