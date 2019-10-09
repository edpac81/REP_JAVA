package test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.control.DAOInterface;
import com.ibm.as400.access.QSYSObjectPathName;

public class TestRPGDAO {

	@Test
	public void test_01_ConstructorWithOpn() {
		String daoName = "ALEATABMNI";
		boolean error = false;
		boolean includeIsOnFile = true;
		boolean includeSelect = true;
		boolean includeInsert = true;
		boolean includeUpdate = true;
		boolean includeDelete = true;
		boolean includeSetters = true;
		boolean includeGetters = true;
		int inputType = DAOInterface.INPUT_AS400_TABLE;
		int outputType = DAOInterface.OUTPUT_AS400_MEMBER;
		QSYSObjectPathName inputTable = new QSYSObjectPathName("LIBEPC", daoName, "FILE");
		QSYSObjectPathName rpgSource = new QSYSObjectPathName("LIBEPC", "QCIF001MOD", daoName, "MBR");
		QSYSObjectPathName txtSource = new QSYSObjectPathName("LIBEPC", "QCIF001TXT", daoName, "MBR");
		DAOInterface daoInterface = new DAOInterface(daoName, includeIsOnFile, 
				includeSelect, includeInsert, includeUpdate, includeDelete,
				includeSetters, includeGetters, 
				inputType, outputType, 
				inputTable, rpgSource, txtSource);

		try {
			daoInterface.buildObjects();
			daoInterface.executeController();
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
			fail("Error");
		}
		assertFalse("Ejecución sin errores", error);
	}

	@Test
	public void test_02_ConstructorWithStrings() {
		String daoName = "BITAR11036";
		boolean error = false;
		boolean includeIsOnFile = true;
		boolean includeSelect = true;
		boolean includeInsert = true;
		boolean includeUpdate = true;
		boolean includeDelete = true;
		boolean includeSetters = true;
		boolean includeGetters = true;
		int inputType = DAOInterface.INPUT_AS400_TABLE;
		int outputType = DAOInterface.OUTPUT_AS400_MEMBER;
		DAOInterface daoInterface = new DAOInterface(daoName, includeIsOnFile, 
				includeSelect, includeInsert, includeUpdate, includeDelete, 
				includeSetters, includeGetters, 
				inputType, outputType,  
				"LIBEPC", "BITAR11036", "LIBEPC", "QENR004MOD",
				"BITAR11036", "LIBEPC", "QENR004TXT", "BITAR11036");

		try {
			daoInterface.buildObjects();
			daoInterface.executeController();
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
			fail("Error");
		}
		assertFalse("Ejecución sin errores", error);
	}

	@Test
	public void test_03_NoExistsTable() {
		String daoName = "TABLA";
		boolean error = false;
		boolean includeIsOnFile = false;
		boolean includeSelect = true;
		boolean includeInsert = true;
		boolean includeUpdate = true;
		boolean includeDelete = true;
		boolean includeSetters = true;
		boolean includeGetters = true;
		int inputType = DAOInterface.INPUT_AS400_TABLE;
		int outputType = DAOInterface.OUTPUT_AS400_MEMBER;
		DAOInterface daoInterface = new DAOInterface(daoName, includeIsOnFile, includeSelect, includeInsert, includeUpdate,
				includeDelete, includeSetters, includeGetters, inputType, outputType, 
				"LIBEPC", daoName, 
				"LIBEPC", "QENR004MOD", daoName,
				"LIBEPC", "QENR004TXT", daoName);

		try {
			daoInterface.buildObjects();
			daoInterface.executeController();
			fail("Error");
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
			assertTrue("Ejecución con errores", error);
		}
	}
	
	@Test
	public void test_03_isOnFile() {
		String daoName = "CUMANOMTAB";
		boolean error = false;
		boolean includeIsOnFile = true;
		boolean includeSelect = true;
		boolean includeInsert = true;
		boolean includeUpdate = true;
		boolean includeDelete = true;
		boolean includeSetters = true;
		boolean includeGetters = true;
		int inputType = DAOInterface.INPUT_AS400_TABLE;
		int outputType = DAOInterface.OUTPUT_CONSOLE;
		DAOInterface daoInterface = new DAOInterface(daoName, includeIsOnFile, includeSelect, includeInsert, includeUpdate,
				includeDelete, includeSetters, includeGetters, inputType, outputType, 
				"LIBEPC", daoName, 
				"LIBEPC", "QENR004MOD", daoName,
				"LIBEPC", "QENR004TXT", daoName);

		try {
			daoInterface.buildObjects();
			daoInterface.executeController();
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
			fail();
		}
		assertFalse("Ejecución correcta",error);
	}
	
	@Test
	public void test_04_csv(){
		String daoName = "CIFPAR2";
		boolean error = false;
		String csv = "ClassData.csv";
		int inputType = DAOInterface.INPUT_CSV_FILE;
		int outputType = DAOInterface.OUTPUT_AS400_MEMBER;
		QSYSObjectPathName rpgSource = new QSYSObjectPathName("LIBEPC", "QCIF001MOD", daoName, "MBR");
		QSYSObjectPathName txtSource = new QSYSObjectPathName("LIBEPC", "QCIF001TXT", daoName, "MBR");
		DAOInterface daoInterface = new DAOInterface(daoName, csv,  inputType, outputType, 
				rpgSource, txtSource);

		try {
			daoInterface.buildObjects();
			daoInterface.executeController();
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
			fail();
		}
		assertFalse("Ejecución correcta",error);
	}
	
	@Test
	public void test_05_csv(){
		String daoName = "EXACLS2";
		boolean error = false;
		String csv = "ClassData.csv";
		int inputType = DAOInterface.INPUT_CSV_FILE;
		int outputType = DAOInterface.OUTPUT_AS400_MEMBER;
		
		DAOInterface daoInterface = new DAOInterface(daoName, csv,  inputType, outputType, 
				"LIBEPC", "QCIF001MOD", daoName,
				"LIBEPC", "QCIF001TXT", daoName);

		try {
			daoInterface.buildObjects();
			daoInterface.executeController();
		} catch (Exception e) {
			e.printStackTrace();
			error = true;
			fail();
		}
		assertFalse("Ejecución correcta",error);
	}
}
