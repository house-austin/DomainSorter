import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DomainSorter {

    public static void main(String[] args) {
        System.out.println("Welcome! This program sorts domains based on the Second Level Domain! Please begin by supplying a file containing domains and subdomains.");
        Domain[] domain_list = readFile();
        sortDomains(domain_list);
        System.out.println("Domains sorted!");
        writeFile(domain_list);

    } //main method end


    public static Domain[] readFile() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter filename (Make sure to include file extension): ");
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


    public static void sortDomains(Domain[] domain_list) {

        long startTime = System.nanoTime();
       //Bubblesort alphabetically 
        for (int i = 0; i < domain_list.length - 1; i++) {

            for (int j = 0; j < domain_list.length - 1; j++) {

                // Compare to logic: a compareTo b = -1 since b comes after a
                //  b compareTo a = 1, a compareTo a = 0
                // The if statement is comparing the domain in index j to the adjacent domain in j + 1
                if (domain_list[j].getDomain().compareToIgnoreCase(domain_list[j + 1].getDomain()) > 0) {
                  
                    Domain temp = domain_list[j];
                    domain_list[j] = domain_list[j + 1];
                    domain_list[j + 1] = temp;
                }
            }
        }

        // Bubblesort by SLD
        for (int i = 0; i < domain_list.length - 1; i++) {

            for (int j = 0; j < domain_list.length - 1; j++) {

                // Compare to logic: a compareTo b = -1 since b comes after a
                // b compareTo a = 1, a compareTo a = 0
                // The if statement is comparing the domain in index j to the adjacent domain in j + 1
                if (domain_list[j].getSLD().compareToIgnoreCase(domain_list[j + 1].getSLD()) > 0) {
                  
                    Domain temp = domain_list[j];
                    domain_list[j] = domain_list[j + 1];
                    domain_list[j + 1] = temp;
                }
            }
        }
        long endTime = System.nanoTime();
        double elapsedTime = ((endTime - startTime));
        double elapsedTimeInSeconds = elapsedTime / 1_000_000_000.0;
        System.out.println("Start time: " + startTime);
        System.out.println("End time: " + endTime);
        System.out.println("Elapsed time: " + elapsedTime);
        System.out.println("Elapsed time (seconds): " + elapsedTimeInSeconds);
    } //sortDomain method end
    


} //DomainSorter class end


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
