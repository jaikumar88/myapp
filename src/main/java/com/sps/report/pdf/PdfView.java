/**
 * 
 */
package com.sps.report.pdf;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sps.stores.application.AppUtil;
import com.sps.stores.application.AppUtilImpl;
import com.sps.stores.model.Transaction;

/**
 * Created by Jai
 */
public class PdfView extends AbstractPdfView {
	
	
	AppUtil appUtil = new AppUtilImpl();
	
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Transaction> transactions = (List<Transaction>) model.get("transactions");
    	// change the file name
        response.setHeader("Content-Disposition", "attachment; filename="+model.get("FileName")+".pdf");

        document.add(new Paragraph("============================================================================================================="));
        document.add(new Paragraph("------------------------------------------SPS Bill Details                                "));
        document.add(new Paragraph("============================================================================================================="));
        document.add(new Paragraph("                             ------- Generated By " + model.get("FileName")+"------ At"+LocalDate.now()+"    "));

        PdfPTable table = new PdfPTable(14);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Product Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Weight:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("No Of Packet:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Rate:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Memo:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Total Amount:", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Expense per piece:", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Deduction:", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Other Expense:", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Total Expenses:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Due Amount:", font));
        table.addCell(cell);
        double totalAmount = 0.00;
        double dueAmount = 0.00;
        for(Transaction trans : transactions){
        	
            table.addCell(trans.getCustomer().getFirstName());
            table.addCell(trans.getCustomer().getLastName());
            table.addCell(appUtil.dateToString(trans.getActivityCreateDate()));
            table.addCell(trans.getProductType());
            table.addCell(trans.getWeight());
            table.addCell(trans.getQuantity());
            table.addCell(trans.getRate());
            table.addCell(trans.getMemo());
            table.addCell(trans.getTotalAmount());
            table.addCell(trans.getExpPerItem());
            table.addCell(trans.getOtherExpense());
            table.addCell(trans.getDeductionPercent());
            table.addCell(trans.getTotalExpense());
            table.addCell(trans.getDueAmount());
           
            totalAmount+= "".equalsIgnoreCase(trans.getTotalAmount())?0.00:Double.valueOf(trans.getTotalAmount());
            dueAmount+= "".equalsIgnoreCase(trans.getDueAmount())?0.00:Double.valueOf(trans.getDueAmount());
    			
    		
        }
       

        document.add(table);
        document.add(new Paragraph("============================================================================================================="));
        document.add(new Paragraph("               "+"Total Amount="+String.valueOf(totalAmount)+ "   And Due Amount = "+String.valueOf(dueAmount)));
        document.add(new Paragraph("============================================================================================================="));
    }
}
