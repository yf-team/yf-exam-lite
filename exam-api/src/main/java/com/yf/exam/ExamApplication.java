package com.yf.exam;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 云帆在线考试系统
 * @author bool
 * @email 18365918@qq.com
 * @date 2020-03-04 19:41
 */
@Log4j2
@SpringBootApplication
public class ExamApplication implements WebMvcConfigurer {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = SpringApplication.run(ExamApplication.class, args);
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path");

		// 未配置默认空白
		if(path == null){
			path = "";
		}


		log.info("\n----------------------------------------------------------\n\t" +
				"云帆考试系统启动成功，访问路径如下:\n\t" +
				"本地路径: \t\thttp://localhost:" + port + path + "/\n\t" +
				"网络地址: \thttp://" + ip + ":" + port + path + "/\n\t" +
				"API文档: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
				"----------------------------------------------------------");
	}

}