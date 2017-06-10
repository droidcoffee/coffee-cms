package coffee.cms.core.action;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import coffee.Config;
import coffee.util.io.FileUtils;
import coffee.util.log.Log;

public class LogAction extends Action {

	private String date;

	@Override
	public void query(HttpServletRequest request) {
		date = request.getParameter("date");
		String[] files = getLogFiles();
		if (!isEmpty(date)) {
			for (String file : files) {
				if (file.contains(date)) {
					files = new String[] { file };
					break;
				}
			}
		}
		Arrays.sort(files);
		request.setAttribute("items", files);
	}

	@Override
	public void delete(HttpServletRequest request) {
		String file = request.getParameter("sid");
		try {
			String cmd = "sudo rm -rf " + Config.LOG_DIR + "/" + file;
			Log.info(this, "删除日志_" + cmd);
			Runtime.getRuntime().exec(cmd);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toUpdate(HttpServletRequest request) {
		String file = request.getParameter("sid");
		String workDir = FileUtils.getProjectBasePath();
		try {
			String mkdir = "sudo mkdir -p " + workDir + "/logs/";
			String cpLog = "sudo cp " + Config.LOG_DIR + "/" + file + " "
					+ workDir + "/logs/" + file;
//			ProcessBuilder pd = new ProcessBuilder(mkdir, cpLog);
			Log.info(this, "查看日志_" + mkdir);
			Log.info(this, "查看日志_" + cpLog);
//			pd.start();
			 Runtime.getRuntime().exec(mkdir);
			 Runtime.getRuntime().exec(cpLog);
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("file", request.getContextPath() + "/logs/" + file);
	}

	public String[] getLogFiles() {
		File file = new File(Config.LOG_DIR);
		return file.list();
	}
}
