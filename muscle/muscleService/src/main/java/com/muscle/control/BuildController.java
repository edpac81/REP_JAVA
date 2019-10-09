package com.muscle.control;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ibm.as400.access.AS400;
import com.muscle.model.PdeItem;
import com.muscle.model.RpgleModuleItem;
import com.muscle.model.RpglePgmItem;
import com.muscle.model.ServicePgmItem;
import com.muscle.model.SqlFileItem;
import com.muscle.model.SqlRpgleItem;
import com.muscle.system.CommandExecute;
import com.muscle.system.JobLogPrinter;
import com.muscle.system.SpooledFileWriter;
import com.muscle.xml.Build;
import com.muscle.xml.Item;
import com.muscle.xml.Library;
import com.muscle.xml.ObjectType;
import com.muscle.xml.PdeProject;
import com.muscle.xml.SourceType;

public class BuildController {

	private Build projectBuild;
	private AS400 system;
	Logger logger;
	private CommandExecute command;

	public BuildController(Build projectBuild, AS400 system) throws Exception {
		super();
		logger = LogManager.getLogger(this.getClass().getName());
		this.projectBuild = projectBuild;
		this.system = system;
		this.command = new CommandExecute(this.system);
	}

	public void execute() throws Exception {
		logger.info("Build Started.");
		prepareEnvironment();
		processPdeProjects();
		logger.info("Build Ended.");
	}

	private void prepareEnvironment() throws Exception {
		List<Library> libList = this.projectBuild.getLibraryList().getLibrary();
		Collections.sort(libList);

		logger.info("Loading Library List");

		// add libraries to job library list
		for (int i = 0; i < libList.size(); i++) {
			try {
				command.run("ADDLIBLE " + libList.get(i).getName() + " *LAST");
			} catch (Exception e) {
				logger.warn(e);
			}
		}
		// change current library
		try {
			command.run("CHGCURLIB " + this.projectBuild.getCurrentLibrary());
		} catch (Exception e) {
			logger.warn(e);
		}
	}

	private void processPdeProjects() throws Exception {
		List<PdeProject> pdeProjectList = this.projectBuild.getPdeProyectList().getPdeProject();

		// Clean Spool
		try {
			command.run("DLTSPLF FILE(*SELECT)");
		} catch (Exception e) {
			logger.warn("Delete Spoolfile Failed.", e);
		}

		// Ordenar projectos
		Collections.sort(pdeProjectList);
		for (int i = 0; i < pdeProjectList.size(); i++) {
			builPdeProject(pdeProjectList.get(i));
		}
	}

	private void builPdeProject(PdeProject pdeProject) throws Exception {
		List<Item> itemList = pdeProject.getItems().getItem();

		Collections.sort(itemList);

		logger.info("Start build PDEProject: " + pdeProject.toString());
		for (int i = 0; i < itemList.size(); i++) {
			builItem(itemList.get(i));
		}
	}

	private void builItem(Item item) throws Exception {
		String srcFileLib = this.projectBuild.getCurrentLibrary();
		PdeItem workItem = null;

		if (item.getSourceType().equals(SourceType.SQLRPGLE)) {
			item.setSourceFileLibrary(srcFileLib);
			workItem = new SqlRpgleItem(item);
		} else if (item.getSourceType().equals(SourceType.RPGLE) && item.getObjectType().equals(ObjectType.PGM)) {
			item.setSourceFileLibrary(srcFileLib);
			workItem = new RpglePgmItem(item);
		} else if (item.getSourceType().equals(SourceType.RPGLE) && item.getObjectType().equals(ObjectType.MODULE)) {
			item.setSourceFileLibrary(srcFileLib);
			workItem = new RpgleModuleItem(item);
		} else if (item.getSourceType().equals(SourceType.SQL) && item.getObjectType().equals(ObjectType.FILE)) {
			item.setSourceFileLibrary(srcFileLib);
			workItem = new SqlFileItem(item);
		} else if (item.getSourceType().equals(SourceType.BND)) {
			item.setSourceFileLibrary(srcFileLib);
			workItem = new ServicePgmItem(item, logger);
		}

		if (workItem != null) {
			try {
				workItem.runBuilCommand(this);
			} catch (Exception e) {
				JobLogPrinter jlp = new JobLogPrinter(system, command.getJob());
				jlp.printJobLog();
				SpooledFileWriter splfw = new SpooledFileWriter(system);
				splfw.getLastSpooledFile();
				splfw.exportSpooledFile();
				throw e;
			}
		}
	}		

	public Build getProjectBuild() {
		return projectBuild;
	}

	public CommandExecute getCommand() {
		return command;
	}
}
