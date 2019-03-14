package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dao.VooDao;
@Controller
public class VooController {

	@Autowired
	private VooDao repositorio;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(VooController.class);

	@GetMapping("/")
    public String index(Model model) {
        atualizarBase();
		model.addAttribute("voos",voos());
		return "index";
    }

	private void atualizarBase() {
	    try {
		      final String URL = "https://opensky-network.org/api/states/all";
		      Map o = ClientBuilder
		        .newClient()
		        .target(URL)
		        .queryParam("lamin", "45.8389")
		        .queryParam("lomin", "5.9962")
		        .queryParam("lamax", "47.8229")
		        .queryParam("lomax", "10.5226")
		        .request()
		        .accept(MediaType.APPLICATION_JSON)
		        .get(Map.class)
		      ;
		     processa(o);
		    } catch (RuntimeException e) {
		      e.printStackTrace();
		      throw new RuntimeException(e);
		    }
	}
	
	private List<Voo> voos() {
		return repositorio.findAll();	
	  }

	  private void processa(final Map map) {
	    final List<Voo> voos = new ArrayList<Voo>();
	    final List lines = (List)map.get("states");
	    for(int i = 0, l = lines.size(); i < l; i++) {
	      	try {
		    	final List line = (List)lines.get(i);
	      		voos.add(new Voo(line));
          		repositorio.saveAll(voos);	      		
	     	}catch(Exception ex) {
	    		logger.warn("Warn: Esse icao24 já consta na base: " + ex);
	    		logger.error("Error: Esse icao24 já consta na base: " + ex);
	      	}
	    }
	    
	  }
}
