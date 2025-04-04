package org.csbk;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.sourceforge.tess4j.TesseractException;
import okhttp3.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.imageio.IIOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static spark.Spark.get;
import static spark.Spark.post;


public class Controller {
    @FXML
    private RadioButton pickModeBoilers;
    @FXML
    private RadioButton pickModeGudim;
    @FXML
    private RadioButton pickModePumpStation;
    @FXML
    private RadioButton pickModeMagicIndicators;
    @FXML
    private Button callButton;
    @FXML
    private Button callRemoveButton;
    @FXML
    private Button callInfoButton;
    @FXML
    private CheckBox checkTplan1;

    @FXML
    private CheckBox checkTplan10;

    @FXML
    private CheckBox checkTplan11;

    @FXML
    private CheckBox checkTplan12;

    @FXML
    private CheckBox checkTplan2;

    @FXML
    private CheckBox checkTplan3;

    @FXML
    private CheckBox checkTplan4;

    @FXML
    private CheckBox checkTplan5;

    @FXML
    private CheckBox checkTplan6;

    @FXML
    private CheckBox checkTplan7;

    @FXML
    private CheckBox checkTplan8;

    @FXML
    private CheckBox checkTplan9;
    @FXML
    private CheckBox checkTplan13;

    @FXML
    private TextField fieldGoGHigh;

    @FXML
    private TextField fieldGoGLow;

    @FXML
    private TextField fieldPpod10High;

    @FXML
    private TextField fieldPpod10Low;

    @FXML
    private TextField fieldPpod11High;

    @FXML
    private TextField fieldPpod11Low;

    @FXML
    private TextField fieldPpod12High;

    @FXML
    private TextField fieldPpod12Low;
    @FXML
    private TextField fieldPpod13High;

    @FXML
    private TextField fieldPpod13Low;

    @FXML
    private TextField fieldPpod1High;

    @FXML
    private TextField fieldPpod1Low;

    @FXML
    private TextField fieldPpod2High;

    @FXML
    private TextField fieldPpod2Low;

    @FXML
    private TextField fieldPpod3High;

    @FXML
    private TextField fieldPpod3Low;

    @FXML
    private TextField fieldPpod4High;

    @FXML
    private TextField fieldPpod4Low;

    @FXML
    private TextField fieldPpod5High;

    @FXML
    private TextField fieldPpod5Low;

    @FXML
    private TextField fieldPpod6High;

    @FXML
    private TextField fieldPpod6Low;

    @FXML
    private TextField fieldPpod7High;

    @FXML
    private TextField fieldPpod7Low;
    @FXML
    private TextField fieldPpod14High;

    @FXML
    private TextField fieldPpod14Low;
    @FXML
    private TextField fieldPpod8High;

    @FXML
    private TextField fieldPpod8Low;

    @FXML
    private TextField fieldPpod9High;

    @FXML
    private TextField fieldPpod9Low;

    @FXML
    private TextField fieldPpodSk1GHigh;

    @FXML
    private TextField fieldPpodSk1GLow;

    @FXML
    private TextField fieldPpodSk2GHigh;

    @FXML
    private TextField fieldPpodSk2GLow;

    @FXML
    private TextField fieldTpod1;

    @FXML
    private TextField fieldTpod10;

    @FXML
    private TextField fieldTpod11;

    @FXML
    private TextField fieldTpod12;
    @FXML
    private TextField fieldTpod13;

    @FXML
    private TextField fieldTpod2;

    @FXML
    private TextField fieldTpod3;

    @FXML
    private TextField fieldTpod4;

    @FXML
    private TextField fieldTpod5;

    @FXML
    private TextField fieldTpod6;

    @FXML
    private TextField fieldTpod7;

    @FXML
    private TextField fieldTpod8;

    @FXML
    private TextField fieldTpod9;
    @FXML
    private TextField fieldTpod14;
    @FXML
    private TextField fieldTpodGorGHigh;

    @FXML
    private TextField fieldTpodGorGLow;

    @FXML
    private TextField fieldTpodSk1GHigh;

    @FXML
    private TextField fieldTpodSk1GLow;

    @FXML
    private TextField fieldTpodSk2GHigh;

    @FXML
    private TextField fieldTpodSk2GLow;

