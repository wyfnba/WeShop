package cn.edu.guet.WeShop.controller;

import cn.edu.guet.WeShop.bean.*;
import cn.edu.guet.WeShop.service.OrderService;
import cn.edu.guet.WeShop.service.impl.OrderServiceImpl;
import cn.edu.guet.WeShop.ui.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.edu.guet.WeShop.ui.Login.user_id;
import static cn.edu.guet.WeShop.ui.ShoppingCart.price;

/**
 * @Author liwei
 * @Date 2021-08-04 11:28
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/auth")
    public String auth(@RequestParam("code") String code) {
        log.info("获取code");
        log.info("code : " + code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd9a46e74fc279fcc&secret=7deb521448e11ddbc163fca849648198&code=" + code + "&grant_type=authorization_code";
        String response = restTemplate.getForObject(url, String.class);
        log.info("response = {}", response);
        return response;
    }

    @PostMapping("/result")
    public String result(HttpServletRequest request, HttpServletResponse res) {

        String ip = request.getRemoteAddr();
        System.out.println("IP地址：" + ip);
        BufferedReader reader = null;
        try {
            reader = request.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
        StringBuffer inputString = new StringBuffer();

        try {
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            request.getReader().close();
            String response = inputString.toString();//接收的回调结果（支付成功后，腾讯调用我们的接口，并发数据给我们）
            String pattern =
                    ".+<mch_id><!\\[CDATA\\[(\\d{10}).+" +
                            "<out_trade_no><!\\[CDATA\\[(.{32}).+" +
                            "<time_end><!\\[CDATA\\[(\\d{14}).+" +
                            "<transaction_id><!\\[CDATA\\[(\\d{28})";
            Pattern r = Pattern.compile(pattern);
            // 现在创建 matcher 对象
            Matcher m = r.matcher(response);
            if (m.find()) {
                String mch_id = m.group(1);
                System.out.println(mch_id);
                String orderNo = m.group(2);
                String orderTime = m.group(3);
                System.out.println(orderTime);
                String transactionId = m.group(4);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                //Timestamp timestamp = new Timestamp(sdf.parse(orderTime).getTime());

                Orderbase orderbase=new Orderbase(Integer.parseInt(mch_id),orderNo,transactionId,user_id,price);
                System.out.println(orderbase.getId());
                List<Orderdetail> orderdetailList= ShoppingCart.getOrderdetailList();
                for (int i=0;i<orderdetailList.size();i++){
                    orderdetailList.get(i).setOrderbase_id(orderbase.getId());
                }
                OrderService orderService=new OrderServiceImpl();
                orderService.addOrder(orderbase,orderdetailList);

                /*
                Order order = new Order();
                String id = UUID.randomUUID().toString().replace("-", "");
                System.out.println(id);
                order.setId(id);//会自动生成32个字符
                order.setMch_id(Integer.parseInt(mch_id));
                order.setOut_trade_no(orderNo);
                order.setOrder_time(timestamp);
                order.setTransaction_id(transactionId);
                order.setUserId(0);
                order.setItem_id(11111);
                order.setItem_price(18.00f);
                order.setAmount(1);
                order.setOrder_price(18.00f);

                OrderService orderService = new OrderServiceImpl();

                orderService.newOrder(order, item_id, item_amount);
                */



                /*
                发信息给微信，告知微信官方，已收到通知
                 */
                String result = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" +
                        "<return_msg><![CDATA[OK]]></return_msg>" +
                        "</xml>";
                BufferedOutputStream out = new BufferedOutputStream(
                        res.getOutputStream());
                out.write(result.getBytes());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
