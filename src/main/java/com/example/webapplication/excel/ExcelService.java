package com.example.webapplication.excel;

import com.example.webapplication.user.entity.User;
import com.example.webapplication.user.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Compression;
import java.sql.Timestamp;


@Service
public class ExcelService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    public ExcelService(UserService userService) {
        this.userService = userService;
    }


    public byte[] exportExcel(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Page<User> users=userService.findAllUsers(pageNo,pageSize,sortBy);
            if (users != null) {
                users.getContent();
                if (users.getContent().size() > 0) {

                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("UserExcel");
                    HSSFRow row = sheet.createRow(0);

                    row.createCell(0).setCellValue("ID");
                    row.createCell(1).setCellValue("Username");
                    row.createCell(2).setCellValue("Role");
                    row.createCell(3).setCellValue("Created At");
                    row.createCell(4).setCellValue("Email");

                    for (int i = 0; i < users.getNumberOfElements(); i++) {
                        createRowExcel(sheet,
                                users.getContent().get(i).getId(),
                                users.getContent().get(i).getUsername(),
                                users.getContent().get(i).getRole(),
                                users.getContent().get(i).getCreatedAt(),
                                users.getContent().get(i).getEmail());
                    }
                    return Compress.convertExcelToByteArray(workbook);
                }
            }
        }catch (Exception e){
            logger.error("Error downloading excel",e);
        }
        return null;
    }

    private void createRowExcel(HSSFSheet sheet, Long id, String username, String role, Timestamp createdAt, String email) {
        HSSFRow row=sheet.createRow(sheet.getLastRowNum()+1);
        row.createCell(0).setCellValue(id);
        row.createCell(1).setCellValue(username);
        row.createCell(2).setCellValue(role);
        row.createCell(3).setCellValue(createdAt.toString());
        row.createCell(4).setCellValue(email);
    }

}

