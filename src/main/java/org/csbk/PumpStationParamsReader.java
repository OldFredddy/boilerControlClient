package org.csbk;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PumpStationParamsReader {
    public static PumpStation parseTxtFile(String path) {
        PumpStation pumpStation = new PumpStation();
        ArrayList<String> tempParams = new ArrayList<>();
        File textFile = new File(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(textFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempParams.add(line);
            }
        } catch (IOException e) {
            pumpStation.setId(0);
            pumpStation.setIsOk(2,1);
            pumpStation.setLastUpdated(0);
            pumpStation.setFromPumpStationTpod("99");
            pumpStation.setReserv1Lvl("99");
            pumpStation.setReserv2Lvl("99");
            pumpStation.setReserv1Tpod("99");
            pumpStation.setReserv2Tpod("99");
            pumpStation.setForCityFlow("99");
            pumpStation.setMagicIndicator1("5");
            pumpStation.setMagicIndicator2("10");
            pumpStation.setMagicIndicator3("15");
            pumpStation.setMagicIndicator4("20");
            e.printStackTrace();
        }
        for (int i = 0; i < tempParams.size(); i++) {
            pumpStation.setId(0);
            pumpStation.setIsOk(1,0);
            pumpStation.setLastUpdated(0);
            pumpStation.setFromPumpStationTpod(tempParams.get(0));
            pumpStation.setReserv1Lvl(tempParams.get(1));
            pumpStation.setReserv2Lvl(tempParams.get(2));
            pumpStation.setReserv1Tpod(tempParams.get(3));
            pumpStation.setReserv2Tpod(tempParams.get(4));
            pumpStation.setForCityFlow(tempParams.get(5));
            pumpStation.setStreet(tempParams.get(6));
        }
        pumpStation.setMagicIndicator1("5");
        pumpStation.setMagicIndicator2("10");
        pumpStation.setMagicIndicator3("15");
        pumpStation.setMagicIndicator4("20");
        return pumpStation;
    }
}
