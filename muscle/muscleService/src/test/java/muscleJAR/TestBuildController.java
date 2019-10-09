package muscleJAR;

import static org.junit.Assert.*;

import org.junit.Test;

import com.muscle.config.Configuration;
import com.muscle.control.MainController;
import com.muscle.system.SpooledFileWriter;
import com.muscle.system.SystemConnection;

public class TestBuildController {

	@SuppressWarnings("unused")
	@Test
	public void test() {
		MainController mc = new MainController();
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testSpoolFile(){
		try {
			Configuration.load();
			SystemConnection sysConn = new SystemConnection();			
			String spoolName = "BCUM75CNTL";
			int spoolNumber = 1254;
			String jobName = "QPRTJOB";
			String jobNumber = "998907";
			String jobUser = "BCOINFEPC";
			SpooledFileWriter splfw = new SpooledFileWriter(sysConn.getSystemConn());
			splfw.getLastSpooledFile();
			splfw.exportSpooledFile();
			
		} catch (Exception e) {			
			fail(e.getMessage());
		}
	}

}
