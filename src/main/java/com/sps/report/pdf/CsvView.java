/**
 * 
 */
package com.sps.report.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.sps.stores.model.Transaction;

/**
 * Created by Jai
 */
public class CsvView extends AbstractCsvView {


    @Override
    protected void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");

        List<Transaction> transactions = (List<Transaction>) model.get("transactions");
        String[] header = {"Firstname","LastName","Date","Product Type","Weight","No of Item","Rate","Memo", "Total Amount","Expense Per item","Other Expense","Deduction","Total Expense","Due Amount"};
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        csvWriter.writeHeader(header);

       /* for(Transaction transaction : transactions){
            csvWriter.write(transaction, header);
        }*/
        csvWriter.close();


    }
}