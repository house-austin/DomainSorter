import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DomainSorter {

    public static void main(String[] args) {
        System.out.println("Welcome! This program sorts domain lists! Please begin by supplying a file containing domains.");
        Domain[] domain_list = readFile();

        for (Domain domain : domain_list) {
            System.out.println(domain.getDomain());
        }

        writeFile(domain_list);


    } //main method end


    public static Domain[] readFile() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter filename (Make sure to include file extension): ");
        Domain[] domain_list = null;

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

                domain_list = new Domain[domain_count];
                fileScanner = new Scanner(file);
                domain_count = 0;

                while (fileScanner.hasNextLine()) {

                    domain_list[domain_count] = new Domain(fileScanner.nextLine());
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


    public static void writeFile(Domain[] domain_list) {

        try {
            PrintWriter writer = new PrintWriter("domain_list_Sorted.txt");
            for (Domain domain : domain_list) {
                writer.println(domain.getDomain());
            }
            writer.close();
            System.out.println("Created file domain_list_sorted.txt");
        }
        
        catch (FileNotFoundException e) {
                System.out.println("Could not write file.");
            }
    } //Write method end
    


} //DomainSorter class end

// Program logic, gets file input, grabs a string, seperates string by . array (lastindex - 2) will
// be domain

// Requirments: 1 array - string array from str.split
// 1 loop and 1 condtional statement - accomplised through menu and file looping
// 2 methods - at least 1 returns value & and at least 1 performs operation on array
// and uses array as paramet - maybe sorting file with array sorting
// 1 custom class
// basic input handling - read from a file


class Domain {

    private String domain;

    //Constructor
    public Domain (String domain) {
        this.domain = domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public String getSLD() {
        String[] separated = domain.split("\\.");
        int length = separated.length;
        String sld = separated[length - 2];
        return sld;
    }
}
