package fractalfintech.orderbook;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MarketControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    public void addMarketItem() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/add/item/apple").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addBid() throws Exception {
        OrderItemDao marketItemDao = new OrderItemDao();
        marketItemDao.setName("Apple");
        marketItemDao.setPrice(148.78);
        marketItemDao.setQty(25);
        marketItemDao.setType("Market");
        marketItemDao.setTranType("Buy");
        mvc.perform(MockMvcRequestBuilders.post("/bid/add")
                .content(asJsonString(marketItemDao))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success: bid added.")));
    }

    @Test
    public void addOffer() throws Exception {
        OrderItemDao marketItemDao = new OrderItemDao();
        marketItemDao.setName("Apple");
        marketItemDao.setPrice(158.78);
        marketItemDao.setQty(20);
        marketItemDao.setType("Market");
        marketItemDao.setTranType("Sell");
        mvc.perform(MockMvcRequestBuilders.post("/offer/add")
                .content(asJsonString(marketItemDao))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success: offer added.")));
    }
}
