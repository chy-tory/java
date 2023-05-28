package com.nanshan.one.three;



import java.math.BigInteger;
        import java.util.Random;
        import java.util.Scanner;

public class millionaire{

    public static void main(String[] args) throws Exception {

        // 输入a b 的财富
        System.out.print("A:");
        int i = new Scanner(System.in).nextInt();
        System.out.print("B:");
        int j = new Scanner(System.in).nextInt();

        // 创建一个随机数
        int x;
        do {
            x = new Random().nextInt(90, 190) + 10;
        } while (is_prime(x));
        System.out.println("随机整数x="+x);

        // 随机创建公钥私钥
        int [] pbk, pvk;
        int [][] r = create_key();
        pbk = r[0];
        pvk = r[1];
        System.out.println("公钥(n,e)=("+pbk[0]+","+pbk[1]+")\n"+ "私钥(n,d)=("+pvk[0]+","+pvk[1]+")");


        // A发送一个大整数给b，并计算
        int C = encrypt(x, pbk);
        System.out.println("A发送C-i="+(C - i)+"给B");
        int [] Y = new int[100];
        for(int u = 1; u<101;u++){
            Y[u - 1] = decrypt(C-i+u,pvk);
        }
        System.out.println("Y="+ toString(Y));

        // a b分别计算
        int p = new Random().nextInt(x - 100) + 10;
        int [] Z = new int[100];
        while (true) {
            for(int u = 0; u<100;u++){
                Z[u] = Y[u] % p;
            }
            if(max(Z) < p -1 &&min(Z)>0){
                break;
            }
            p = new Random().nextInt(x - 11) + 10;
        }
        System.out.println("p="+p+"\nZ="+toString(Z));
        for(int u=0;u<100;u++){
            if(u>=j-1){
                Z[u] = Z[u] + 1;
            }
        }
        System.out.println("k="+toString(Z));

        // 判断
        if(Z[i-1] == x % p){
            if (i<j){
                System.out.println("B更富有");
            }else {
                System.out.println("验证错误，i应该大于j，Alice可能更富有，也可能和Bob一样富有");
            }
        }else {
            if (i >= j){
                System.out.println("Alice可能更富有，也可能和Bob一样富有");
            }else {
                System.out.println("验证错误，j应该大于i，Bob更富有才对");
            }
        }
    }

    // 变成字符串
    public static StringBuffer toString(int []d){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ ");
        for (int j : d) {
            stringBuffer.append(j);
            stringBuffer.append(" ");
        }
        stringBuffer.append(" ]");
        return stringBuffer;
    }


    public static int gcd(int n, int m){
        int z;
        while (m%n != 0){
            z = m%n;
            m = n;
            n = z;
        }
        return n;
    }

    public static int max(int []d){
        int m = d[0];
        for (int i = 1; i<d.length;i++){
            if(m<d[i]){
                m = d[i];
            }
        }
        return m;
    }

    public static int min(int []d){
        int m = d[0];
        for (int i = 1; i<d.length;i++){
            if(m>d[i]){
                m = d[i];
            }
        }
        return m;
    }

    public static boolean is_prime(int x) throws Exception {
        if (x <= 1) {
            throw new Exception("0和1既不是素数也不是合数，x应为大于1的正整数");
        }
        for(int i = 2; i < (int) (Math.sqrt(x) + 1); i++) {
            if(x % i == 0) {
                return true;
            }
        }
        return false;
    }

    public static int[] get_inverse(int a, int b){
//        a*x = 1 mod b
//        b*y = 1 mod a
//        已知a、b，返回x, y
        if (b == 0) {
            return new int[]{1, 0};
        } else {
            int [] r = get_inverse(b, a % b);
            int x1, x2, y1, y2;
            x2 = r[0];
            y2 = r[1];
            x1 = r[1];
            y1 = x2 - (a / b) * y2;
            return new int[]{x1, y1};
        }
    }

    public static int [][] create_key() throws Exception {
//        创建公钥和私钥
        int p, q, n, s;
        do {
            p = new Random().nextInt(100, 190) + 10;
        } while (is_prime(p));
        do {
            q = new Random().nextInt(100, 190) + 10;
        } while (is_prime(q) || q == p);


        n = p * q;
        s = (p - 1)*(q - 1);
        int d,e;
        System.out.println("n="+n+",s="+s);
        while (true) {
            e = new Random().nextInt(s - 3) + 2;
            if (gcd(e, s) == 1){
                d = get_inverse(e,s)[0];
                if (d>0){
                    break;
                }
            }
        }
        return new int[][]{{n,e},{n,d}};
    }

    public static int encrypt(int content, int [] pbkey){
        BigInteger c = new BigInteger(String.valueOf(content));
        BigInteger p0 = new BigInteger(String.valueOf(pbkey[0]));
        return (c.pow(pbkey[1]).mod(p0)).intValue();
//        return (int)Math.pow(content, pbkey[1]) % pbkey[0];
    }

    public static int decrypt(int encrypt_content, int [] pvkey){
        BigInteger c = new BigInteger(String.valueOf(encrypt_content));
        BigInteger p0 = new BigInteger(String.valueOf(pvkey[0]));
        return (c.pow(pvkey[1]).mod(p0)).intValue();
//        return (int)Math.pow(encrypt_content, pvkey[1]) % pvkey[0];
    }

}