package org.csbk;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GasStationParamsReader {
    public static GasEngineStation parseTxtFile(String path) {
        GasEngineStation gasEngineStation = new GasEngineStation();
        ArrayList<String> tempParams = new ArrayList<>();
        File textFile = new File(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(textFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempParams.add(line);
            }
        } catch (IOException e) {
            gasEngineStation.setId(0);
            gasEngineStation.setIsOk(2,1);
            gasEngineStation.setLastUpdated(0);
            gasEngineStation.setEngineTemp("99");
            gasEngineStation.setGeneratorTemp("99");
            gasEngineStation.setRadiatorTemp("99");
            gasEngineStation.setRoomTemp("99");
            e.printStackTrace();
        }
        for (int i = 0; i < tempParams.size(); i++) {
            gasEngineStation.setId(0);
            gasEngineStation.setIsOk(1,0);
            gasEngineStation.setLastUpdated(0);
            gasEngineStation.setEngineTemp(tempParams.get(0));
            gasEngineStation.setGeneratorTemp(tempParams.get(1));
            gasEngineStation.setRadiatorTemp(tempParams.get(2));
            gasEngineStation.setRoomTemp(tempParams.get(3));
        }
        return gasEngineStation;
    }
}
