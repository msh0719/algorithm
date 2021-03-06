package BaekJoon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 14888 연산자 끼워넣기
 */

public class B_14888 {

    static int N;
    static int[] arr;
    static int[] cal;
    static boolean[][] visit;
    static int max;
    static int min;
    static ArrayList<String> list = new ArrayList<>();

    public static void Calculation(int num, int index, int sum, int sub, int mul, int div){
        if(index == N){
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }

        if(sum < cal[0]){
            list.add("+");
            list.add(Integer.toString(arr[index]));
            Calculation(num+arr[index], index+1, sum+1, sub, mul, div);
        }
        if(sub < cal[1]){
            Calculation(num-arr[index], index+1, sum, sub+1, mul, div);
        }
        if(mul < cal[2]){
            Calculation(num*arr[index], index+1, sum, sub, mul+1, div);
        }
        if(div < cal[3]){
            Calculation(num/arr[index], index+1, sum, sub, mul, div+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visit = new boolean[N-1][1<<4];
        cal = new int[4]; //sum, sub, mul, div
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            cal[i] = Integer.parseInt(st.nextToken());
        }
        list = new ArrayList<>();
        Calculation(arr[0],1,0,0,0,0);

        bw.write(max + "\n");
        bw.write(min + "\n");

        bw.flush();
        bw.close();
        br.close();

    }
}
