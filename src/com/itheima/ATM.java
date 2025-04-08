package com.itheima;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ATM {
    //私有创建Account对象类型的账户用户列表
    private ArrayList<Account> accounts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Account loginAcc;
    //***启动STM系统，展示欢迎界面*/
    public void start() {
        while (true) {
            System.out.println("欢迎进入到ATM系统");
            System.out.println("1、用户登录");
            System.out.println("2、用户开户");
            System.out.println("请选择：");
            int command = sc.nextInt();
            switch (command) {
                case 1:// 用户登录
                    login();
                    break;
                case 2:// 用户开户
                    creatAccount();
                    break;
                default:
                    System.out.println("没有该操作");
            }
        }
    }

    /**登录用户界面**/
    private void login(){
        System.out.println("===进入用户登录界面===");
        //1、判断 账户信息为0，不允许进入
        if(accounts.size() == 0){
            System.out.println("没有账户，请先创建账户");
            return;
        }
        System.out.println("请输入卡号：");
        String cardId = sc.next();
        //2、账户信息不为0； 根据卡号判断登录，卡号正确输入密码，
        Account acc = getAccountByCardId(cardId);
        if(acc == null){
            System.out.println("您输出的账号不存在");
            return;
        }
        loginAcc = acc;
        while (true) {
            System.out.println("请输入密码");
            String passWord = sc.next();
            if(acc.getPassWord().equals(passWord)){//密码输入正确
                System.out.println("恭喜您成功登录" + acc.getUserName() + "您的卡号是：" +acc.getCardId());
                //展示登录后的界面
                showUserCommand();
                return;
            }else{
                System.out.println("您输入的密码有误，请重新输入：");

            }
        }

    }

    /**展示登录后的操作界面**/
    private void showUserCommand(){
        while(true){
            System.out.println(loginAcc.getUserName() + "您可以选择如下功能进行账户处理===");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、密码修改");
            System.out.println("6、退出");
            System.out.println("7、注销当前账户");
            int command = sc.nextInt();
            switch(command){
                case 1://查询账户
                    showLoginAccount();
                    break;
                case 2://存款
                    depositMoney();
                    break;
                case 3://取款
                    withdrawMoney();
                    break;
                case 4://转账
                    transforMoney();
                    break;
                case 5://修改密码
                    upDataPassword();
                    break;
                case 6://退出
                    System.out.println("成功退出查询账户");
                    return;
                case 7://注销当前账户
                    if(deleteAccount()){//如果真销户
                        //直接退出
                        return;
                    }
                    break;
                default:
                    System.out.println("您当前的选择无效，请确认：");
            }

        }

    }
    /**注销账户**/
    //真要销户后，应该退出登录，所以需要判断是真销户还是假销户
    private boolean deleteAccount(){
        while (true) {
            System.out.println("进入销户，请谨慎！");
            System.out.println("确认销户：y，退出：n");
            char command = sc.next().charAt(0);
            switch(command){
                case 'y':
                    //卡里没有钱才能销户
                    if(loginAcc.getMoney() == 0){
                        accounts.remove(loginAcc);
                        System.out.println("成功销户！");
                        return true;
                    }else{
                        System.out.println("您的账户余额不为0，请取出后，再注销。");
                        return false;
                    }

                case 'n':
                    return false;
                default:
                    System.out.println("您的账户保留！");
                    return false;

            }
        }
    }
    /**修改密码**/
    private void upDataPassword(){
        while (true) {
            System.out.println("请输入新密码");
            String passWord = sc.next();
            System.out.println("请输入确认密码");
            String okPassWord = sc.next();
            if(passWord.equals(okPassWord)) {//两次密码相等
                loginAcc.setPassWord(okPassWord);
                System.out.println("修改密码成功");
                break;
            }else{
                System.out.println("您输入的密码有误，请重新输入：");
            }
        }
    }
    /**转账**/
    private void transforMoney() {
        //1、需要获取账号，判断是否存在
        while (true) {
            System.out.println("请输入对方账户");
            String cardId = sc.next();
            Account transAcc = getAccountByCardId(cardId);
            if(transAcc == null){
                System.out.println("您输入的账户不存在，请您重新输入：");
            }else{//账号存在的，需要判断姓
                System.out.println("请输入对方姓式" + "*" +transAcc.getUserName().substring(1));
                String firstName = sc.next();
                while (true) {
                    if(transAcc.getUserName().substring(0,1).equals(firstName)){//对方用户姓名输入正确
                        //开始转账
                        System.out.println("请输入转账金额：");
                        double money = sc.nextDouble();
                        if(money > loginAcc.getMoney()){
                            System.out.println("您输入的金额超过余额");
                        }else{
                            transAcc.setMoney(transAcc.getMoney() + money);
                            loginAcc.setMoney(loginAcc.getMoney() - money);
                            System.out.println("成功转账" + money);
                            return;
                        }
                    }
                }
            }
        }

        //2、账号存在的情况下，判断名字对不对，名字对，开始转账
    }

    /**存钱**/
    private void depositMoney(){
        System.out.println("请输入存款金额：");
        double money =  sc.nextDouble();
        loginAcc.setMoney(loginAcc.getMoney() + money);
        System.out.println("您的余额为：" + loginAcc.getMoney());
    }

    /**取款**/
    private void withdrawMoney(){
        while (true) {
            System.out.println("请输入取款金额：");
            double money = sc.nextDouble();
            //判断取钱<余额，是，判断限额，小于则进行取钱
            if(money <= loginAcc.getMoney()){
                if(money > loginAcc.getLimit())
                {
                    System.out.println("取款金额大于限额，限额为：" + loginAcc.getLimit());
                    break;
                }else{
                    loginAcc.setMoney(loginAcc.getMoney() - money);
                    System.out.println("成功取款：" + money + "卡内余额为：" + loginAcc.getMoney());
                    return;
                }
            }else{
                System.out.println("余额不足，您的账户余额为：" + loginAcc.getMoney());
            }
        }

    }

    /**查询账户**/
    private void showLoginAccount(){
        System.out.println("卡号：" + loginAcc.getCardId());
        System.out.println("户主：" + loginAcc.getUserName());
        System.out.println("性别：" + loginAcc.getSex());
        System.out.println("余额：" + loginAcc.getMoney());
        System.out.println("每次取现额度：" + loginAcc.getLimit());
    }

    /*** 定义用户操作*/
    private void creatAccount() {
        System.out.println("=======进入开户系统======");
        Account acc = new Account();
        System.out.println("请输入您的账户名称：");
        String name = sc.next();
        acc.setUserName(name);

        while (true) {
            System.out.println("请输入您的性别：");
            char sex = sc.next().charAt(0);
            //判断输入的数据是否有效
            if (sex == '男' || sex == '女') {
                acc.setSex(sex);
                break;
            } else {
                System.out.println("您输入的性别有误");
            }
        }

        //账户密码
        while (true) {
            System.out.println("请输入您的账户密码：");
            String passWord = sc.next();
            System.out.println("请在此输出您的密码：");
            String okPassWord = sc.next();
            if (okPassWord.equals(passWord)) {
                acc.setPassWord(okPassWord);
                break;
            } else {
                System.out.println("您输入的有误，请再次输入");
            }
        }

        //设置取现金额
        System.out.println("请输入您的取现额度：");
        double limit = sc.nextDouble();
        acc.setLimit(limit);
        //生成卡号，并存入
        String newCardId = creatCardId();
        acc.setCardId(newCardId);

        //把账户对象，存放到账户集合中去
        accounts.add(acc);
        System.out.println("恭喜您，" + acc.getUserName() + "开户成功，您的卡号是" + newCardId);
    }

    /**创建生成8位卡号， 而且卡号不能重复**/
    private String creatCardId() {
        Random r = new Random();
        while (true) {
            //生成8位不同的数字
            String cardId = "";
            for (int i = 0; i < 8; i++) {
                cardId += r.nextInt(8);
            }
            //判断cardId的卡号是否和之前的重复
            Account acc = getAccountByCardId(cardId);
            if (acc == null) {//说明不存在，可以返回这个新卡号
                return cardId;
            }//否则重新生成
        }

    }

    /**根据卡号查询账户信息**/
    private Account getAccountByCardId(String cardId) {
        //遍历全部对象，判断卡号是否存在
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            //loginAcc = acc;
            if (acc.getCardId().equals(cardId)) {
                //存在此账户
                return acc;
            }
        }
        return null;
    }
}

