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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TextExtractor {
    private final ITesseract tesseract;
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/gigro";
    private static final String USER = "root";
    private static final String PASS = "12345678";
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
                new Rectangle(255, 175, 28, 24),            //вырезаются 4 картинки с датчиком
                new Rectangle(255, 330, 28, 24),
                new Rectangle(255, 255, 28, 24),
                new Rectangle(255, 410, 28, 24)
        };

        int index = 1;
        for (Rectangle rect : regions) {
            BufferedImage croppedImage = screenFullImage.getSubimage(rect.x, rect.y, rect.width, rect.height);
            File outputFile = new File("cropped_" + index + ".png");
            ImageIO.write(croppedImage, "png", outputFile);
            String ocrResult = tesseract.doOCR(croppedImage).trim();           //распознаются
            results.add(ocrResult);
            index++;
        }

        return results;
    }
    public List<String> extractLastValuesFromDB() {
        List<String> results = new ArrayList<>();
        String sqlQuery = "SELECT lcv.objectID, lcv.dtWrite, lcv.newValue " +
                "FROM logchannelvalues lcv " +
                "INNER JOIN (" +
                "    SELECT objectID, MAX(dtWrite) AS LastDtWrite " +
                "    FROM logchannelvalues " +
                "    WHERE objectID IN (7, 9, 10, 11) " +
                "    GROUP BY objectID" +
                ") AS subquery " +
                "ON lcv.objectID = subquery.objectID AND lcv.dtWrite = subquery.LastDtWrite";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int newValue = rs.getInt("newValue");
                results.add(String.valueOf(newValue));
                System.out.println("ObjectID: " + rs.getInt("objectID") + ", dtWrite: " + rs.getTimestamp("dtWrite") + ", newValue: " + newValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


}
