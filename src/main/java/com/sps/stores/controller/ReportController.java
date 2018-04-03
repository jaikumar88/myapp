package com.sps.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sps.stores.service.TransactionService;

@Controller
@RequestMapping("/download")
public class Export {

@Autowired
TransactionService transactionService;

/**
 * Handle request to download an Excel document
 */
@RequestMapping(value = "/download", method = RequestMethod.GET)
public String download(Model model) {
    model.addAttribute("transactions", transactionService.findAllTransactions());
    return "";
}
}