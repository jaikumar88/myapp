/**
 * 
 */
package com.sps.report.pdf;

/**
 * @author Jai1.Kumar
 *
 */
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.sps.stores.model.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");

        @SuppressWarnings("unchecked")
        List<Transaction> transactions = (List<Transaction>) model.get("transactions");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Transaction Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Firstname");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("LastName");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Date");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Product Type");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Weight");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("No Of Item");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("Rate");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Memo");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("Total Amount");
        header.getCell(8).setCellStyle(style);
        header.createCell(9).setCellValue("Exp per piece");
        header.getCell(9).setCellStyle(style);
        header.createCell(10).setCellValue("Other Expense");
        header.getCell(10).setCellStyle(style);
        header.createCell(11).setCellValue("Deduction");
        header.getCell(11).setCellStyle(style);
        header.createCell(12).setCellValue("Total Expenses");
        header.getCell(12).setCellStyle(style);
        header.createCell(13).setCellValue("Due Amount");
        header.getCell(13).setCellStyle(style);



        int rowCount = 1;

        for(Transaction transaction : transactions){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(transaction.getCustomer().getFirstName());
            userRow.createCell(1).setCellValue(transaction.getCustomer().getLastName());
            userRow.createCell(2).setCellValue(transaction.getActivityCreateDate());
            userRow.createCell(3).setCellValue(transaction.getProductType());
            userRow.createCell(4).setCellValue(transaction.getWeight());
            userRow.createCell(5).setCellValue(transaction.getQuantity());
            userRow.createCell(6).setCellValue(transaction.getRate());
            userRow.createCell(7).setCellValue(transaction.getMemo());
            userRow.createCell(8).setCellValue(transaction.getTotalAmount());
            userRow.createCell(9).setCellValue(transaction.getExpPerItem());
            userRow.createCell(10).setCellValue(transaction.getOtherExpense());
            userRow.createCell(11).setCellValue(transaction.getDeductionPercent());
            userRow.createCell(12).setCellValue(transaction.getTotalExpense());
            userRow.createCell(13).setCellValue(transaction.getDueAmount());

            }

    }

}
