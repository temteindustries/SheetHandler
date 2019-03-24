package com.temteindustries.sheethandler.Cache;

import com.temteindustries.sheethandler.model.GuestObject;
import org.springframework.boot.json.GsonJsonParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheUtils {

    public static void addToCache(List<Object> guestList){
        HashMap<Integer, GuestObject> cache = GuestCache.getInstance();
        GsonJsonParser parser = new GsonJsonParser();
        for(int i = 0; i< guestList.size(); i++){
            Map<String,Object> guest = parser.parseMap(guestList.get(i).toString());
            GuestObject guestObject = new GuestObject();
            guestObject.setFName(guest.get("fName").toString());
            guestObject.setLName(guest.get("lName").toString());
            guestObject.setCeremony(guest.get("ceremony").toString());
            guestObject.setReception(guest.get("reception").toString());
            cache.put(cache.size()+1,guestObject);
        }
    }
}
