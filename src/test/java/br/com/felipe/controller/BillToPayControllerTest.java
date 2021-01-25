package br.com.felipe.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.felipe.models.BillToPay;
import br.com.felipe.util.AbstractTest;

public class BillToPayControllerTest extends AbstractTest {
	
   @Before
   public void setUp() {
      super.setUp();
   }
   
   @Test
   public void getBillsList() throws Exception {
	   
      String uri = "/bill";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      BillToPay[] productlist = super.mapFromJson(content, BillToPay[].class);
      assertTrue(productlist.length > 0);
   }
   @Test
   public void createProduct() throws Exception {
      String uri = "/bill";
      
      BillToPay bill = new BillToPay();
      bill.setId(50l);
      bill.setName("Felipe 50");
      bill.setCorretValue(12.0);
      bill.setPaymentDate(new Date());
      bill.setDueDate(new Date());
            
      String inputJson = super.mapToJson(bill);
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .content(inputJson)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(201, status);
      String content = mvcResult.getResponse().getContentAsString();
      assertEquals(content, "Conta Foi Cadastrada");
   }

}