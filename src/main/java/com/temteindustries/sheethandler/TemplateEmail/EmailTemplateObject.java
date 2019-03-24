package com.temteindustries.sheethandler.TemplateEmail;
import org.springframework.boot.json.GsonJsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class EmailTemplateObject {



    public static String getHtml(List<Object> guestList){
        GsonJsonParser parser = new GsonJsonParser();

        String HTMLDoc;
        String HTMLTop = null;
        String HTMLBottom = null;

        try {
            HTMLTop = new String(Files.readAllBytes(Paths.get("src/main/resources/static/templates/RSVPemail-part1.html")), "UTF-8");
            HTMLBottom = new String(Files.readAllBytes(Paths.get("src/main/resources/static/templates/RSVPemail-part2.html")), "UTF-8");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String HTMLMiddle = "";
        for(int i = 0; i < guestList.size(); i++){

            Map<String,Object> guest = parser.parseMap(guestList.get(i).toString());

            HTMLMiddle = HTMLMiddle+"<tr>\n" +
                    "    <td>"+guest.get("fName")+"</td>\n" +
                    "    <td>"+guest.get("lName")+"</td>\n" +
                    "    <td>"+guest.get("ceremony")+"</td>\n" +
                    "    <td>"+guest.get("reception")+"</td>\n" +
                    "</tr>\n";
        }
        HTMLDoc = HTMLTop + HTMLMiddle + HTMLBottom;
        return HTMLDoc;
    }

}
