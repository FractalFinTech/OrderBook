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
        mvc.perform(MockMvcRequestBuilders.get("/market/add/item/apple").accept(MediaType.APPLICATION_JSON))
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
        MarketItemDao marketItemDao = new MarketItemDao();
        marketItemDao.setName("Apple");
        marketItemDao.setPrice(148.78);
        marketItemDao.setQty(25);
        marketItemDao.setType("Market");
        marketItemDao.setTranType("Buy");
        mvc.perform(MockMvcRequestBuilders.post("/market/bid/add")
                .content(asJsonString(marketItemDao))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
    }

    @Test
    public void addOffer() throws Exception {
        MarketItemDao marketItemDao = new MarketItemDao();
        marketItemDao.setName("Apple");
        marketItemDao.setPrice(148.78);
        marketItemDao.setQty(20);
        marketItemDao.setType("Market");
        marketItemDao.setTranType("Sell");
        mvc.perform(MockMvcRequestBuilders.post("/market/offer/add")
                .content(asJsonString(marketItemDao))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
    }
}
