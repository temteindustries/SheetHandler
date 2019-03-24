package com.temteindustries.sheethandler.Controller;

import com.temteindustries.sheethandler.CSV.CSVGenerator;
import com.temteindustries.sheethandler.Cache.CacheUtils;
import com.temteindustries.sheethandler.Cache.GuestCache;
import com.temteindustries.sheethandler.Email.sendEmail;
import com.temteindustries.sheethandler.model.GuestObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class controller {

    @PostMapping("/update")
    public ResponseEntity<String> updateGuestList(
            @RequestBody String stmnt) {
        sendEmail sendemail = new sendEmail();
        GsonJsonParser parser = new GsonJsonParser();
        List<Object> guestList = parser.parseList(stmnt);
        CacheUtils.addToCache(guestList);
        boolean msgSent = sendemail.sendMail(guestList);

        if (msgSent){
            System.out.println("Email Sent");
            return new ResponseEntity<>("Email Sent", HttpStatus.OK);
        }
        else{
            System.out.println("Error sending email");
            return new ResponseEntity<>("Email Sent", HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @GetMapping(value = "/getCSV")
    public ResponseEntity<String> getCSVString() {
        String CSVString = CSVGenerator.GenerateCSVText();
        return new ResponseEntity<>(CSVString, HttpStatus.OK);
    }

    @GetMapping(value = "/clearCache")
    public ResponseEntity<String> clearCache() {
        HashMap<Integer, GuestObject> cache = GuestCache.getInstance();
        cache.clear();
        return new ResponseEntity<>("Cache size: "+cache.size(), HttpStatus.OK);
    }

    @PostMapping(value = "/updateCache",produces = "application/json")
    public ResponseEntity<String> updateCache(@RequestBody String json) {
        GsonJsonParser parser = new GsonJsonParser();
        List<Object> guestList = parser.parseList(json);
        CacheUtils.addToCache(guestList);
        return new ResponseEntity<>(getCache(), HttpStatus.OK);
    }

    @GetMapping(value = "/getCache",produces = "application/json")
    public ResponseEntity<String> getTheCache() {
        return new ResponseEntity<>(getCache(), HttpStatus.OK);
    }

    public String getCache() {
        HashMap<Integer, GuestObject> cache = GuestCache.getInstance();
        String response = "[";
        for (int i = 1; i <= cache.size(); i++){
            if(i == 1){
                response = response + "\n"+cache.get(i).toString();
            }else{
                response = response + ",\n"+ cache.get(i).toString();
            }
        }
        return response+']';
    }
}

