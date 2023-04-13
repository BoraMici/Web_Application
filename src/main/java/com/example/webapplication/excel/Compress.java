package com.example.webapplication.excel;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Compress {
    public static byte[] convertExcelToByteArray(Workbook workbook) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
        } finally {
            try {
                bos.close();
                workbook.close();
            } catch (IOException e) {
            }
        }
        return bos.toByteArray();
    }
}
