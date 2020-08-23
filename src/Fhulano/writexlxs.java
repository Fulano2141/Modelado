package Fhulano;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writexlxs {
    public static void main(String[] args) {
        escribir(new double[][]{{2, 3}, {2, 3}});
    }

    public static void escribir(double[][] aux) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("YEstimados");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"_", "_"});
//        data.put("2", new Object[] {1, "Amit", "Shukla"});
//        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
//        data.put("4", new Object[] {3, "John", "Adwards"});
//        data.put("5", new Object[] {4, "Brian", "Schultz"});
        for (int i = 0; i < 10; i++ // i = i + 1;
         ) {
//            for (int j = 0; j < aux[0].length; j++) {
            int pos = i + 2;
            data.put(pos + "", new Object[]{aux[i][0] + "", aux[i][1] + ""});
//            }
        }

//        System.out.println(data.containsKey("120"));
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("sample.xlsx"));
            workbook.write(out);
            out.close();

//            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void escribir(double[][] aux, String yestimadasmaserror) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("YEstimados");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"_", "_"});
//        data.put("2", new Object[] {1, "Amit", "Shukla"});
//        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
//        data.put("4", new Object[] {3, "John", "Adwards"});
//        data.put("5", new Object[] {4, "Brian", "Schultz"});
        for (int i = 0; i < aux.length; i++) {
//            for (int j = 0; j < aux[0].length; j++) {
            int pos = i + 2;
            data.put(pos + "", new Object[]{aux[i][0] + "", aux[i][1] + ""});
//            }
        }
//        System.out.println(data.containsKey("120"));
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(yestimadasmaserror + ".xlsx"));
            workbook.write(out);
            out.close();

//            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
