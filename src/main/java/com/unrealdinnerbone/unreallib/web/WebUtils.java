package com.unrealdinnerbone.unreallib.web;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class WebUtils {
    public static <T extends ITableData> String makeHTML(String title, List<String> heads, List<T> tList) {
        StringBuilder base = new StringBuilder("""           
                <html>
                <head>
                <title>{title}</title>
                                    
                <!--Links to Style Sheets and JS-->
                <link rel="stylesheet" type="text/css" href="css/stats.css">
                <link rel="shortcut icon" href="http://www.belboz.tv/favicon.png">
                <script src="js/fixed.js" type="text/javascript"></script>
                <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" type="text/javascript"></script>
                <script src="js/sorttable.txt" type="text/javascript"></script>
                </head>
                                    
                <body>
                <div id="header2">
                </div>
                <div id="wrapper">
                <h1 text align="center">{title}</h1>
                <div id="wrapper1">
                <table class="sortable">
                <tr>"""
                .replace("{title}", title));
        for(String head : heads) {
            base.append("<th align=\"left\"><b>").append(head).append("</b></th>\n");
        }
        base.append("</tr></thead>");
        base.append(getTable(tList));
        base.append("""
                </table></div>
                </body>
                </html>
                """);
        return base.toString();

    }

    public static <T extends ITableData> String getTable(List<T> tList) {
        return tList.stream().map(t -> "<tr>" + fromValue(t) + "</tr>").collect(Collectors.joining());
    }

    public static <T extends ITableData> String fromValue(T t) {
        StringBuilder builder = new StringBuilder();
        t.getData().forEach(datum -> builder.append("<td align=\"left\" class=\"cell\">").append(datum).append("</td>"));
        return builder.toString();
    }

    public interface ITableData {
        List<String> getData();
    }
}