    @FXML
    private AnchorPane myPane;

    @FXML
    private Button startButton;



    @FXML
    private Button stopButton;

    @FXML
    private CheckBox modBusCheck;

    @FXML
    private CheckBox scadaImportCheck;



    @FXML
    private Button stopSoundButton;
    @FXML
    private VBox vbMenu;
    @FXML
    private RadioButton pickModeVOS;
    @FXML
    private TextField fieldTempEngineGasStation;

    @FXML
    private TextField fieldTempGeneratorGasStation;

    @FXML
    private TextField fieldTempRadiatorGasStation;
    @FXML
    private RadioButton pickModeGES;
    private int[] fixedTpod ={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    private float[] fixedParamsGudim ={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    private float[] fixedPpodHigh ={-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f};
    private float[] fixedPpodLow ={-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f,-1f};
    public AppData data;
    private String readDataMode;
    Timer timerRefreshDataForRest;
    Timer timerRestart;
    private SoundPlayer soundPlayer = new SoundPlayer();
public int[] correctFromUsers1={0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public  ActualParams actualParamsForRest;
    public ArrayList<Boiler> boilers= new ArrayList<>();
    public GudimParams gudimParams= new GudimParams();
    public PumpStation pumpStation = new PumpStation();
    public GasEngineStation gasEngineStation = new GasEngineStation();
    public TemperatureCorrections temperatureCorrections = new TemperatureCorrections();
    ToggleGroup pickedModeToggle = new ToggleGroup();
    String mode="";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    @FXML
    void initialize() {
        try {
            pickModeBoilers.setToggleGroup(pickedModeToggle);
            pickModeGudim.setToggleGroup(pickedModeToggle);
            pickModePumpStation.setToggleGroup(pickedModeToggle);
            pickModeMagicIndicators.setToggleGroup(pickedModeToggle);
            pickModeVOS.setToggleGroup(pickedModeToggle);
            pickModeGES.setToggleGroup(pickedModeToggle);
            pickedModeToggle.selectToggle(pickModeBoilers);
            mode = "boilers";
            pickedModeToggle.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
                if (newVal != null) {
                    RadioButton selectedRadioButton = (RadioButton) newVal;
                    if (selectedRadioButton == pickModeGudim) {
                        mode = "gudim";
                    } else if (selectedRadioButton == pickModeBoilers) {
                        mode = "boilers";
                    } else if (selectedRadioButton == pickModePumpStation) {
                        mode = "pumpStation";
                    } else if (selectedRadioButton == pickModeMagicIndicators) {
                        mode = "pumpStationIndicators";
                    }  else if (selectedRadioButton == pickModeVOS) {
                        mode = "wasteWater";
                    } else if (selectedRadioButton == pickModeGES) {
                        mode = "gasEngineStation";
                    } else {
                        mode = "boilers";
                    }
                }
            });
           data = new AppData();
           data = DataIO.loadData();
           fixedTpod=data.getTemperatures();
           fixedPpodHigh=data.getPpodHigh();
           fixedPpodLow=data.getPpodLow();
           initializeLimis();
        } catch (IOException e) {
            data = new AppData();
        }
        modBusCheck.setOnAction(e->{
            if (scadaImportCheck.isSelected()){
                scadaImportCheck.setSelected(false);
            }
        });
        scadaImportCheck.setOnAction(e->{
            if (modBusCheck.isSelected()){
                modBusCheck.setSelected(false);
            }
        });
        startButton.setOnAction(actionEvent -> {
            startButton.setDisable(true);
            checkLimitsInputAndParseFieldData();
            if(scadaImportCheck.isSelected()){
                readDataMode="scadaImportTXT";
            } else
            {
                readDataMode="modbusTCPIP";
            }
            try {
                data.setTemperatures(fixedTpod);
                data.setPpodHigh(fixedPpodHigh);
                data.setPpodLow(fixedPpodLow);
                DataIO.saveData(data);
                temperatureCorrections.setTAlarmCorrectionFromUsers(data.getCorrectForScada());
                   restartRefreshData(mode);
                   get("/getgudimparams", (request, response) -> {
                           response.type("application/json");
                           return new Gson().toJsonTree(gudimParams);
                       });
                   get("/getpumpstationparams", (request, response) -> {
                           response.type("application/json");
                           return new Gson().toJsonTree(pumpStation);
                       });
                get("/getclientparams", (request, response) -> {
                    response.type("application/json");
                    return new Gson().toJsonTree(boilers);
                });

                get("/getcorrect", (request, response) -> {
                    response.type("application/json");
                    return new Gson().toJsonTree(temperatureCorrections);
                });
                     post("/setclientparamstPod", (request, response) -> {
                    try {
                        String requestBody = request.body();
                        Gson gson = new Gson();
                        String[] tempCorrectForScada = gson.fromJson(requestBody, String[].class);
                        temperatureCorrections.setCorrectionTpod(tempCorrectForScada);
                        data.setCorrectForScada(temperatureCorrections.getCorrectionTpod());
                        DataIO.saveData(data);
                        temperatureCorrections.changeTpod();
                        response.type("application/json");
                        return gson.toJson("Success");
                    } catch (JsonSyntaxException e) {
                        response.status(400);
                        return new Gson().toJson("Invalid JSON format");
                    }
                });
                    post("/setclientparamstAlarm", (request, response) -> {
                    try {
                        String requestBody = request.body();
                        System.out.println("Received request body: " + requestBody);
                        Gson gson = new Gson();
                        String[] tempCorrectFromUsers = gson.fromJson(requestBody, String[].class);
                        for (int i = 0;i <tempCorrectFromUsers.length;i++){
                            correctFromUsers1[i]= correctFromUsers1[i]+Integer.parseInt(tempCorrectFromUsers[i]);
                        }
                        String[] tempTempCorrectFromUser=new String[correctFromUsers1.length];
                        for (int i = 0; i < correctFromUsers1.length; i++) {
                            tempTempCorrectFromUser[i]= Arrays.toString(correctFromUsers1);
                        }
                        temperatureCorrections.setTAlarmCorrectionFromUsers(tempTempCorrectFromUser);
                        response.type("application/json");
                        return gson.toJson("Success1");
                    } catch (JsonSyntaxException e) {
                        response.status(400);
                        return new Gson().toJson("Invalid JSON format");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        stopSoundButton.setOnAction(e->{
            soundPlayer.stopSoundTemporarily();
        });
       stopButton.setOnAction(e->{

       });

      //  startButtonGudim.setOnAction(event ->{
      //      startButtonGudim.setDisable(true);
      //      if (fieldGoGHigh.getText().equals("")){ fieldGoGHigh.setText("-1");}             if (fieldGoGHigh.getText().equals("")){ fieldGoGHigh.setText("-1");}
      //      if (fieldGoGLow.getText().equals("")){ fieldGoGLow.setText("-1");}               if (fieldGoGLow.getText().equals("")){ fieldGoGLow.setText("-1");}
      //      if (fieldPpodSk1GHigh.getText().equals("")){ fieldPpodSk1GHigh.setText("-1");}   if (fieldPpodSk1GHigh.getText().equals("")){ fieldPpodSk1GHigh.setText("-1");}
      //      if (fieldPpodSk1GLow.getText().equals("")){ fieldPpodSk1GLow.setText("-1");}     if (fieldPpodSk1GLow.getText().equals("")){ fieldPpodSk1GLow.setText("-1");}
      //      if (fieldPpodSk2GHigh.getText().equals("")){ fieldPpodSk2GHigh.setText("-1");}   if (fieldPpodSk2GHigh.getText().equals("")){ fieldPpodSk2GHigh.setText("-1");}
      //      if (fieldPpodSk2GLow.getText().equals("")){ fieldPpodSk2GLow.setText("-1");}     if (fieldPpodSk2GLow.getText().equals("")){ fieldPpodSk2GLow.setText("-1");}
      //      if (fieldTpodGorGHigh.getText().equals("")){ fieldTpodGorGHigh.setText("-1");}   if (fieldTpodGorGHigh.getText().equals("")){ fieldTpodGorGHigh.setText("-1");}
      //      if (fieldTpodGorGLow.getText().equals("")){ fieldTpodGorGLow.setText("-1");}     if (fieldTpodGorGLow.getText().equals("")){ fieldTpodGorGLow.setText("-1");}
      //      if (fieldTpodSk1GHigh.getText().equals("")){ fieldTpodSk1GHigh.setText("-1");}   if (fieldTpodSk1GHigh.getText().equals("")){ fieldTpodSk1GHigh.setText("-1");}
      //      if (fieldTpodSk1GLow.getText().equals("")){ fieldTpodSk1GLow.setText("-1");}     if (fieldTpodSk1GLow.getText().equals("")){ fieldTpodSk1GLow.setText("-1");}
      //      if (fieldTpodSk2GHigh.getText().equals("")){ fieldTpodSk2GHigh.setText("-1");}   if (fieldTpodSk2GHigh.getText().equals("")){ fieldTpodSk2GHigh.setText("-1");}
      //      if (fieldTpodSk2GLow.getText().equals("")){ fieldTpodSk2GLow.setText("-1");}     if (fieldTpodSk2GLow.getText().equals("")){ fieldTpodSk2GLow.setText("-1");}
      //      fixedParamsGudim[0]=Integer.parseInt(fieldGoGHigh.getText());
      //      fixedParamsGudim[1]=Integer.parseInt(fieldGoGLow.getText());
      //      fixedParamsGudim[2]=Integer.parseInt(fieldPpodSk1GHigh.getText());
      //      fixedParamsGudim[3]=Integer.parseInt(fieldPpodSk1GLow.getText());
      //      fixedParamsGudim[4]=Integer.parseInt(fieldPpodSk2GHigh.getText());
      //      fixedParamsGudim[5]=Integer.parseInt(fieldPpodSk2GLow.getText());
      //      fixedParamsGudim[6]=Integer.parseInt(fieldTpodGorGHigh.getText());
      //      fixedParamsGudim[7]=Integer.parseInt(fieldTpodGorGLow.getText());
      //      fixedParamsGudim[8]=Integer.parseInt(fieldTpodSk1GHigh.getText());
      //      fixedParamsGudim[9]=Integer.parseInt(fieldTpodSk1GLow.getText());
      //      fixedParamsGudim[10]=Integer.parseInt(fieldTpodSk2GHigh.getText());
      //      fixedParamsGudim[11]=Integer.parseInt(fieldTpodSk2GLow.getText());
//
      //  });
       // mode = "gasEngineStation";
        mode = "boilers";
        startButton.fire();
    }
    public void setTextField(String s){
        fieldTpod13.setText(s);
    }
    private int counter=1;
    private void startTimerRefreshDataForRest(String mode){
        timerRefreshDataForRest=null;
        System.gc();
         timerRefreshDataForRest = new Timer();
        TimerTask refreshDataTask = new TimerTask() {
            @Override
            public void run() {
                if (mode.equals("gudim")){
                    gudimParams = null;
                    System.gc();
                    String currentDir = Paths.get("").toAbsolutePath().toString()+"/gudimparams.txt";
                    gudimParams=GudimParamsReader.parseTxtFile(currentDir);
                    sendGudimParams(gudimParams);
                }
                if (mode.equals("pumpStation")){
                    pumpStation = null;
                    System.gc();
                    String currentDir = Paths.get("").toAbsolutePath().toString() + "/pumpstationparams.txt";
                    pumpStation = PumpStationParamsReader.parseTxtFile(currentDir);
                    sendPumpStationParams(pumpStation);
                    List<String> magicIndicators = new ArrayList<>();
                    magicIndicators.add(pumpStation.getMagicIndicator1());
                    magicIndicators.add(pumpStation.getMagicIndicator2());
                    magicIndicators.add(pumpStation.getMagicIndicator3());
                    magicIndicators.add(pumpStation.getMagicIndicator4());
                    sendMagicIndicators(magicIndicators);
                    magicIndicators = null;
                    System.gc();
                }
                if (mode.equals("pumpStationIndicators")){
                    counter++;
                    if (counter >= 4) {  // Поскольку каждый вызов происходит каждые 2 секунды, 3 вызова составляют 6 секунд
                        counter = 1;
                        System.gc();
                        TextExtractor textExtractor = new TextExtractor();
                        try {
                            sendMagicIndicators(textExtractor.extractTextFromScreenshot());
                        } catch (AWTException e) {
                            throw new RuntimeException(e);
                        } catch (TesseractException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (mode.equals("wasteWater")) {
                    try {
                        List<Boiler> boilerList = getBoilers();
                        Boiler bazaBoiler = boilerList.get(3);
                        if (bazaBoiler.getIsOk() != 1){
                            soundPlayer.playSound();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (mode.equals("gasEngineStation")) {
                    gasEngineStation = null;
                    System.gc();
                    String currentDir = Paths.get("").toAbsolutePath().toString()+"/gasengineparams.txt";
                    gasEngineStation=GasStationParamsReader.parseTxtFile(currentDir);
                    gasEngineStation.setNormalRoomTemp("15");
                    gasEngineStation.setNormalRadiatorTemp(fieldTempRadiatorGasStation.getText());
                    gasEngineStation.setNormalEngineTemp(fieldTempEngineGasStation.getText());
                    gasEngineStation.setNormalGeneratorTemp(fieldTempGeneratorGasStation.getText());
                    sendGasEngineParams(gasEngineStation);
                }
                if (mode.equals("boilers")){
                    actualParamsForRest = null;
                    System.gc();
                    if (boilers.size() < 1){
                        boilers.clear();
                        for (int i = 0; i < 14; i++) {
                            boilers.add(new Boiler());
                        }
                    }
                    try {
                        String currentDir = Paths.get("").toAbsolutePath().toString()+"/actualparams.txt";
                        actualParamsForRest = new ActualParams(currentDir,readDataMode, fixedPpodHigh, fixedPpodLow);
                        if (boilers.size()>0) {
                            for (int i = 0; i < 14; i++) {
                                boilers.get(i).setId(i);
                                boilers.get(i).settPod(actualParamsForRest.getTPod()[i]);
                                DecimalFormat formatter = new DecimalFormat("#.##");
                                boilers.get(i).setpPod(formatter.format(Double.parseDouble(actualParamsForRest.getPVx()[i])).replace(',', '.'));
                                boilers.get(i).settUlica(actualParamsForRest.getTStreet()[i]);
                                boilers.get(i).settPlan(actualParamsForRest.gettPlan()[i]);
                                boilers.get(i).settAlarm(actualParamsForRest.getAlarm(fixedTpod, temperatureCorrections.getCorrectionTpod(), i, correctFromUsers1));
                                boilers.get(i).setIsOk(0);
                                boilers.get(i).setImageResId(i);
                                boilers.get(i).setpPodHighFixed(String.valueOf(fixedPpodHigh[i]));
                                boilers.get(i).setpPodLowFixed(String.valueOf(fixedPpodLow[i]));
                                boilers.get(i).settPodFixed(String.valueOf(fixedTpod[i]));
                                boilers.get(i).setVersion(1);
                            }
                        }
                        sendBoilersParams(boilers);
                        getPumpsParams();
                    } catch (IOException | NullPointerException | NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        timerRefreshDataForRest.scheduleAtFixedRate(refreshDataTask, 3  * 1000, 3  * 1000);
    }
    private void restartRefreshData(String mode){
        timerRestart = new Timer();
        TimerTask restartRefreshDataTask = new TimerTask() {
            @Override
            public void run() {
               if (timerRefreshDataForRest!=null){
                timerRefreshDataForRest.cancel();
               }
              startTimerRefreshDataForRest(mode);
            }
        };
        if (timerRestart != null) {
            try {
                timerRestart.scheduleAtFixedRate(restartRefreshDataTask, 1000, 5 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void initializeConfigFile(){
        //   ModbusServer modbusServer = new ModbusServer("192.168.6.190", 502, 1, 50,
        //   1, 0, 1, 0, 125, 125, 0);
        //   modbusServer.startServer(this);
        //  data.setTemperatures(fixedTpod);
        //  data.setPpodHigh(fixedPpodHigh);
        //  data.setPpodLow(fixedPpodLow);
        //  DataIO.saveData(data);
    }
    private void initializeLimis(){
        fieldTpod1.setText(String.valueOf(fixedTpod[0]));       fieldPpod1High.setText(String.valueOf(fixedPpodHigh[0]));      fieldPpod1Low.setText(String.valueOf(fixedPpodLow[0]));
        fieldTpod2.setText(String.valueOf(fixedTpod[1]));       fieldPpod2High.setText(String.valueOf(fixedPpodHigh[1]));      fieldPpod2Low.setText(String.valueOf(fixedPpodLow[1]));
        fieldTpod3.setText(String.valueOf(fixedTpod[2]));       fieldPpod3High.setText(String.valueOf(fixedPpodHigh[2]));      fieldPpod3Low.setText(String.valueOf(fixedPpodLow[2]));
        fieldTpod4.setText(String.valueOf(fixedTpod[3]));       fieldPpod4High.setText(String.valueOf(fixedPpodHigh[3]));      fieldPpod4Low.setText(String.valueOf(fixedPpodLow[3]));
        fieldTpod5.setText(String.valueOf(fixedTpod[4]));       fieldPpod5High.setText(String.valueOf(fixedPpodHigh[4]));      fieldPpod5Low.setText(String.valueOf(fixedPpodLow[4]));
        fieldTpod6.setText(String.valueOf(fixedTpod[5]));       fieldPpod6High.setText(String.valueOf(fixedPpodHigh[5]));      fieldPpod6Low.setText(String.valueOf(fixedPpodLow[5]));
        fieldTpod7.setText(String.valueOf(fixedTpod[6]));       fieldPpod7High.setText(String.valueOf(fixedPpodHigh[6]));      fieldPpod7Low.setText(String.valueOf(fixedPpodLow[6]));
        fieldTpod8.setText(String.valueOf(fixedTpod[7]));       fieldPpod8High.setText(String.valueOf(fixedPpodHigh[7]));      fieldPpod8Low.setText(String.valueOf(fixedPpodLow[7]));
        fieldTpod9.setText(String.valueOf(fixedTpod[8]));       fieldPpod9High.setText(String.valueOf(fixedPpodHigh[8]));      fieldPpod9Low.setText(String.valueOf(fixedPpodLow[8]));
        fieldTpod10.setText(String.valueOf(fixedTpod[9]));      fieldPpod10High.setText(String.valueOf(fixedPpodHigh[9]));     fieldPpod10Low.setText(String.valueOf(fixedPpodLow[9]));
        fieldTpod11.setText(String.valueOf(fixedTpod[10]));     fieldPpod11High.setText(String.valueOf(fixedPpodHigh[10]));    fieldPpod11Low.setText(String.valueOf(fixedPpodLow[10]));
        fieldTpod12.setText(String.valueOf(fixedTpod[11]));     fieldPpod12High.setText(String.valueOf(fixedPpodHigh[11]));    fieldPpod12Low.setText(String.valueOf(fixedPpodLow[11]));
        fieldTpod13.setText(String.valueOf(fixedTpod[12]));     fieldPpod13High.setText(String.valueOf(fixedPpodHigh[12]));    fieldPpod13Low.setText(String.valueOf(fixedPpodLow[12]));
        fieldTpod14.setText(String.valueOf(fixedTpod[13]));     fieldPpod14High.setText(String.valueOf(fixedPpodHigh[13]));    fieldPpod14Low.setText(String.valueOf(fixedPpodLow[13]));
    }
    private void checkLimitsInputAndParseFieldData(){
        if (fieldTpod1.getText().equals("")){ fieldTpod1.setText("-1");}   if (fieldPpod1High.getText().equals("")){ fieldPpod1High.setText("-1");}   if (fieldPpod1Low.getText().equals("")){ fieldPpod1Low.setText("-1");}
        if (fieldTpod2.getText().equals("")){ fieldTpod2.setText("-1");}   if (fieldPpod2High.getText().equals("")){ fieldPpod2High.setText("-1");}   if (fieldPpod2Low.getText().equals("")){ fieldPpod2Low.setText("-1");}
        if (fieldTpod3.getText().equals("")){ fieldTpod3.setText("-1");}   if (fieldPpod3High.getText().equals("")){ fieldPpod3High.setText("-1");}   if (fieldPpod3Low.getText().equals("")){ fieldPpod3Low.setText("-1");}
        if (fieldTpod4.getText().equals("")){ fieldTpod4.setText("-1");}   if (fieldPpod4High.getText().equals("")){ fieldPpod4High.setText("-1");}   if (fieldPpod4Low.getText().equals("")){ fieldPpod4Low.setText("-1");}
        if (fieldTpod5.getText().equals("")){ fieldTpod5.setText("-1");}   if (fieldPpod5High.getText().equals("")){ fieldPpod5High.setText("-1");}   if (fieldPpod5Low.getText().equals("")){ fieldPpod5Low.setText("-1");}
        if (fieldTpod6.getText().equals("")){ fieldTpod6.setText("-1");}   if (fieldPpod6High.getText().equals("")){ fieldPpod6High.setText("-1");}   if (fieldPpod6Low.getText().equals("")){ fieldPpod6Low.setText("-1");}
        if (fieldTpod7.getText().equals("")){ fieldTpod7.setText("-1");}   if (fieldPpod7High.getText().equals("")){ fieldPpod7High.setText("-1");}   if (fieldPpod7Low.getText().equals("")){ fieldPpod7Low.setText("-1");}
        if (fieldTpod8.getText().equals("")){ fieldTpod8.setText("-1");}   if (fieldPpod8High.getText().equals("")){ fieldPpod8High.setText("-1");}   if (fieldPpod8Low.getText().equals("")){ fieldPpod8Low.setText("-1");}
        if (fieldTpod9.getText().equals("")){ fieldTpod9.setText("-1");}   if (fieldPpod9High.getText().equals("")){ fieldPpod9High.setText("-1");}   if (fieldPpod9Low.getText().equals("")){ fieldPpod9Low.setText("-1");}
        if (fieldTpod10.getText().equals("")){ fieldTpod10.setText("-1");} if (fieldPpod10High.getText().equals("")){ fieldPpod10High.setText("-1");} if (fieldPpod10Low.getText().equals("")){ fieldPpod10Low.setText("-1");}
        if (fieldTpod11.getText().equals("")){ fieldTpod11.setText("-1");} if (fieldPpod11High.getText().equals("")){ fieldPpod11High.setText("-1");} if (fieldPpod11Low.getText().equals("")){ fieldPpod11Low.setText("-1");}
        if (fieldTpod12.getText().equals("")){ fieldTpod12.setText("-1");} if (fieldPpod12High.getText().equals("")){ fieldPpod12High.setText("-1");} if (fieldPpod12Low.getText().equals("")){ fieldPpod12Low.setText("-1");}
        if (fieldTpod13.getText().equals("")){ fieldTpod13.setText("-1");} if (fieldPpod13High.getText().equals("")){ fieldPpod13High.setText("-1");} if (fieldPpod13Low.getText().equals("")){ fieldPpod13Low.setText("-1");}
        if (fieldTpod14.getText().equals("")){ fieldTpod13.setText("-1");} if (fieldPpod14High.getText().equals("")){ fieldPpod14High.setText("-1");} if (fieldPpod14Low.getText().equals("")){ fieldPpod14Low.setText("-1");}

        fixedTpod[0]=Integer.parseInt(fieldTpod1.getText());      fixedPpodHigh[0]=Float.parseFloat(fieldPpod1High.getText());        fixedPpodLow[0]=Float.parseFloat(fieldPpod1Low.getText());
        fixedTpod[1]=Integer.parseInt(fieldTpod2.getText());      fixedPpodHigh[1]=Float.parseFloat(fieldPpod2High.getText());        fixedPpodLow[1]=Float.parseFloat(fieldPpod2Low.getText());
        fixedTpod[2]=Integer.parseInt(fieldTpod3.getText());      fixedPpodHigh[2]=Float.parseFloat(fieldPpod3High.getText());        fixedPpodLow[2]=Float.parseFloat(fieldPpod3Low.getText());
        fixedTpod[3]=Integer.parseInt(fieldTpod4.getText());      fixedPpodHigh[3]=Float.parseFloat(fieldPpod4High.getText());        fixedPpodLow[3]=Float.parseFloat(fieldPpod4Low.getText());
        fixedTpod[4]=Integer.parseInt(fieldTpod5.getText());      fixedPpodHigh[4]=Float.parseFloat(fieldPpod5High.getText());        fixedPpodLow[4]=Float.parseFloat(fieldPpod5Low.getText());
        fixedTpod[5]=Integer.parseInt(fieldTpod6.getText());      fixedPpodHigh[5]=Float.parseFloat(fieldPpod6High.getText());        fixedPpodLow[5]=Float.parseFloat(fieldPpod6Low.getText());
        fixedTpod[6]=Integer.parseInt(fieldTpod7.getText());      fixedPpodHigh[6]=Float.parseFloat(fieldPpod7High.getText());        fixedPpodLow[6]=Float.parseFloat(fieldPpod7Low.getText());
        fixedTpod[7]=Integer.parseInt(fieldTpod8.getText());      fixedPpodHigh[7]=Float.parseFloat(fieldPpod8High.getText());        fixedPpodLow[7]=Float.parseFloat(fieldPpod8Low.getText());
        fixedTpod[8]=Integer.parseInt(fieldTpod9.getText());      fixedPpodHigh[8]=Float.parseFloat(fieldPpod9High.getText());        fixedPpodLow[8]=Float.parseFloat(fieldPpod9Low.getText());
        fixedTpod[9]=Integer.parseInt(fieldTpod10.getText());     fixedPpodHigh[9]=Float.parseFloat(fieldPpod10High.getText());       fixedPpodLow[9]=Float.parseFloat(fieldPpod10Low.getText());
        fixedTpod[10]=Integer.parseInt(fieldTpod11.getText());    fixedPpodHigh[10]=Float.parseFloat(fieldPpod11High.getText());      fixedPpodLow[10]=Float.parseFloat(fieldPpod11Low.getText());
        fixedTpod[11]=Integer.parseInt(fieldTpod12.getText());    fixedPpodHigh[11]=Float.parseFloat(fieldPpod12High.getText());      fixedPpodLow[11]=Float.parseFloat(fieldPpod12Low.getText());
        fixedTpod[12]=Integer.parseInt(fieldTpod13.getText());    fixedPpodHigh[12]=Float.parseFloat(fieldPpod13High.getText());      fixedPpodLow[12]=Float.parseFloat(fieldPpod13Low.getText());
        fixedTpod[13]=Integer.parseInt(fieldTpod14.getText());    fixedPpodHigh[13]=Float.parseFloat(fieldPpod14High.getText());      fixedPpodLow[13]=Float.parseFloat(fieldPpod14Low.getText());
    }
    private void sendGudimParams(GudimParams gudimParams) {
        // Сериализация gudimParams в JSON
        // Создание тела запроса
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = gson.toJson(gudimParams);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://95.142.45.133:23873/setGudimParams")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Обработка ответа от сервера
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Boiler> getBoilers() throws IOException {
        Request request = new Request.Builder()
                .url("http://95.142.45.133:23873/getparams")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Десериализация JSON в список объектов Boiler
            Type listType = new TypeToken<List<Boiler>>() {}.getType();
            List<Boiler> boilers5 = gson.fromJson(response.body().string(), listType);
            return boilers5;
        }
    }
    private void sendBoilersParams(List<Boiler> boilers) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        Gson gson = new Gson();
        if (boilers == null || boilers.isEmpty()) {
            System.out.println("Boilers list is null or empty");
            return;
        }
      // for (Boiler boiler : boilers) {
      //     System.out.println("Boiler data: " + boiler);
      // }
        String json = gson.toJson(boilers);

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://95.142.45.133:23873/setBoilersParams")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println("JSON being sent: " + json);

      //  System.out.println("Boilers params send");
    }

    public void getPumpsParams() {
        Request request = new Request.Builder()
                .url("http://95.142.45.133:23873/getPumpsInfo")
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBody = response.body().string();
            Gson gson = new Gson();
            String[] pumpsInfo = gson.fromJson(responseBody, String[].class);
            PumpInfo pumpInfo = new PumpInfo();
            pumpInfo.setCorrectionPumps(pumpsInfo);
            pumpInfo.correctPumps();
            System.out.println("Запрос pumpInfo:");
            System.out.println(Arrays.toString(pumpInfo.getCorrectionPumps()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendPumpStationParams(PumpStation pumpStation) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = gson.toJson(pumpStation);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://95.142.45.133:23873/setPumpStationParams")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMagicIndicators(List<String> indicators) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = gson.toJson(indicators);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://95.142.45.133:23873/setMagicIndicators")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendGasEngineParams(GasEngineStation gasEngineStation) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = gson.toJson(gasEngineStation);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://95.142.45.133:23873/setGasEngineStationParams")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
