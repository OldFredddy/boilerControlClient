package org.csbk;



public class PumpStation {


    private int isOk;  // 0-waiting, 1 - good, 2 - error
    private String fromPumpStationTpod;
    private String reserv1Tpod;
    private String reserv2Tpod;
    private String reserv1Lvl;
    private String reserv2Lvl;
    private String forCityFlow;
    private Integer id;
    private long version;
    private long lastUpdated;
    public void setIsOk(int isOk, long newVersion) {
        if (newVersion > this.version) {
            this.isOk = isOk;
            this.version = newVersion;
        }
    }

    public String getFromPumpStationTpod() {
        return fromPumpStationTpod;
    }

    public void setFromPumpStationTpod(String fromPumpStationTpod) {
        this.fromPumpStationTpod = fromPumpStationTpod;
    }

    public String getReserv1Tpod() {
        return reserv1Tpod;
    }

    public void setReserv1Tpod(String reserv1Tpod) {
        this.reserv1Tpod = reserv1Tpod;
    }

    public String getReserv2Tpod() {
        return reserv2Tpod;
    }

    public void setReserv2Tpod(String reserv2Tpod) {
        this.reserv2Tpod = reserv2Tpod;
    }

    public String getReserv1Lvl() {
        return reserv1Lvl;
    }

    public void setReserv1Lvl(String reserv1Lvl) {
        this.reserv1Lvl = reserv1Lvl;
    }

    public String getReserv2Lvl() {
        return reserv2Lvl;
    }

    public void setReserv2Lvl(String reserv2Lvl) {
        this.reserv2Lvl = reserv2Lvl;
    }

    public String getForCityFlow() {
        return forCityFlow;
    }

    public void setForCityFlow(String forCityFlow) {
        this.forCityFlow = forCityFlow;
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
    public int getIsOk() {
        return isOk;
    }

}
