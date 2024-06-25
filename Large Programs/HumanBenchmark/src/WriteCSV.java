//***************************************************
//* Emely Seheon
//* CS351 Project2
//* This is a class to write scores to a csv file
//***************************************************

import java.io.*;

public class WriteCSV {
    private final String[][] scores = {{"name", "reaction_time", "sequence_memory", "aim_trainer", "chimp_test",
            "visual_memory", "typing", "number_memory", "verbal_memory"},
            {"0", "0", "0", "0", "0", "0", "0", "0", "0"}};

    public void setScore (String score, int type) {scores[1][type] = score;}

    public void writeCSV () {
        String filepath = "resources\\scores.csv";
        File file = new File(filepath);
        try (FileWriter outFile = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(outFile)) {
            for(String[] row : scores) {
                for (int i = 0; i < row.length; i++) {
                    if (i != 0) {
                        writer.write(",");
                    }
                    writer.write(row[i]);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}