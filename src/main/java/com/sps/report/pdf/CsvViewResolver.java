package com.sps.report.pdf;


import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created by jai 
 */
public class CsvViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        CsvView view = new CsvView();
        return view;
    }
}