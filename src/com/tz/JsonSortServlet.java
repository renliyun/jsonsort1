package com.tz;


import com.sun.deploy.util.StringUtils;
import com.tz.entity.CompartorS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class JsonSortServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //System.out.println(request.getParameter("name"));
        // String jsonStr = request.getParameter("jsonStr");
        String json = "{'张三':'65','李四':'80','王五:'60'}";
        System.out.println(json);
        String jsonStr = json.substring(json.indexOf("{") + 1).substring(0, json.lastIndexOf("}") - 1);
        List<String> list = Arrays.asList(jsonStr.split(","));
        List<CompartorS> list1 = new ArrayList<>();
        CompartorS compartorS = null;
        for (String s : list) {
            compartorS = new CompartorS();
            compartorS.setKey(s.split(":")[0].replaceAll("'", ""));
            compartorS.setValue(Integer.valueOf(s.split(":")[1].substring(1, s.split(":")[1].lastIndexOf("'"))));
            list1.add(compartorS);
        }
        Collections.sort(list1, new Comparator<CompartorS>() {
            @Override
            public int compare(CompartorS o1, CompartorS o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        List<String> strings = new ArrayList<>();
        for (CompartorS compartorS1 : list1) {
            StringBuilder str = new StringBuilder();
            str = str.append("'")
                    .append(compartorS1.getKey().toString())
                    .append("'")
                    .append(":")
                    .append("'")
                    .append(compartorS1.getValue())
                    .append("'")
            ;
            strings.add(str.toString());
        }
        StringBuilder result = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        //这一块代码使用StringUtils.join(list,  “,”)进行连接比较好，但是报错，没有找到合适的包
        for (int i = 0; i <= strings.size() - 1; i++) {
            stringBuilder.append(strings.get(i)).
                    append(",");
        }
        result = result.append("{")
                .append(stringBuilder.substring(0, stringBuilder.lastIndexOf(",")))
                .append("}");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<h1>传入的数据是：" + json + "<h1/>");
        printWriter.write("<h1>排序之后的数据是：" + result.toString() + "</h1>");
        printWriter.close();
    }
}
