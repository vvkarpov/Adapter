package ru.vkarpov.dev;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
Study task: ADAPTER
*/

public class Solution {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner){
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {
            String firstName;
            String middleName;
            String lastName;
            Date birthDate;
            Person result = null;

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] array = line.split("\\s");//IVANOV IVAN IVANOVITCH 31 12 1950
                firstName = array[1];
                middleName = array[2];
                lastName = array[0];

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                String dateInString = array[3] + "-" + array[4] + "-" + array[5];
                birthDate = formatter.parse(dateInString);

                result = new Person(firstName, middleName, lastName, birthDate);
            }
            return result;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
