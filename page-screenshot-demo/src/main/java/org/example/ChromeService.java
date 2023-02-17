package org.example;

import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.net.InetSocketAddress;

@Slf4j
@Service
public class ChromeService implements InitializingBean, DisposableBean {

    private String chromeDriverPath;

    private ChromeDriver chromeDriver;

    private HttpServer httpServer;


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(this.chromeDriverPath, "chrome driver path is empty");
        log.info("chrome driver path: {}", this.chromeDriverPath);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--disable-gpu", "--no-sandbox");
        this.chromeDriver = new ChromeDriver(chromeOptions);

        this.httpServer = HttpServer.create(new InetSocketAddress(10080), 0);
        this.httpServer.start();
    }

    @Override
    public void destroy() throws Exception {
        this.chromeDriver.close();
        this.httpServer.stop(10);
    }
}
