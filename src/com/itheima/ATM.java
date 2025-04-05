package com.itheima;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ATM {
    //私有创建Account对象类型的账户用户列表
    private ArrayList<Account> accounts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    //***启动STM系统，展示欢迎界面*/
    public void start(){
        System.out.println("欢迎进入到ATM系统");
        System.out.println("1、用户登录");
        System.out.println("2、用户开户");
        System.out.println("请选择：");
        int command = sc.nextInt();
        switch(command){
            case 1:// 用户登录
                break;
            case 2:// 用户开户
                creatAccount();
                break;
            default:
                System.out.println("没有该操作");
        }
    }
    /**定义用户操作*/
    private void creatAccount(){
        System.out.println("=======进入开户系统======");
        Account acc = new Account();
        System.out.println("请输入您的账户名称：");
        String name = sc.next();
        acc.setUserName(name);

        while (true) {
            System.out.println("请输入您的性别：");
            char sex = sc.next().charAt(0);
            //判断输入的数据是否有效
            if(sex == '男' || sex == '女') {
                acc.setSex(sex);
                break;
            }else{
                System.out.println("您输入的性别有误");
            }
        }

        //账户密码
        while (true) {
            System.out.println("请输入您的账户密码：");
            String passWord = sc.next();
            System.out.println("请在此输出您的密码：");
            String okPassWord = sc.next();
            if(okPassWord.equals(passWord)){
                acc.setPassWord(okPassWord);
                break;
            }else{
                System.out.println("您输入的有误，请再次输入");
            }
        }

        //设置取现金额
        System.out.println("请输入您的取现额度：");
        double limit = sc.nextDouble();
        acc.setLimit(limit);
        //生成卡号

        //把账户对象，存放到账户集合中去
        accounts.add(acc);
        System.out.println("恭喜您，" + acc.getUserName() + "开户成功，您的卡号是");
    }

    /*创建生成8位卡号， 而且卡号不能重复*/
    private String creatCardId(){
        //生成8位不同的数字
        String cardId = "";
        Random r = new Random();
        for(int i = 0; i < 8; i++){
            cardId += r.nextInt(8);
        }
        //判断cardId的卡号是否和之前的重复

    }

    /*根据卡号查询账户信息*/
    private Account getAccountByCardId(String cardId){
        //遍历全部对象，判断卡号是否存在
        for(int i = 0; i < accounts.size(); i++){
            Account acc = accounts.get(i);
            if(acc.getCardId().equals(cardId)){
                //存在此账户
                return acc;
            }
            return null;
        }
    }
}
