package outserra.awards.adapter.movie;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"dev"})
class WinnerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testLookForWinnerRange() throws Exception {
		mockMvc.perform(get("/api/winners"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.min", hasSize(2)))
				.andExpect(jsonPath("$.min.[0].producer", is("joel silver")))
				.andExpect(jsonPath("$.min.[0].interval", is(1)))
				.andExpect(jsonPath("$.min.[0].previousWin", is(1991)))
				.andExpect(jsonPath("$.min.[0].followingWin", is(1990)))
				.andExpect(jsonPath("$.min.[1].producer", is("bo derek")))
				.andExpect(jsonPath("$.min.[1].interval", is(6)))
				.andExpect(jsonPath("$.min.[1].previousWin", is(1990)))
				.andExpect(jsonPath("$.min.[1].followingWin", is(1984)))
				.andExpect(jsonPath("$.max", hasSize(4)))
				.andExpect(jsonPath("$.max.[0].producer", is("joel silver")))
				.andExpect(jsonPath("$.max.[0].interval", is(1)))
				.andExpect(jsonPath("$.max.[0].previousWin", is(1991)))
				.andExpect(jsonPath("$.max.[0].followingWin", is(1990)))
				.andExpect(jsonPath("$.max.[1].producer", is("bo derek")))
				.andExpect(jsonPath("$.max.[1].interval", is(6)))
				.andExpect(jsonPath("$.max.[1].previousWin", is(1990)))
				.andExpect(jsonPath("$.max.[1].followingWin", is(1984)))
				.andExpect(jsonPath("$.max.[2].producer", is("buzz feitshans")))
				.andExpect(jsonPath("$.max.[2].interval", is(9)))
				.andExpect(jsonPath("$.max.[2].previousWin", is(1994)))
				.andExpect(jsonPath("$.max.[2].followingWin", is(1985)))
				.andExpect(jsonPath("$.max.[3].producer", is("matthew vaughn")))
				.andExpect(jsonPath("$.max.[3].interval", is(13)))
				.andExpect(jsonPath("$.max.[3].previousWin", is(2015)))
				.andExpect(jsonPath("$.max.[3].followingWin", is(2002)))

		;
	}

}
