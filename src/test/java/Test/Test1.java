package Test;


/**
 * @author yuan
 * @date 2018/12/28 0028
 */
public class Test1 {
    /*    public static void main(String[] args) {
            String json="{\n"+
                    "  \"name\": \"李四\",\n"+
                    "  \"age\": 22\n"+
                    "}";
            Map<String, String> value=JSON.parseObject(json, Map.class);
            for (int i=0; i < 100; i++) {
                System.out.println("请输入你需要改变的值");
                Scanner sc=new Scanner(System.in);
                final String s1=sc.nextLine();
                value.put("name", s1);//改变name的值
                final String s=JSON.toJSONString(value);
                System.out.println(s);

            }

        }*/
/*    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("输入x：");
        long x=sc.nextLong();
        System.out.println("输入n：");
        long n=sc.nextLong();
        System.out.println(function1(x, n));
    }

    public static long function(long x, long n) {
        if (n > 0) {
            return x * function(x, n-1);
        }
        return 1;
    }

    public static long function1(long x, long n) {
        long sun=1;
        for (int i=0; i < n; i++) {
            sun=x * sun;
        }
        return sun;
    }*/

}