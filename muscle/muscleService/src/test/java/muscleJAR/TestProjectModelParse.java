package muscleJAR;

import static org.junit.Assert.*;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.muscle.xml.ProjectModel;

public class TestProjectModelParse {

	@Test
	public void test() {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = loader.getResourceAsStream("muscle.xml");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ProjectModel.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			ProjectModel pm = (ProjectModel)jaxbUnmarshaller.unmarshal(inputStream);
			
			assertTrue(pm.getAnalist().equals("Analista Programador RPG"));
			assertTrue(pm.getBuild().getBindingDirectory().equals("PJRBNDDIR"));
			assertTrue(pm.getBuild().getPdeProyectList().getPdeProject().get(0).getAvpNumber() == 307);
		} catch (JAXBException e) {
			e.printStackTrace();
		}  
	}

}
