package com.temteindustries.sheethandler.Controller;

import com.temteindustries.sheethandler.CSV.CSVGenerator;
import com.temteindustries.sheethandler.Cache.CacheUtils;
import com.temteindustries.sheethandler.Email.sendEmail;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}

