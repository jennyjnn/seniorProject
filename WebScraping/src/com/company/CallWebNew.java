package com.company;

import com.jaunt.*;
import com.jaunt.util.IOUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;

public class CallWebNew {
    public static void main(String[] args) {

        getContentEachMeds();
//        UserAgent userAgent = new UserAgent();
//        try {
            //Get url for each medications

//            userAgent.open(new File("web/pobpad/_listMeds.html"));
//            Element listMeds = userAgent.doc.findFirst("<ul class=\"menu-children\">");
//            Elements meds = listMeds.findEach("<a");
//            int count = 1;
//            for (Element med : meds) {
//                String name = med.getAt("title");
//                String url = med.getAt("href");
//                System.out.println(count + " : " + name + " - " + url);
//                count++;
////                File fileName = new File("web/pobpad/_medName.txt");
////                IOUtil.append(fileName, name + "\n");
//                File fileUrl = new File("web/pobpad/_medUrl.txt");
//                IOUtil.append(fileUrl, name + " : " + url + "\n");
//            }

            // Call Web
//            ArrayList<String> webArr = new ArrayList<String>();
//            File webFile = new File("web/pobpad/_medUrl.txt");
//            BufferedReader url = new BufferedReader(new FileReader(webFile));
//            String webMed;
//            while ((webMed = url.readLine()) != null) {
//                webArr.add(webMed);
//            }
//
//            for (int i = 150; i < webArr.size(); i++) {
//                String[] urlSplit = webArr.get(i).split(" : ");
//                String urlMed = URLEncoder.encode(urlSplit[1].substring(23), "UTF-8");
//                userAgent.visit("https://www.pobpad.com/" + urlMed);
//                String web = userAgent.doc.outerHTML().trim();
//                File file = new File("web/pobpad/" + urlSplit[0] + ".html");
//                IOUtil.write(file, web);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ResponseException e) {
//            e.printStackTrace();
//        }

    }

    public void getUrl() {
        UserAgent userAgent = new UserAgent();
        try {
            userAgent.open(new File("web/pobpad/_listMeds.html"));
            Element listMeds = userAgent.doc.findFirst("<ul class=\"menu-children\">");
            Elements meds = listMeds.findEach("<a");
            int count = 1;
            for (Element med : meds) {
                String name = med.getAt("title");
                String url = med.getAt("href");
                System.out.println(count + " : " + name + " - " + url);
                count++;
//                File fileName = new File("web/pobpad/_medName.txt");
//                IOUtil.append(fileName, name + "\n");
                File fileUrl = new File("web/pobpad/_medUrl.txt");
                IOUtil.append(fileUrl, name + " : " + url + "\n");
//
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }
    }

    public static void getContentEachMeds() {
        UserAgent userAgent = new UserAgent();
        try {
            ArrayList<String> nameArr = new ArrayList<String>();
            File webFile = new File("web/pobpad/_medName.txt");
            BufferedReader name = new BufferedReader(new FileReader(webFile));
            String webMed;
            while ((webMed = name.readLine()) != null) {
                nameArr.add(webMed);
            }

            for (int i = 341; i < nameArr.size(); i++) {
                userAgent.open(new File("web/pobpad/"+ nameArr.get(i)+".html"));
                Elements tableInfo = userAgent.doc.findFirst("<table").findEach("<td");
                for (Element info: tableInfo) {
                    String content = info.getTextContent();
                    File file = new File("web/pobpad/textFile/" + nameArr.get(i) + ".txt");
                    IOUtil.append(file, content + " ///// ");
                }
                System.out.println(i+1 + " - " + nameArr.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }
}