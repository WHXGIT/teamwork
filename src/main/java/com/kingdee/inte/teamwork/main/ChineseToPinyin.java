package com.kingdee.inte.teamwork.main;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 使用pinyin4j
 *
 * @author Administrator
 * @date 2020/9/15 17:22
 */
public class ChineseToPinyin {

	public static void main(String[] args) {
		String filePath = "E:\\test\\county.txt";
		File file = new File(filePath);
		List<String> chineseList = getAllLines(file);
		List<String> pinyinList = new ArrayList<>();
		for (String str : chineseList) {
			String pinyin = toPinyin(str);
			pinyinList.add(pinyin);
		}

		write("E:\\test", "county-pinyin.txt", pinyinList);
		System.out.println("完成");
	}

	public static String toPinyin(String chinese) {

		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		String result = "";
		try {
			for (int i = 0; i < chinese.length(); i++) {
				String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), defaultFormat);
				String pin = "";
				if (pinyins != null) {
					pin = pinyins[0];
				} else {
					pin = String.valueOf(chinese.charAt(i));
				}
				result += pin;
			}
			result = result.replaceFirst(String.valueOf(result.charAt(0)), String.valueOf(result.charAt(0)).toUpperCase());
		} catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
			badHanyuPinyinOutputFormatCombination.printStackTrace();
		}
		return result;
	}

	public static void write(String path, String filename, List<String> lines) {
		// 相对路径，如果没有则要建立一个新的output。txt文件
		File writename = new File(path + File.separator + filename);
		BufferedWriter out = null;
		try {
			if (!writename.exists()) {
				writename.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(writename));
			for (String line : lines) {
				out.write(line);
				out.write("\r\n");
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<String> getAllLines(File file) {
		List<String> lineList = new ArrayList<>();
		try {
			BufferedReader textFile = new BufferedReader(new FileReader(file));
			String lineDta = "";

			//第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
			while ((lineDta = textFile.readLine()) != null) {
				lineList.add(lineDta.trim());
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有找到指定文件");
		} catch (IOException e) {
			System.out.println("文件读写出错");
		}
		return lineList;
	}
}
