package com.kingdee.inte.teamwork.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * description:
 *
 * @author Administrator
 * @date 2020/9/15 15:32
 */
@RestController
@RequestMapping("/v1/videos")
public class VideoController {
	/**
	 * 获取视频流
	 *
	 * @param response
	 * @return
	 * @author xWang
	 * @Date 2020-05-20
	 */
	@RequestMapping("/video/1")
	public void getVideo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("**********************log**************************");

		//视频资源存储信息
		String videoPath = "C:\\Users\\Administrator\\Videos\\Captures\\地图地址demo.mp4";
		response.reset();
		//获取从那个字节开始读取文件
		String rangeString = request.getHeader("Range");

		try {
			//获取响应的输出流
			OutputStream outputStream = response.getOutputStream();
			File file = new File(videoPath);
			if (file.exists()) {
				RandomAccessFile targetFile = new RandomAccessFile(file, "r");
				long fileLength = targetFile.length();
				//播放
				if (StringUtils.isNotBlank(rangeString)) {
					long range = Long.parseLong(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
					//设置内容类型
					response.setHeader("Content-Type", "video/mp4");
					//设置此次相应返回的数据长度
					response.setHeader("Content-Length", String.valueOf(fileLength - range));
					//设置此次相应返回的数据范围
					response.setHeader("Content-Range", "bytes " + range + "-" + (fileLength - 1) + "/" + fileLength);
					//返回码需要为206，而不是200
					response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
					//设定文件读取开始位置（以字节为单位）
					targetFile.seek(range);
				} else {//下载

					//设置响应头，把文件名字设置好
					response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
					//设置文件长度
					response.setHeader("Content-Length", String.valueOf(fileLength));
					//解决编码问题
					response.setHeader("Content-Type", "application/octet-stream");
				}


				byte[] cache = new byte[1024 * 300];
				int flag;
				while ((flag = targetFile.read(cache)) != -1) {
					outputStream.write(cache, 0, flag);
				}
			} else {
				String message = "file:" + file.getName() + " not exists";
				//解决编码问题
				response.setHeader("Content-Type", "application/json");
				outputStream.write(message.getBytes(StandardCharsets.UTF_8));
			}

			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
