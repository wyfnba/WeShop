package cn.edu.guet.WeShop;

import cn.edu.guet.WeShop.pay.WXPay;
import cn.edu.guet.WeShop.ui.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WeappdemoApplication {

    public static void main(String[] args) {
        new Login();
        SpringApplicationBuilder builder = new SpringApplicationBuilder(WeappdemoApplication.class);
        builder.headless(false).run(args);
    }

}
