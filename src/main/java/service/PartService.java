package service;

import model.Part;
import repository.PartRepo;

import java.util.List;

public class PartService {

    public void getValidRecords()  {
        System.out.println("Number of valid records: "+new CSVProcessor().findValidRecords());
    }

    public void getMaxCost()  {
        System.out.println(new PartRepo()
                .getMaxCost());
    }

    public void getAveragePurchaseTimePlanned()  {
        System.out.println(new PartRepo()
                .getAvgPurchaseTimePlanned());

    }
    public void insertRecords(List<Part> listOfParts)  {
//        listOfParts.forEach(part -> {
//            new PartRepo().savePartData(part);
//        });


    }
}
