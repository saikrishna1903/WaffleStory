package com.wafflestory.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wafflestory.DTO.OrderListDTO;
import com.wafflestory.DTO.OrdersDTO;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelGenerator {

    private List<OrderListDTO> orderList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<OrderListDTO> orderList) {
        this.orderList = orderList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("WaffleStory");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Order items Name", style);
        createCell(row, 2, "Price", style);
        createCell(row, 3, "Qucantity", style);
        createCell(row, 4, "time", style);
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (OrderListDTO record: orderList) {
        	for(OrdersDTO orders: record.getItems()) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, orders.getId(), style);
            createCell(row, columnCount++, orders.getName(), style);
            createCell(row, columnCount++, orders.getPrice(), style);
            createCell(row, columnCount++, orders.getQuantity(), style);
            createCell(row, columnCount++, record.getTimestamp().toString(), style);
        	}
        }
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        saveFileToResourcesFolder();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
    
    public void generateExcelFileToSystem() throws IOException {
        writeHeader();
        write();
        saveFileToResourcesFolder();
    }
    
    private void saveFileToResourcesFolder() throws IOException {
    	
    	String filePath = System.getProperty("java.io.tmpdir") + "/Sales_Report_";
    	//excelGenerator.generateExcelFileToSystem(filePath);
    	System.out.println("filepath:  "+filePath);
        // Define the directory where you want to save the file
        String directoryPath = "src/main/resources/excel"; // You can change this as needed

        // Create the directory if it doesn't exist
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory
        }

        // Create a file object with the desired file name
        File file = new File(filePath, "generatedExcel.xlsx");

        // Write the workbook to the file
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        }
    }
}

