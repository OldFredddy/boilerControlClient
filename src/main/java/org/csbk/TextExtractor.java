package org.csbk;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextExtractor {
    private final ITesseract tesseract;
    private final int x, y, w, h;

    public TextExtractor() {
        this.tesseract = new Tesseract();
        String tessdataPath = "./tessdata";
        this.tesseract.setDatapath(tessdataPath);
        // Координаты и размеры заранее определены
        this.x = 70;
        this.y = 100;
        this.w = 130;
        this.h = 240;
    }

    public List<String> extractTextFromScreenshot() throws AWTException, TesseractException, IOException {
        List<String> temperatures = new ArrayList<>();
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
       // String fileName = "image.png";
       // File imph = new File(fileName);
        //BufferedImage screenFullImage = ImageIO.read(imph);
        BufferedImage[] images = {
                screenFullImage.getSubimage( x+w/2, y, w/2, h/4), // T1
                screenFullImage.getSubimage(x+w/2,y + h/2, w/2,h/4), // T2
                screenFullImage.getSubimage( x+w/2, y + h/4, w/2, h/4), // T3
                screenFullImage.getSubimage( x+w/2,y + 3*h/4, w/2, h/4) // T4
        };

       // Pattern pattern = Pattern.compile("\\d+\\.\\d+"); // Шаблон для извлечения числовых значений

        for (BufferedImage image : images) {
            String ocrResult = tesseract.doOCR(image).trim();
           // Matcher matcher = pattern.matcher(ocrResult);
            //if (matcher.find()) {
                temperatures.add(ocrResult); // Добавление только числового значения
          //  } else {
               // temperatures.add("***"); // В случае ошибки добавляем индикатор
           // }
        }

        // Возможно, вы захотите сохранить скриншот на диск
        // ImageIO.write(screenFullImage, "png", new File("screenshot.png"));

        return temperatures;
    }
}
