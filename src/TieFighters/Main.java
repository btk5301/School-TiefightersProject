/*
Name: Brian Khunchana
netId: btk150130
*/
package TieFighters;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // check to see if file exists
        File file = new File("pilot_routes1.txt");
        if (!file.exists()) {
            System.out.println("File does not exist");
            System.exit(0);
        }
        // create scanner to scan the file
        Scanner input = new Scanner(file);
        // create 3d array to store pilot data
        double data[][][] = new double[20][16][2];
        //create an array to hold pilot names
        String pilots[] = new String[20];
        // array to hold amt of coordinates
        int coord[] = new int[20];
        //  index and numcoord variable to hold the index of the pilot and number of coordinates per pilot
        int index = 0;
        int numcoord = 0;
        // while loop to keep running until no more lines in file
        while (input.hasNextLine()) {
            String line = input.nextLine();
            // if empty string skip the line
            if (line.equals("")) {
                continue;
            }
            // pass line into lineparser function and increment index
            numcoord = lineParser(data,line,pilots,index);
            coord[index] = numcoord;
            index++;
        }
        // print area to file using printarea function
        // printArea(data, index, numcoord, pilots);
        printArea(data, index, coord, pilots);

    }

    //function to parse line strings
    public static int lineParser(double data[][][], String line, String pilots[], int index) {
        // create scanner to scan the line strings
        Scanner linescan = new Scanner(line);
        // store the name of the pilot into the pilot array
        pilots[index] = linescan.next();
        int numcoord = 0;
        // while loop to scan in each coordinate
        while (linescan.hasNext()) {
            // store the coordinate into coordinate variable
            String coordinate = linescan.next();
            // find the coma and grab that index
            int coma = coordinate.indexOf(',');
            // substring functions to get first and second coordinate
            String x = coordinate.substring(0, coma);
            String y = coordinate.substring(coma + 1);
            // store x and y values into the 3d array
            data[index][numcoord][0] = Double.parseDouble(x);
            data[index][numcoord][1] = Double.parseDouble(y);
            // increment the number of coordinates
            numcoord++;
        }
        //return number of coordinates
        return numcoord;
    }

    // function to print area to file
    public static void printArea(double data[][][], int index, /* int numcoord */ int coord[], String pilots[]) throws Exception {
        // create a printwriter object to print to the output file pilot_areas.txt
        PrintWriter writer = new PrintWriter("pilot_areas.txt");
        // create a double for loop to print out from thr 3d area
        for (int i = 0; i < index; i++) {
            // reset the area
            double area = 0;
            // use the area function to calculate the area from x and y values of the 3d array
            for (int j = 0; j < /* numcoord */ coord[i]; j++) {
                area += data[i][j][0] * data[i][j+1][1] - data[i][j][1] * data[i][j+1][0];
            }
            // find abs value of the area
            area = Math.abs(area);
            // multiply by 1/2
            area *= .5;
            // round the area
            area = Math.round(area*100.0)/100.0;
            // print the area to the file
            writer.printf("%s %.2f\n", pilots[i], area);
        }
        // close the writer object
        writer.close();
    }
}
