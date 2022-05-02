package cn.edu.guet.WeShop.service;

import cn.edu.guet.WeShop.bean.User;

public interface UserService {
    void addUser(User user);
    void deleteUser(String username);
}
