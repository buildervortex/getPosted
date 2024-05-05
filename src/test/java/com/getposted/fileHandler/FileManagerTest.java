package com.getposted.fileHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.getposted.enums.Stored;
import com.getposted.system.Sysenv;

public class FileManagerTest {

    private static String testPath = Paths.get("src/test/java/com/getposted/fileHandler").toAbsolutePath().toString()+"/testDirectory";

    @BeforeClass
    public static void createSampleDirectory(){
        Sysenv.setEnv("FILESYSTEMLOCATION", testPath);
        FileManager.createDirectory(testPath);
    }

    @Test
    public void testStoreFile() throws IOException{
        String sampleString = "Sample Text For Storing";
        byte[] bytes = sampleString.getBytes(StandardCharsets.UTF_8);
        InputStream stream = new ByteArrayInputStream(bytes);
        String fileName = "hello.txt";

        long size = FileManager.storeFile(fileName, stream, Stored.AUTHORPROFILEPICTURE);
        assertEquals(size, bytes.length);
        stream = new ByteArrayInputStream(bytes);
        size = FileManager.storeFile(fileName, stream, Stored.PUBLICATIONPDF);
        assertEquals(size, bytes.length);
        stream = new ByteArrayInputStream(bytes);
        size = FileManager.storeFile(fileName, stream, Stored.PUBLICATIONTHUMBNAIL);
        assertEquals(size, bytes.length);
    }

    @Ignore
    @Test
    public void testRetrivingFile() throws IOException{
        String sampleString = "Sample Text For Storing";
        byte[] bytes = sampleString.getBytes(StandardCharsets.UTF_8);
        InputStream stream = new ByteArrayInputStream(bytes);
        String fileName = "hello.txt";

        long size = FileManager.storeFile(fileName, stream, Stored.AUTHORPROFILEPICTURE);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        long retrivedSize = FileManager.retriveFile(fileName, outputStream, Stored.AUTHORPROFILEPICTURE);

        String resultedString = outputStream.toString(StandardCharsets.UTF_8.name());

        assertEquals(resultedString,sampleString);
        assertEquals(size,retrivedSize);
        assertEquals(retrivedSize,bytes.length);
    }

    @Test
    public void testIsExists(){
        assertTrue(FileManager.isExists(testPath));
    }

    @Test
    public void testIsADirectory(){
        assertTrue(FileManager.isADirectory(testPath));
    }

    @Test
    public void testCreateADirectory(){
        String path = testPath+"/myDirectory";
        FileManager.createDirectory(path);
        assertTrue(FileManager.isADirectory(path));
    }

    @Test
    public void testDeleteFileOrDirectory(){
        String path = testPath+"/myDirectory20";
        FileManager.createDirectory(path);
        assertTrue(FileManager.isADirectory(path));
        FileManager.deleteFileOrDirectory(path);
        assertFalse(FileManager.isExists(path));
    }

    @Test
    public void testWriteToFile() throws IOException{
        String filePath = testPath+"/hello.txt";
        String content = "helloworld";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        InputStream stream = new ByteArrayInputStream(bytes);

        long size = FileManager.writeToFile(stream,filePath);
        File file = new File(filePath);

        assertTrue(content.length()==file.length());
        assertEquals(size, bytes.length);
        assertTrue(FileManager.isExists(filePath));
    }

    @Test
    public void testReadFromFile() throws IOException{
        String filePath = testPath+"/hello10.txt";
        String content = "helloworld";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        InputStream stream = new ByteArrayInputStream(bytes);

        FileManager.writeToFile(stream,filePath);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();        
        
        long size = FileManager.readFromFile(outputStream, filePath);

        String resultedString = outputStream.toString(StandardCharsets.UTF_8.name());
        
        assertEquals(resultedString,content);
        assertTrue(size == content.length());
    }

    @Test
    public void testAbsPath(){
        String relativePath = "src/test/java/com/getposted/fileHandler/testDirectory";
        String absolutePath = FileManager.getAbsPath(relativePath);

        assertEquals(absolutePath,testPath);
    }

    @AfterClass
    public static void deleteTestDirectory(){
        Sysenv.deleteEnv("FILESYSTEMLOCATION");
        FileManager.deleteRecursively(testPath);
    }
}
