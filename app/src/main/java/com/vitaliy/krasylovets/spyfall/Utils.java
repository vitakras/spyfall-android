package com.vitaliy.krasylovets.spyfall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Utils Class for random Functions
 * Created by vitaliy on 2016-02-08.
 */
public class Utils {

    /**
     * Reads A String from input String and returns a String object
     * @param inputStream InputString we want to read in
     * @return content of the input String
     * @throws IOException Failed to Read the file
     */
    public static String readResourceData(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line; // Stores line it reads
        StringBuilder output = new StringBuilder();

        while((line = bufferedReader.readLine()) != null) {
            output.append(line);
        }

        return output.toString();
    }
}
