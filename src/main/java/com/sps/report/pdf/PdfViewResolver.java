package com.sps.report.pdf;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created by Jai
 */
public class PdfViewResolver implements ViewResolver {
	
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        PdfView view = new PdfView();
        return view;
    }
}