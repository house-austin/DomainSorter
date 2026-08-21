import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DomainSorter {

    public static void main(String[] args) {
        System.out.println("Welcome! This program sorts domain lists! Please begin by supplying a file containing domains.");
        String[] domain_list = readFile();

        for (String domain : domain_list) {
            System.out.println(domain);
        }

        writeFile(domain_list);


    } //main method end


    public static String[] readFile() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter filename (Make sure to include file extension): ");
        String[] domain_list = null;

        int domain_count = 0;
        while (domain_count == 0) {

            String path_input = scanner.nextLine();

            try {

                File file = new File(path_input);
                Scanner fileScanner = new Scanner(file);

                while (fileScanner.hasNextLine()) {

                    fileScanner.nextLine();
                    domain_count++;
                }

                fileScanner.close();

                domain_list = new String[domain_count];
                fileScanner = new Scanner(file);
                domain_count = 0;

                while (fileScanner.hasNextLine()) {

                    domain_list[domain_count] = fileScanner.nextLine();
                    domain_count++;
                }

                System.out.println("File loaded!");
                fileScanner.close();


            }

            catch (FileNotFoundException e) {
                System.out.println("Could not read file. Please try again");
            }
        }
        
        scanner.close();
        return domain_list;

    } //Read method end


    public static void writeFile(String[] domain_list) {

        try {
            PrintWriter writer = new PrintWriter("domain_list_Sorted.txt");
            for (String domain : domain_list) {
                writer.println(domain);
            }
            writer.close();
            System.out.println("Created file domain_list_sorted.txt");
        }
        
        catch (FileNotFoundException e) {
                System.out.println("Could not write file.");
            }
    } //Write method end
    


} //DomainSorter class end

// Program logic, gets file input, grabs a string, seperates string by . array (lastindex - 1) will
// be domain

// Requirments: 1 array - string array from str.split
// 1 loop and 1 condtional statement - accomplised through menu and file looping
// 2 methods - at least 1 returns value & and at least 1 performs operation on array
// and uses array as paramet - maybe sorting file with array sorting
// 1 custom class
// basic input handling - read from a file

/*
class Domain {

    private String domain;

    pubic Domain(String domain) {

        this.domain = domain;
    }

    public getDomain() {
        return domain;
    }

    public void getSLD() {
        seperated = domain.split(".");
        int arLength = seperated.length();
        String sld = domain[size-1];
        return sld;
    }


}
    */