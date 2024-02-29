package service;

import mainPackage.Main;
import model.Part;
//import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVProcessor {
//     String csvDataFilePath = "/data.csv";
//     String processedCSVFilePath = "/processedCSV.csv";
     int countValidRecords = 0;


    public int readCSV()  {

        return preprocessCSVFile();
//        convertToList(processedCSVFilePath);

    }

    private int preprocessCSVFile() {

//        File f = new File(processedCSVFilePath);

        try (InputStream inputStream = Main.class.getResourceAsStream("/data.csv");
//             FileReader reader = new FileReader(csvDataFilePath);
//             FileWriter fw = new FileWriter(f)
        ){
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = " ";
//
//            System.out.println("br.readLine() "+br.readLine());
            List<String[]> processedRecordList=   new ArrayList<>();
            List<String[]> originalRecordList=   new ArrayList<>();
            while((line=br.readLine())!=null)
            {
                originalRecordList.add(line.split(","));
                String str = "\""+line
                        .replace(';',',')
                        .replaceAll(",\\(", " \\(")
                        .replaceAll(",","\",\"")+"\"";
//                fw.write(str);
//                fw.write("\n");
//                System.out.println(str);
//                System.out.println(Arrays.toString(str.split(",")));
                processedRecordList.add(str.split(","));

            }

//            System.out.println(processedRecordList);
//            System.out.println("Number of valid records: "+validRecordCount);
            convertToList(processedRecordList);
            return checkForValidRecord(originalRecordList);



        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private int checkForValidRecord( List<String[]> originalRecordList) {
//        String line = " ";
//        while((line=br.readLine())!=null)
//        {
//            String[] s = line.split(",");
        originalRecordList.forEach( record ->{
            if(record.length == 7 ){
               countValidRecords++;
           }
        });
        return countValidRecords;


//           if(s.length == 7 ){
//               countValidRecords++;
//           }
//
//        }
    }

    private void convertToList( List<String[]> processedRecordList) {
        List<Part> listOfParts = new ArrayList<>();
//        try (InputStream inputStream = Main.class.getResourceAsStream(filepath);
//        ) {
            //             CSVReader reader = new CSVReader(new FileReader(filepath))
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = " ";
//            List<String[]> listof=  reader.readAll();
//            List<String[]> processedRecordList=   new ArrayList<>();
//            while((line=br.readLine())!=null)
//            {
//                processedRecordList.add(line.split(","));
//            }
            listOfParts = creatListForData(processedRecordList);

//            listof.subList(1, listof.size()).forEach( listItem -> {
//                if(listItem.length == 7){
//                    if(Objects.equals(csvDataFilePath, filepath)){
//                        countValidRecords++;
//                    }
//                    Part partObject = createPartObject(Arrays.asList(listItem));
//                    listOfParts.add(partObject);
//
//                    // save to db
//                }
//
//                else {
//                    // remove recvords
//                    // handle other type of error by removing and asserting remeoved rows
//                }
//            });
            new PartService().insertRecords(listOfParts);
//           listOfParts.forEach(partObj -> {
//               System.out.println(partObj.getCode());
//            });
//
//        } catch (IOException e){
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

    }

    private List<Part> creatListForData(List<String[]> listof) {
        List<Part> listOfParts = new ArrayList<>();
        listof.subList(1, listof.size()).forEach( listItem -> {

            if(listItem.length == 7){
//                if(Objects.equals(dataType, "original")){
//                    countValidRecords++;
//                }
                Part partObject = createPartObject(Arrays.asList(listItem));
                listOfParts.add(partObject);

                // save to db
            }

            else {
                // remove recvords
                // handle other type of error by removing and asserting remeoved rows
            }
        });
        return listOfParts;
    }

    private Part createPartObject(List<String> inputList) {
        Part part = new Part();
//        System.out.println(inputList.get(1).substring(1,inputList.get(1).length()-1) );
        part.setCode(Integer.parseInt(inputList.get(0).substring(1,inputList.get(0).length()-1)));
        part.setDescription(inputList.get(1).substring(1,inputList.get(1).length()-1));
        part.setPurchaseTimePlanned(Integer.parseInt(inputList.get(2).substring(1,inputList.get(2).length()-1)));
        part.setCost(Double.parseDouble(inputList.get(3).substring(1,inputList.get(3).length()-1)));
        part.setDemandForecast(Double.parseDouble(inputList.get(4).substring(1,inputList.get(4).length()-1)));
        part.setDemandPLCDO(inputList.get(5).substring(1,inputList.get(5).length()-1));
        part.setMin(Integer.parseInt(inputList.get(6).substring(1,inputList.get(6).length()-1)));

        return part;


    }

    public int findValidRecords(){
//        convertToList(csvDataFilePath);
        return  countValidRecords -1;
    }
}
