package com.sda.dmpcars.controller;

import com.sda.dmpcars.model.Rent;
import com.sda.dmpcars.service.RentService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RentService rentService;

    @InjectMocks
    private RentController rentController;

    @Before
    public void init() {
        this.mockMvc
                = MockMvcBuilders
                .standaloneSetup()
                .build();
    }

    @Test
    public void getAllRentsByCarId() throws Exception {
        List<Rent> rents = new ArrayList<>();
        Rent r1
                = Rent
                .builder()
                .id(1)
                .dateFrom(LocalDate.of(2017, 12, 2))
                .dateTo(LocalDate.of(2017, 12, 20))
                .totalPrice(BigDecimal.valueOf(1000))
                .build();

        Mockito.when(rentController.getAllRentsByCarId(1)).thenReturn(rents);
        List<Rent> actual = rentService.getAllRentsByCarId(1);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/rent/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateFrom", Matchers.is("2017-12-02")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateTo", Matchers.is("2017-12-20")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", Matchers.is("1000")));

    }

    @Test
    public void getAllRentsByAccountId() throws Exception {
        List<Rent> rents = new ArrayList<>();
        Rent r1
                = Rent
                .builder()
                .id(1)
                .dateFrom(LocalDate.of(2017, 12, 2))
                .dateTo(LocalDate.of(2017, 12, 20))
                .totalPrice(BigDecimal.valueOf(1000))
                .build();

        Mockito.when(rentController.getAllRentsByAccountId(1)).thenReturn(rents);
        List<Rent> actual = rentService.getAllRentsByAccountId(1);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/rent/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateFrom", Matchers.is("2017-12-02")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateTo", Matchers.is("2017-12-20")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", Matchers.is("1000")));

    }

    @Test
    public void addRent() {
        Rent rent
                = Rent
                .builder()
                .id(1)
                .dateFrom(LocalDate.of(2017, 12, 2))
                .dateTo(LocalDate.of(2017, 12, 20))
                .totalPrice(BigDecimal.valueOf(1000))
                .build();


    }

    @Test
    public void shouldAddRentHappyPath() {
        String excpected = "OK";
        Rent rent = new Rent();
        rent.setId(1);

        Mockito.doNothing().when(rentService).addRent(rent);
        String actual = rentController.addRent(rent);

        Assert.assertEquals(excpected, actual);

        Mockito.verify(rentService, Mockito.timeout(1)).addRent(rent);
    }

    @Test
    public void updateRent() {
    }

    @Test
    public void deleteRent() {
    }

    @Test
    public void deleteAllRents() {
    }

}