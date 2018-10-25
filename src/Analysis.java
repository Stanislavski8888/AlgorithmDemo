import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Analysis {

    public static void main(String[] args) {
        Analysis mInstance = new Analysis();

        BufferedReader bufferedReader = null;
        try {
            File file = new File("双色球.txt");
            if (!file.exists()) {
                throw new Exception("File not exists!!!");
            }
            FileReader reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            HashMap<String, Integer> normalResult = new HashMap<>();
            HashMap<String, Integer> specialResult = new HashMap<>();
            while (line != null) {
                // System.out.println(line);
                String[] numbers = line.split(" ");
                for (int i = 0; i < numbers.length; i++) {
                    if (i == numbers.length - 1) {
                        if (specialResult.containsKey(numbers[i])) {
                            int specialCount = specialResult.get(numbers[i]).intValue();
                            specialResult.put(numbers[i], ++specialCount);
                        } else {
                            specialResult.put(numbers[i], 1);
                        }
                        continue;
                    } else if (normalResult.containsKey(numbers[i])) {
                        int count = normalResult.get(numbers[i]).intValue();
                        normalResult.put(numbers[i], ++count);
                    } else {
                        normalResult.put(numbers[i], 1);
                    }
                }
                line = bufferedReader.readLine();
            }
            System.out.println("Normal Count:\n"+normalResult.toString());
            System.out.println("Special Count:\n"+specialResult.toString());
            
            mInstance.writeToFile(normalResult, specialResult);
            
            Collection<Integer> countCollection = normalResult.values();
            int sum = 0;
            for(Integer c : countCollection) {
                sum+= c.intValue();
            }
            System.out.println("normal sum: "+sum);
            
            countCollection = specialResult.values();
            int sumSpec = 0;
            for(Integer c : countCollection) {
                sumSpec+= c.intValue();
            }
            System.out.println("special sum: "+sumSpec);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private void writeToFile(HashMap<String, Integer> normResult, HashMap<String, Integer> specResult) {
        File file = new File("analysis.csv");
        if (file.exists()) {
            file.delete();
        }

        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            Set<String> normKeySet = normResult.keySet();
            bufferedWriter.write("Normal:\n");
            for(String number : normKeySet) {
                bufferedWriter.write(number+", "+normResult.get(number)+"\n");
            }
            bufferedWriter.write("Special:\n");
            Set<String> specKeySet = specResult.keySet();
            for(String spec : specKeySet) {
                bufferedWriter.write(spec+", "+specResult.get(spec)+"\n");
            }
            bufferedWriter.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            try {
                if(writer != null) writer.close();
                if(bufferedWriter != null) bufferedWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }*/
    }

}
