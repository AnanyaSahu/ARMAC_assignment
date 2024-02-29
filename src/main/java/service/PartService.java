package service;

import model.Part;
import repository.PartRepo;

import java.util.List;

public class PartService {

    public int getValidRecords()  {
        return new CSVProcessor().findValidRecords();

    }

    public Double getMaxCost()  {
        return new PartRepo().getMaxCost();
    }

    public Double getAveragePurchaseTimePlanned()  {
        return new PartRepo().getAvgPurchaseTimePlanned();


    }
    public void insertRecords(List<Part> listOfParts)  {
//        listOfParts.forEach(part -> {
//            new PartRepo().savePartData(part);
//        });


    }
}
