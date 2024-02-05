package org.csbk;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;


public class GudimParamsReader {

    public static GudimParams parseTxtFile(String path) {
        GudimParams gudimParams = new GudimParams();
        ArrayList<String> tempParams = new ArrayList<>();
        File textFile = new File(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(textFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempParams.add(line);
            }
        } catch (IOException e) {
            gudimParams.setId(0);
            gudimParams.setIsOk(2,1);
            gudimParams.setLastUpdated(0);
            gudimParams.setVersion(0);
            gudimParams.setInTownTpod("99");
            gudimParams.setReserv1Lvl("99");
            gudimParams.setReserv2Lvl("99");
            gudimParams.setReserv1Tpod("99");
            gudimParams.setReserv2Tpod("99");
            gudimParams.setWell1Tpod("99");
            gudimParams.setWell2Tpod("99");
            gudimParams.setStreet("5");
            gudimParams.setInTownFlow("20");
            e.printStackTrace();
        }
        for (int i = 0; i < tempParams.size(); i++) {
            gudimParams.setId(0);
            gudimParams.setIsOk(1,0);
            gudimParams.setLastUpdated(0);
            gudimParams.setInTownTpod(tempParams.get(0));
            gudimParams.setReserv1Lvl(tempParams.get(1));
            gudimParams.setReserv2Lvl(tempParams.get(2));
            gudimParams.setReserv1Tpod(tempParams.get(3));
            gudimParams.setReserv2Tpod(tempParams.get(4));
            gudimParams.setWell1Tpod(tempParams.get(5));
            gudimParams.setWell2Tpod(tempParams.get(6));
            gudimParams.setStreet(tempParams.get(7));
            gudimParams.setInTownFlow(tempParams.get(8));
        }
        return gudimParams;
    }



}