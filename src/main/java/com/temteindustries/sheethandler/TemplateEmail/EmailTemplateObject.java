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

        String HTMLMiddle = "";
        for(int i = 0; i < guestList.size(); i++){

            Map<String,Object> guest = parser.parseMap(guestList.get(i).toString());

            HTMLMiddle = HTMLMiddle+"<tr>\n" +
                    "    <td>"+guest.get("firstname")+"</td>\n" +
                    "    <td>"+guest.get("lastname")+"</td>\n" +
                    "    <td>"+guest.get("ceremony")+"</td>\n" +
                    "    <td>"+guest.get("reception")+"</td>\n" +
                    "</tr>\n";
        }
        HTMLDoc = getRSVP1() + HTMLMiddle + getRSVP2();
        return HTMLDoc;
    }

    private static String getRSVP1(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Temte Wedding</title>\n" +
                " \n" +
                "</head>\n" +
                "<body>\n" +
                "<table style=\"color: #090940; text-align: center; border: 2px solid #A18700; border-collapse: collapse;\">\n" +
                "\n" +
                "\t<tr><td colspan=\"4\"><h1 style=\"text-align: center; color: #A18700; font-size: 35px; font-weight: bold;\">New RSVP</h1></td></tr>\n" +
                "\t<tr id=\"header\" style=\"text-align: center; color: #090940; font-size: 22px; font-weight: bold;\">\n" +
                "\t\t<td style=\" border: 2px solid #A18700; \"><h4>First Name</h4></td>\n" +
                "\t\t<td style=\" border: 2px solid #A18700; \"><h4>Last Name</h4></td>\n" +
                "\t\t<td style=\" border: 2px solid #A18700; \"><h4>Ceremony</h4></td>\n" +
                "\t\t<td style=\" border: 2px solid #A18700; \"><h4>Reception</h4></td>\n" +
                "\t</tr>";
    }

    private static String getRSVP2(){
        return "\n" +
                "\t</table>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
