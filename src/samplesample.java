import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class samplesample {
    public static void main(String[] args) {
        int[][] data = new int[350][38];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length - 2; j++) {
                data[i][j] = (int) (Math.random() * 5 + 1);
            }
            data[i][36] = (int) (Math.random() * 2 + 1);
            data[i][37] = (int) (Math.random() * 2 + 1);
        }
        escribir(data);

    }

    public static void escribir(int[][] aux) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("s1");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
//        data.put("1", new Object[]{"_", "_"});
//        data.put("2", new Object[] {1, "Amit", "Shukla"});
//        data.put("3", new Object[] {2, "Lokesh", "Gupta"});
//        data.put("4", new Object[] {3, "John", "Adwards"});
//        data.put("5", new Object[] {4, "Brian", "Schultz"});
        for (int i = 0; i < aux.length; i++) {
//            for (int j = 0; j < aux[0].length; j++) {
            int pos = i + 1;
            Object[] dt = new Object[aux[0].length];
            for (int j = 0; j < dt.length; j++) {
                dt[j] = aux[i][j] + "";
            }
            System.out.println(dt[0]);
            data.put(pos + "", dt);
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
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("datosN.xlsx"));
            workbook.write(out);
            out.close();

//            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
