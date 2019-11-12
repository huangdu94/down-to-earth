package regex;

public class Test04 {
    public static void main(String[] args) {
        String url = "GET /reg?username=fanchuanqi&password=123456&nickname=fanfan&phonenumber=13544444444 HTTP:/1.1";
        /*
         * 解析该请求内容，
         * 输出：
         * method:GET
         * uri:/reg
         * protocol:HTTP:/1.1
         * 注册用户信息：fanchuanqi,123456,fanfan,13544444444
         * 其中用户信息解析出来后存入一个UserInfo对象中
         * 然后在注册用户信息输出时，输出该对象toString返回的字符串.
         */
        String[] data = url.split("\\s+"); //按照空格把url分成三部分存在suburl中
        String method = data[0];
        String protocol = data[2];

        String[] suburl1 = data[1].split("\\?");//按照?把suburl[1]再分成两部分存在suburl1中
        String uri = suburl1[0];

        String[] suburl11 = suburl1[1].split("&");//按照&把suburl1[1]再分成四部分存在suburl11中
//		String[] suburl110=suburl11[0].split("=");
//		String[] suburl111=suburl11[1].split("=");
//		String[] suburl112=suburl11[2].split("=");
//		String[] suburl113=suburl11[3].split("=");
//		UserInfo user=new UserInfo(suburl110[1],suburl111[1],suburl112[1],suburl113[1]);
        UserInfo user = new UserInfo();
        for (int i = 0; i < suburl11.length; i++) {
            String[] arg = suburl11[i].split("=");
            if (arg[0].equals("username")) {
                user.setUsername(arg[1]);
                continue;
            }
            if (arg[0].equals("password")) {
                user.setPassword(arg[1]);
                continue;
            }
            if (arg[0].equals("nickname")) {
                user.setNickname(arg[1]);
                continue;
            }
            if (arg[0].equals("phonenumber")) {
                user.setPhonenumber(arg[1]);
                continue;
            }
        }
        System.out.println("用户信息：" + user.toString());
        System.err.println(method + protocol + uri);
    }
}

class UserInfo {
    private String username;
    private String password;
    private String nickname;
    private String phonenumber;

    /*
     * 添加构造方法，get，set方法，重写toString方法
     * toString方法返回的字符串格式：
     * username,password,nickname,phonenumber
     * 例如：
     * fanchuanqi,123456,fanfan,13544444444
     */
    public UserInfo() {
    }

    public UserInfo(String username, String password, String nickname, String phonenumber) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String toString() {
        return username + "," + password + "," + nickname + "," + phonenumber;
    }
}