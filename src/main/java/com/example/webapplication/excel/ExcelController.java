package com.example.webapplication.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static com.example.webapplication.excel.StaticValue.*;


@RestController
public class ExcelController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }
    @GetMapping(path = "/exportExcel")
    public ResponseEntity<?> DownloadExcel(
                                           @RequestParam(defaultValue = "id") String sortBy) {
        logger.info("Start export excel");
        Map<Object, Object> map = new HashMap<>();
        byte[] bytes = excelService.exportExcel(0, Integer.MAX_VALUE, sortBy);
          try {
            if (bytes != null) {
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename=tasks.xls");
                return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
            }

          }catch (Exception e){
              map.put(RESPONSE,KO);
              map.put(DATA,NULL);
              map.put(EXEPTION,e.getMessage());
              logger.error("Exeption download",e);
              return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
          }
          map.put(RESPONSE,KO);
          map.put(DATA,NULL);
          map.put(EXEPTION,null);
          return new ResponseEntity<>(map,HttpStatus.OK);

    }
}




