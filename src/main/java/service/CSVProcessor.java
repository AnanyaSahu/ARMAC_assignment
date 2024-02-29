package service;

import mainPackage.Main;
import model.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVProcessor {
     int countValidRecords = 0;


    public int readCSV()  {
        return preprocessCSVFile();
    }

    private int preprocessCSVFile() {
        try (InputStream inputStream = Main.class.getResourceAsStream("/data.csv")
        ){
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = " ";

            List<String[]> processedRecordList=   new ArrayList<>();
            List<String[]> originalRecordList=   new ArrayList<>();
            while((line=br.readLine())!=null)
            {
                originalRecordList.add(line.split(","));
                String str = "\""+line
                        .replace(';',',')
                        .replaceAll(",\\(", " \\(")
                        .replaceAll(",","\",\"")+"\"";

                processedRecordList.add(str.split(","));
            }
            convertToList(processedRecordList);
            return checkForValidRecord(originalRecordList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private int checkForValidRecord( List<String[]> originalRecordList) {
        originalRecordList.forEach( record ->{
            if(record.length == 7 ){
               countValidRecords++;
           }
        });
        return countValidRecords;
    }

    private void convertToList( List<String[]> processedRecordList) {
        List<Part> listOfParts = creatListForData(processedRecordList);
            new PartService().insertRecords(listOfParts);
    }

    private List<Part> creatListForData(List<String[]> listof) {
        List<Part> listOfParts = new ArrayList<>();
        listof.subList(1, listof.size()).forEach( listItem -> {

            if(listItem.length == 7){
                Part partObject = createPartObject(Arrays.asList(listItem));
                listOfParts.add(partObject);
            }
            else {
//                TODO
                // handle other type of error by removing and asserting remeoved rows
            }
        });
        return listOfParts;
    }

    private Part createPartObject(List<String> inputList) {
        Part part = new Part();
        part.setCode(Integer.parseInt(inputList.get(0).substring(1,inputList.get(0).length()-1)));
        part.setDescription(inputList.get(1).substring(1,inputList.get(1).length()-1));
        part.setPurchaseTimePlanned(Integer.parseInt(inputList.get(2).substring(1,inputList.get(2).length()-1)));
        part.setCost(Double.parseDouble(inputList.get(3).substring(1,inputList.get(3).length()-1)));
        part.setDemandForecast(Double.parseDouble(inputList.get(4).substring(1,inputList.get(4).length()-1)));
        part.setDemandPLCDO(inputList.get(5).substring(1,inputList.get(5).length()-1));
        part.setMin(Integer.parseInt(inputList.get(6).substring(1,inputList.get(6).length()-1)));

        return part;
    }
}
