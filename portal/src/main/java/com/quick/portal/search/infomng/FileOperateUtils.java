package com.quick.portal.search.infomng;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * @author
 * @date
 */
public class FileOperateUtils {
	private static final String REALNAME = "realName";
	private static final String STORENAME = "storeName";
	private static final String SIZE = "size";
	private static final String SUFFIX = "suffix";
	private static final String CONTENTTYPE = "contentType";
	private static final String CREATETIME = "createTime";
	private static final String UPLOADDIR = "uploadDir/";

	/**
	 * 将上传的文件进行重命名
	 * 
	 * @param name
	 * @return
	 */
	private static String rename(String name) {

		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;

		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}

		return fileName;
	}

	/**
	 * 压缩后的文件名
	 * 
	 * @param name
	 * @return
	 */
	private static String zipName(String name) {
		String prefix = "";
		if (name.indexOf(".") != -1) {
			prefix = name.substring(0, name.lastIndexOf("."));
		} else {
			prefix = name;
		}
		return prefix + ".zip";
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param params
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> upload(HttpServletRequest request,
			String[] params, Map<String, Object[]> values) throws Exception {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();

		String uploadDir = request.getSession().getServletContext()
				.getRealPath("/")
				+ FileOperateUtils.UPLOADDIR;
		File file = new File(uploadDir);

		if (!file.exists()) {
			file.mkdir();
		}

		String fileName = null;
		int i = 0;
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
				.iterator(); it.hasNext(); i++) {

			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();

			fileName = mFile.getOriginalFilename();

			String storeName = rename(fileName);

			String noZipName = uploadDir + storeName;
			String zipName = zipName(noZipName);

			// 上传成为压缩文件
			ZipOutputStream outputStream = new ZipOutputStream(
					new BufferedOutputStream(new FileOutputStream(zipName)));
			outputStream.putNextEntry(new ZipEntry(fileName));
			outputStream.setEncoding("GBK");

			FileCopyUtils.copy(mFile.getInputStream(), outputStream);

			Map<String, Object> map = new HashMap<String, Object>();
			// 固定参数值对
			map.put(FileOperateUtils.REALNAME, zipName(fileName));
			map.put(FileOperateUtils.STORENAME, zipName(storeName));
			map.put(FileOperateUtils.SIZE, new File(zipName).length());
			map.put(FileOperateUtils.SUFFIX, "zip");
			map.put(FileOperateUtils.CONTENTTYPE, "application/octet-stream");
			map.put(FileOperateUtils.CREATETIME, new Date());

			// 自定义参数值对
			for (String param : params) {
				map.put(param, values.get(param)[i]);
			}

			result.add(map);
		}
		return result;
	}

	/**
	 * 下载
	 *
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 *             storeName = "201205051340364510870879724.zip"; realName =
	 *             "Java设计模式.zip"; contentType = "application/octet-stream";
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ FileOperateUtils.UPLOADDIR;
		String downLoadPath = ctxPath + storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

	public static void download(HttpServletRequest request,
			HttpServletResponse response, String filePath) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String downLoadPath = filePath;
		File file = new File(downLoadPath);
		if (!file.exists()) {
			throw new Exception("文件路径不存在，请检查文件路径！不存在文件路径是"+downLoadPath);
		}
		
		// 取得文件名。
		String filename = file.getName();
		long fileLength = file.length();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

	public static void doCompress(String srcFile, String zipFile)
			throws IOException {
		doCompress(new File(srcFile), new File(zipFile));
	}

	/**
	 * 文件压缩
	 * 
	 * @param srcFile
	 *            目录或者单个文件
	 * @param zipFile
	 *            压缩后的ZIP文件
	 */
	public static void doCompress(File srcFile, File zipFile)
			throws IOException {
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipFile));
			doCompress(srcFile, out);
		} catch (Exception e) {
			throw e;
		} finally {
			out.close();// 记得关闭资源
		}
	}

	public static void doCompress(String filelName, ZipOutputStream out)
			throws IOException {
		doCompress(new File(filelName), out);
	}

	public static void doCompress(File file, ZipOutputStream out)
			throws IOException {
		doCompress(file, out, "");
	}

	public static void doCompress(File inFile, ZipOutputStream out, String dir)
			throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			if (files != null && files.length > 0) {
				for (File file : files) {
					String name = inFile.getName();
					if (!"".equals(dir)) {
						name = dir + "/" + name;
					}
					FileOperateUtils.doCompress(file, out, name);
				}
			}
		} else {
			FileOperateUtils.doZip(inFile, out, dir);
		}
	}

	public static void doZip(File inFile, ZipOutputStream out, String dir)
			throws IOException {
		String entryName = null;
		if (!"".equals(dir)) {
			entryName = dir + "/" + inFile.getName();
		} else {
			entryName = inFile.getName();
		}
		ZipEntry entry = new ZipEntry(entryName);
		out.putNextEntry(entry);

		int len = 0;
		byte[] buffer = new byte[1024];
		FileInputStream fis = new FileInputStream(inFile);
		while ((len = fis.read(buffer)) > 0) {
			out.write(buffer, 0, len);
			out.flush();
		}
		out.closeEntry();
		fis.close();
	}

	public static void main(String[] args) throws IOException {
		doCompress("D:/java/", "D:/java.zip");
	}

	public static void download(HttpServletRequest request,
			HttpServletResponse response, String tFilePath, String aFilePath)
			throws Exception {
		// 文件名称
		String tFileName = new File(tFilePath).getName();
		String aFileName = new File(aFilePath).getName();
		String[] names = { tFileName, aFileName };
		File tFile = new File(tFilePath);
		if (!tFile.exists()) {
			throw new Exception("文件路径不存在，请检查文件路径！不存在文件路径是"+tFilePath);
		}
		
		File aFile = new File(aFilePath);
		if (!aFile.exists()) {
			throw new Exception("文件路径不存在，请检查文件路径！不存在文件路径是"+aFilePath);
		}
		
		
		// 四个文件流
		FileInputStream input1 = new FileInputStream(new File(tFilePath));
		FileInputStream input2 = new FileInputStream(new File(aFilePath));
		FileInputStream[] inputs = { input1, input2 };
		String date = DateUtil.getDateStrByFormat("yyyyMMddHHmmss");
		String outFileName = date.concat(".zip");
		String fileDic = System.getProperty("java.io.tmpdir");

		if (!fileDic.endsWith(File.separator)) {
			fileDic = fileDic + File.separator;
		}

		File tmpFile = new File(fileDic);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		String zipPath = fileDic.concat("/").concat(outFileName);
		File tmpZipFile = new File(zipPath);
		if (!tmpZipFile.exists())
			tmpZipFile.createNewFile();
		byte[] buf = new byte[1024];
		int len;
		ZipOutputStream zout = new ZipOutputStream(
				new FileOutputStream(zipPath));
		for (int i = 0; i < inputs.length; i++) {
			FileInputStream in = inputs[i];
			zout.putNextEntry(new ZipEntry(names[i]));
			while ((len = in.read(buf)) > 0) {
				zout.write(buf, 0, len);
			}
			zout.closeEntry();
			in.close();
		}
		zout.close();

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		FileInputStream zipInput = new FileInputStream(zipPath);
		OutputStream out = response.getOutputStream();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ outFileName);
		while ((len = zipInput.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		zipInput.close();
		out.flush();
		out.close();
		// 删除压缩包
		tmpZipFile.delete();
	}

	/**
	 * 压缩并导出文件
	 * 
	 * @param zipPath
	 *            压缩文件临时路径 路径最后不要有 /
	 * @param zipName
	 *            压缩为文件名 **.zip
	 * @param createFilesPath
	 *            需要压缩的文件列表
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static boolean downloadZip(String zipPath, String zipName,
			List<String> createFilesPath, HttpServletRequest request,
			HttpServletResponse response) {
		byte[] buffer = new byte[1024];
		String strZipPath = zipPath + "/" + zipName;
		try {
			File tmpZip = new File(zipPath);
			if (!tmpZip.exists())
				tmpZip.mkdirs();
			File tmpZipFile = new File(strZipPath);
			if (!tmpZipFile.exists())
				tmpZipFile.createNewFile();

			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					strZipPath));
			// 需要同时下载的两个文件result.txt ，source.txt

			File[] file1 = new File[createFilesPath.size()];

			for (int i = 0; i < createFilesPath.size(); i++) {
				file1[i] = new File(createFilesPath.get(i));
			}
			for (int i = 0; i < file1.length; i++) {
				FileInputStream fis = new FileInputStream(file1[i]);
				out.putNextEntry(new ZipEntry(file1[i].getName()));
				// 设置压缩文件内的字符编码，不然会变成乱码
				// out.setEncoding("UTF-8");
				int len;
				// 读入需要下载的文件的内容，打包到zip文件
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
			out.close();
			downloadFile(zipPath, zipName, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 以压缩文件导出
	 * 
	 * @param fileName
	 * @param filePath
	 * @param response
	 */
	public static void downloadFile(String filePath, String fileName,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			File file = new File(filePath, fileName);
			// 以流的形式下载文件。
			BufferedInputStream fis = new BufferedInputStream(
					new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
