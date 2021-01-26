package br.com.felipe.controller;

import br.com.felipe.controllers.BillToPayController;
import br.com.felipe.dtos.BillToPayDTO;
import br.com.felipe.models.BillToPay;
import br.com.felipe.repositories.BillToPayRepository;
import br.com.felipe.services.BillToPayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BillToPayController.class)
public class BillToPayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillToPayService billToPayService;

    @MockBean
    private BillToPayRepository billToPayRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve buscar todas as contas a pagar")
    void getAll() throws Exception {
        List<BillToPayDTO> list = new ArrayList<>();
        list.add(new BillToPayDTO("firstName4", 10.0, 0.0, 0, new Date()));
        list.add(new BillToPayDTO("firstName5", 15.0, 0.0, 0, new Date()));
        Mockito.when(billToPayService.getAll()).thenReturn(list);
        MvcResult mvc = mockMvc.perform(get("/bill")).andExpect(status().isOk()).andReturn();
        String result = mvc.getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void save() throws Exception {
        BillToPay bill = new BillToPay(null, "Teste Mock", 15.0, 0.0, new Date(), new Date(), 0);
        BillToPay billSaved = new BillToPay(1L, "Teste Mock", 15.0, 0.0, new Date(), new Date(), 0);

        Mockito.when(billToPayRepository.save(bill)).thenReturn(billSaved);

        MvcResult mvc = mockMvc.perform(
                post("/bill")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(bill)))
                .andExpect(status().isOk())
                .andReturn();
        String result = mvc.getResponse().getContentAsString();
        System.out.println(result);
    }
}