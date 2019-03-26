package com.example.springboot.designpattern.proxy;

/**
 * Description:真实明星类，实现明星接口
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/26
 */
public class RealStar implements Star {

    @Override
    public void sing() {
        System.out.println("明星本人开始唱歌...");
    }
}
