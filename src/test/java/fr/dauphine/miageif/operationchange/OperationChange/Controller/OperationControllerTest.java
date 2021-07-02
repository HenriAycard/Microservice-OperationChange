package fr.dauphine.miageif.operationchange.OperationChange.Controller;
import fr.dauphine.miageif.operationchange.OperationChange.Model.OperationChange;
import fr.dauphine.miageif.operationchange.OperationChange.OperationChangeApplication;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import org.junit.Before;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OperationChangeApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class OperationControllerTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    @Order(1)
    public void getAllOperationChange() throws Exception {
        String uri = "/operation-change";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertTrue(operationChanges.length > 0);
    }

    @Test
    @Order(2)
    public void getOperationChangeById() throws Exception {
        String uri = "/operation-change/id/1243";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(3)
    public void getOperationChangeByMontant() throws Exception {
        String uri = "/operation-change/montant/1000";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(4, operationChanges.length);
    }

    @Test
    @Order(4)
    public void getOperationChangeByDate() throws Exception {
        String uri = "/operation-change/date/2021-06-21";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(3, operationChanges.length);
    }

    @Test
    @Order(5)
    public void getOperationChangeByTaux() throws Exception {
        String uri = "/operation-change/taux/1.19";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(3, operationChanges.length);
    }

    @Test
    @Order(6)
    public void getOperationChangeBySourceAndDest() throws Exception {
        String uri = "/operation-change/source/EUR/dest/USD";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(4, operationChanges.length);
    }

    @Test
    @Order(7)
    public void getOperationChangeBySourceAndDestAndDate() throws Exception {
        String uri = "/operation-change/source/EUR/dest/USD/date/2021-06-21";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(2, operationChanges.length);
    }

    @Test
    @Order(8)
    public void getOperationChangeByCounterpart() throws Exception {
        String uri = "/operation-change/counterpart/HSBC";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(1, operationChanges.length);
    }

    @Test
    @Order(9)
    public void getOperationChangeByCounterpartAndSourceAndDest() throws Exception {
        String uri = "/operation-change/counterpart/HSBC/source/EUR/dest/USD";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(1, operationChanges.length);
    }

    @Test
    @Order(10)
    public void getOperationChangeByCounterpartAndSourceAndDestAndDate() throws Exception {
        String uri = "/operation-change/counterpart/HSBC/source/EUR/dest/USD/date/2021-06-21";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(1, operationChanges.length);
    }

    @Test
    @Order(11)
    public void getOperationChangeByCounterpartAndDate() throws Exception {
        String uri = "/operation-change/counterpart/HSBC/date/2021-06-21";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange[] operationChanges= mapFromJson(content, OperationChange[].class);
        assertEquals(1, operationChanges.length);
    }

    @Test
    @Order(12)
    public void createOperationChange() throws Exception {
        String uri = "/operation-change";
        OperationChange operationChange = new OperationChange("EUR", "USD", 500, new BigDecimal(1.2234), "2021-06-20", "Banque_Radin");

        String inputJson = mapToJson(operationChange);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange _operationchange = mapFromJson(content, OperationChange.class);
        _operationchange.setId(null);
        assertEquals(operationChange, _operationchange);
    }

    @Test
    @Order(13)
    public void updateOperationChangeById() throws Exception {
        String uri = "/operation-change/id/1237";
        OperationChange operationChange = new OperationChange("USD", "RON", 500, new BigDecimal(1.24), "2021-06-24", "Cyril_Lignac");
        String inputJson = mapToJson(operationChange);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange _operationchange = mapFromJson(content, OperationChange.class);
        operationChange.setId(1237L);
        assertEquals(200, status);
        assertEquals(operationChange, _operationchange);
    }

    @Test
    @Order(14)
    public void updateTauxForOperationChange() throws Exception {
        String uri = "/operation-change/id/1237/taux/1.7582";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange _operationchange = mapFromJson(content, OperationChange.class);
        assertEquals(200, status);
        assertEquals("1.7582", String.valueOf(_operationchange.getTaux()));
    }

    @Test
    @Order(15)
    public void updateCounterpartForOperationChange() throws Exception {
        String uri = "/operation-change/id/1237/counterpart/JAP";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange _operationChange = mapFromJson(content, OperationChange.class);
        assertEquals(200, status);
        assertEquals("JAP", String.valueOf(_operationChange.getCounterpart()));
    }

    @Test
    @Order(15)
    public void updateMontantForOperationChange() throws Exception {
        String uri = "/operation-change/id/1237/montant/200";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange _operationChange = mapFromJson(content, OperationChange.class);
        assertEquals(200, status);
        assertEquals(Integer.valueOf(200), _operationChange.getMontant());
    }

    @Test
    @Order(16)
    public void updateDateForOperationChange() throws Exception {
        String uri = "/operation-change/id/1237/date/2021-06-19";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        OperationChange _operationChange = mapFromJson(content, OperationChange.class);
        assertEquals(200, status);
        assertEquals("2021-06-19", _operationChange.getDate());
    }

    @Test
    @AfterAll
    public void deleteOperationChangeById() throws Exception {
        String uri = "/operation-change/id/1237";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }


}
