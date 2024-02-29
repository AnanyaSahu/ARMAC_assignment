package service;

import com.opencsv.CSVReader;
import mainPackage.Main;
import model.Part;
//import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CSVProcessor {
     String csvDataFilePath = "/data.csv";
     String processedCSVFilePath = "/processedCSV.csv";
     int countValidRecords = 0;


    public void readCSV()  {

        preprocessCSVFile("");
        convertToList(processedCSVFilePath);

    }

    private void preprocessCSVFile(String csvDataFilePath) {

        File f = new File(processedCSVFilePath);

        try (InputStream inputStream = Main.class.getResourceAsStream("/data.csv");
//             FileReader reader = new FileReader(csvDataFilePath);
             FileWriter fw = new FileWriter(f)
        ){
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = " ";

            while((line=br.readLine())!=null)
            {
                String str = "\""+line
                        .replace(';',',')
                        .replaceAll(",\\(", " \\(")
                        .replaceAll(",","\",\"")+"\"";
                fw.write(str);
                fw.write("\n");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void convertToList(String filepath) {
        List<Part> listOfParts = new ArrayList<>();
        try (InputStream inputStream = Main.class.getResourceAsStream(filepath);


        ) {
            //             CSVReader reader = new CSVReader(new FileReader(filepath))
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = " ";
//            List<String[]> listof=  reader.readAll();
            List<String[]> listof=   new ArrayList<>();
            while((line=br.readLine())!=null)
            {
                listof.add(line.split(","));
            }



            listof.subList(1, listof.size()).forEach( listItem -> {
                if(listItem.length == 7){
                    if(Objects.equals(csvDataFilePath, filepath)){
                        countValidRecords++;
                    }
                    Part partObject = createPartObject(Arrays.asList(listItem));
                    listOfParts.add(partObject);

                    // save to db
                }

                else {
                    // remove recvords
                    // handle other type of error by removing and asserting remeoved rows
                }
            });
            new PartService().insertRecords(listOfParts);
//           listOfParts.forEach(partObj -> {
//               System.out.println(partObj.getCode());
//            });

        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    private Part createPartObject(List<String> inputList) {
        Part part = new Part();
        part.setCode(Integer.parseInt(inputList.get(0)));
        part.setDescription(inputList.get(1));
        part.setPurchaseTimePlanned(Integer.parseInt(inputList.get(2)));
        part.setCost(Double.parseDouble(inputList.get(3)));
        part.setDemandForecast(Double.parseDouble(inputList.get(4)));
        part.setDemandPLCDO(inputList.get(5));
        part.setMin(Integer.parseInt(inputList.get(6)));

        return part;


    }

    public int findValidRecords(){
        convertToList(csvDataFilePath);
        return  countValidRecords;
    }
}
