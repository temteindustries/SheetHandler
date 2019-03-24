package com.temteindustries.sheethandler.CSV;

import com.temteindustries.sheethandler.Cache.GuestCache;
import com.temteindustries.sheethandler.model.GuestObject;

import java.util.HashMap;

public class CSVGenerator {

    public static String GenerateCSVText(){

        HashMap<Integer, GuestObject> cache = GuestCache.getInstance();
        String CSVText = "id,firstName,lastName,ceremony,reception\n";

        for (int i = 1; i <= cache.size(); i ++){
            GuestObject guest = cache.get(i);
            CSVText = CSVText + i +','+guest.getfName()+','+guest.getlName()+','+guest.getCeremony()+','+guest.getReception()+"\n";
        }

        return CSVText;
    }
}
