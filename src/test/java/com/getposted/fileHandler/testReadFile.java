package com.getposted.fileHandler;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class testReadFile {

    @Test
    public void testFileReadLines() throws IOException {
        List<String> lines = ReadFile
                .readLines(Paths.get("src/test/resources/databaseTables.txt").toAbsolutePath().toString());
        assertTrue(lines.size() >= 19);
    }

    @Test
    public void testResourceReadLines() throws IOException {
        List<String> lines = ReadFile.readLines("databaseTables.txt", Thread.currentThread().getContextClassLoader());
        assertTrue(lines.size() >= 19);
    }

}
