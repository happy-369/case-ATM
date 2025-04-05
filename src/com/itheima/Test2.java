package com.itheima;
import java.util.Random;


public class Test2 {
    public static void main(String[] arg){
        System.out.println(creatcode(5));
    }
    public static String creatcode(int n){
        //创建随机生成器
        Random r = new Random();
        //存储随机码
        String code = "";
        //for循环用于产生多少个随机字符
        for(int i = 0; i <= n; i++){
            //每一个是数字、大写字母、小写字母随机switch
            //判断类型
            int type = r.nextInt(3);//0、1、2随机产生
            switch(type){
                case 0://数字
                    code += r.nextInt(10);
                    break;
                case 1://小写字母 a 97, z 97+25
                    code += (char) (97 + r.nextInt(26));
                    break;
                case 2://大写字母 A 65, Z 65+25
                    code += (char) (65 + r.nextInt(26));
                    break;
            }

        }
        return code;
    }
}
