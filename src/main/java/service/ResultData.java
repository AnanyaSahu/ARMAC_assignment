package service;

public class ResultData {


    public void showResults()  {
        CSVProcessor cs = new CSVProcessor();
        PartService rr = new PartService();
//        cs.readCSV();
        rr.getValidRecords();
////        rr.insertRecords();
        rr.getAveragePurchaseTimePlanned();
        rr.getMaxCost();


    }

}
