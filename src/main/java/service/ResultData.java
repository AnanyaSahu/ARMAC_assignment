package service;

public class ResultData {


    public void showResults()  {
        CSVProcessor cs = new CSVProcessor();
        PartService partService = new PartService();
        int validRecords = cs.readCSV();
        Double avgPurchaseTimePlanned = partService.getAveragePurchaseTimePlanned();
        Double maxCost = partService.getMaxCost();

        System.out.println("Number of valid records => "+validRecords);
        System.out.println("Average Purchase Time Planned => "+ avgPurchaseTimePlanned);
        System.out.println("Max Cost => "+ maxCost);
    }

}
