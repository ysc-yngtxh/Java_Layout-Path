package K11_流.流Ⅴ_File类;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class 流13_目录拷贝 {

	public static void main(String[] args) {
		// 手动填写源文件夹路径
		File srcDir = new File(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/流Ⅰ_文件专属流");
		// 手动填写目标路径
		File targetDir = new File(System.getProperty("user.dir") + "/Java基础及进阶/K11_流/流Ⅴ_File类/newDir");
		// 调用复制文件夹方法
		copyDir(srcDir, targetDir);
	}

	// 复制文件夹（目录）方法
	public static void copyDir(File srcDir, File targetDir) {
		if (!targetDir.exists()) { // 如果目标文件不存在
			targetDir.mkdir();     // 则创建之
		}
		File[] files = srcDir.listFiles(); // 列举目录下所有文件（包含子目录）存放至数组
		for (File file : files) {          // 增强for循环提取文件
			if (file.isFile()) {           // 判断是否为文件
				// 调用复制文件方法；创建新的File表示源文件名及目标文件名，文件名为源文件夹路径＋文件名，目标文件名同理
				copyFile(new File(srcDir + "/" + file.getName()), new File(targetDir + "/" + file.getName()));
			} else {
				// 不是文件则为目录（文件夹），以下为文件夹的处理方法
				// 如果当前file为文件夹，则再次调用copyDir方法（递归）
				copyDir(new File(srcDir + "/" + file.getName()), new File(targetDir + "/" + file.getName()));
			}
		}
	}

	// 复制文件方法
	public static void copyFile(File srcFile, File targetFile) {
		// 提高读取效率，从数据源
		FileInputStream fis = null;
		// 提高写入效率，写到目的地
		FileOutputStream fos = null;
		try {
            /*
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            while(br.readLine()!=null){
                bw.write(br.readLine());
            }
            */
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(targetFile);
			// 边读边写
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}

			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
