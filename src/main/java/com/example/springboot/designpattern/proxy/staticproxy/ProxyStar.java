package com.example.springboot.designpattern.proxy.staticproxy;

import com.example.springboot.designpattern.proxy.Star;

/**
 * Description:明星的静态代理类，实现明星接口
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class ProxyStar implements Star {

    private Star star;

    public ProxyStar(Star star) {
        this.star = star;
    }

    @Override
    public void sing() {
        System.out.println("明星代理先进行谈判...");
        // 明星本人唱歌
        star.sing();
        System.out.println("明星代理去收钱...");
    }
}
