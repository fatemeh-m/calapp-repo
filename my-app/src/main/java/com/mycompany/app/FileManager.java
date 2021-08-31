package com.mycompany.app;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * This class contains methods working with files
 */
public class FileManager {

    public static LinkedList<Expression> readExpressions(String pathname)
            throws FileNotFoundException, InputMismatchException{

        LinkedList<Expression> expressionList = new LinkedList<>();
        double op1, op2;
        String operator;

        try (Scanner scanner = new Scanner(new File(pathname))) {
            while (scanner.hasNext()){
                op1 = scanner.nextDouble();
                if ((operator = scanner.next()).length() != 1)
                    throw new InputMismatchException();
                op2 = scanner.nextDouble();
                expressionList.add(new Expression(op1,op2,operator.charAt(0)));
            }
        }
        return expressionList;
    }

    public static void writeResults(String pathname, LinkedList<Expression> expressions) throws IOException{
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathname))) {
            while (bufferedReader.ready())
                lines.add(bufferedReader.readLine());
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathname))) {
            for (String line : lines)
                bufferedWriter.write(line + " = " + expressions.poll().getResult() + "\n");
        }
    }
}
