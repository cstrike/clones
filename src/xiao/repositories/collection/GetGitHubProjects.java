package xiao.repositories.collection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.OutputStream;


public class GetGitHubProjects {
	public static void main(String[] args) throws Exception {
		downloadProjects();
	}
	
	public static void downloadProjects() throws IOException, InterruptedException{
		CSVReader csvReader = new CSVReader(new FileReader("projects.csv"));
		List<String[]> rows = csvReader.readAll();
		rows.remove(0);
		//final BufferedReader br=new BufferedReader(new FileReader("E:/users/kochharps/Projects/ISSTA 2016/FinalApacheProjects.txt"));
		String line="";
		//while((line=br.readLine())!=null)
		for(String [] cols : rows){
			line = cols[3];
			String[] command =
				{
					"cmd",
				};
			Process p = Runtime.getRuntime().exec(command);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());

			stdin.println("E:");
			stdin.println("cd E:/repositories");
			stdin.println("git clone "+line);
			stdin.close();

			int returnCode = p.waitFor();
			System.out.println("Return code = " + returnCode);
		}
		csvReader.close();
	}
	
}

class SyncPipe implements Runnable
{
	public SyncPipe(InputStream istrm, OutputStream ostrm) {
		istrm_ = istrm;
		ostrm_ = ostrm;
	}
	public void run() {
		try
		{
			final byte[] buffer = new byte[1024];
			for (int length = 0; (length = istrm_.read(buffer)) != -1; )
			{
				ostrm_.write(buffer, 0, length);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private final OutputStream ostrm_;
	private final InputStream istrm_;
}
