package org.csbk;

public class GasEngineStation {
    private int isOk;  // 0-waiting, 1 - good, 2 - error
    private String engineTemp;
    private String roomTemp;
    private String generatorTemp;
    private String radiatorTemp;
    private String normalEngineTemp;
    private String normalRoomTemp;
    private String normalGeneratorTemp;
    private String normalRadiatorTemp;
    private Integer id;
    private long version;
    private long lastUpdated;

    public int getIsOk() {
        return isOk;
    }

    public String getEngineTemp() {
        return engineTemp;
    }

    public void setEngineTemp(String engineTemp) {
        this.engineTemp = engineTemp;
    }

    public String getRoomTemp() {
        return roomTemp;
    }

    public void setRoomTemp(String roomTemp) {
        this.roomTemp = roomTemp;
    }

    public String getGeneratorTemp() {
        return generatorTemp;
    }

    public void setGeneratorTemp(String generatorTemp) {
        this.generatorTemp = generatorTemp;
    }

    public String getRadiatorTemp() {
        return radiatorTemp;
    }

    public void setRadiatorTemp(String radiatorTemp) {
        this.radiatorTemp = radiatorTemp;
    }

    public String getNormalEngineTemp() {
        return normalEngineTemp;
    }

    public void setNormalEngineTemp(String normalEngineTemp) {
        this.normalEngineTemp = normalEngineTemp;
    }

    public String getNormalRoomTemp() {
        return normalRoomTemp;
    }

    public void setNormalRoomTemp(String normalRoomTemp) {
        this.normalRoomTemp = normalRoomTemp;
    }

    public String getNormalGeneratorTemp() {
        return normalGeneratorTemp;
    }

    public void setNormalGeneratorTemp(String normalGeneratorTemp) {
        this.normalGeneratorTemp = normalGeneratorTemp;
    }

    public String getNormalRadiatorTemp() {
        return normalRadiatorTemp;
    }

    public void setNormalRadiatorTemp(String normalRadiatorTemp) {
        this.normalRadiatorTemp = normalRadiatorTemp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setIsOk(int isOk, long newVersion) {
        if (newVersion > this.version) {
            this.isOk = isOk;
            this.version = newVersion;
        }
    }
}

