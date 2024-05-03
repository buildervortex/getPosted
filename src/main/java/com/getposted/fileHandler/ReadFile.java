package com.getposted.fileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.getposted.logger.Logging;

public class ReadFile {
    private static Logger logger = Logging.getLogger(ReadFile.class.getName());
    
    public static List<String> readLines(String fileName) throws IOException{
        List<String> lines = new ArrayList<String>();
        String line = null;
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader(fileName));
        }
        catch(FileNotFoundException e){
            logger.severe(String.format("The FileNot found Exception occoured in the readLines method of the ReadFile.java. The file name is %. The error message is %s",fileName,e.getMessage()));
            throw e;
        }

        try{
            line = reader.readLine();
        }
        catch(IOException e){
            logger.severe(String.format("The IOException occoured in the readLines method of the ReadFile.java. The file name is %s. the exception message is %s",fileName ,e.getMessage()));
            throw e;
        }

        while(line!= null){
            lines.add(line);
            line = reader.readLine();
        }

        reader.close();
        return lines;
    }
}
