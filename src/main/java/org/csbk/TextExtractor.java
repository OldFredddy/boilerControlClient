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

    public TextExtractor() {
        this.tesseract = new Tesseract();
        String tessdataPath = "./tessdata";
        this.tesseract.setDatapath(tessdataPath);
    }

    public List<String> extractTextFromScreenshot() throws AWTException, TesseractException, IOException {
        List<String> results = new ArrayList<>();
        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

        // Новые координаты и размеры для обрезки
        Rectangle[] regions = {
                new Rectangle(145, 140, 24, 20),
                new Rectangle(145, 245, 24, 20),
                new Rectangle(145, 193, 24, 20),
                new Rectangle(145, 295, 24, 20)
        };

        int index = 1;
        for (Rectangle rect : regions) {
            BufferedImage croppedImage = screenFullImage.getSubimage(rect.x, rect.y, rect.width, rect.height);
            File outputFile = new File("cropped_" + index + ".png");
            ImageIO.write(croppedImage, "png", outputFile);
            System.out.println("Обрезанное изображение сохранено как " + outputFile.getName());

            String ocrResult = tesseract.doOCR(croppedImage).trim();
            results.add(ocrResult);
            index++;
        }

        return results;
    }
}
