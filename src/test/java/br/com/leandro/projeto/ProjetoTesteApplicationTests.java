package br.com.leandro.projeto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjetoTesteApplicationTests {

	private MockMvc mock;
	
	@Test
	public void testeGETClientes() throws Exception{
		this.mock.perform(MockMvcRequestBuilders.get("/clientes")).andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
