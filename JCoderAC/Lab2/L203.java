package JCoderAC.Lab2;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L203 {
    public static void main(String[] args) {
        FastReader fastReader = new FastReader(System.in);
        FastWriter fastWriter = new FastWriter(System.out);

        int n = fastReader.nextInt();
        long k = fastReader.nextLong();
        long sum = 0;

        long[] height_counts = new long[n];
        for (int i = 0; i < n; i++) {
            height_counts[i] = fastReader.nextLong();
            sum += height_counts[i];
        }

        long low=0,high=sum,mid_per=0;

        long ans=0;
        while (low<=high){
            mid_per=(low+high)/2;
            if(checkIsValid(height_counts,k,mid_per)){
                low=mid_per+1;
                ans=mid_per;
            }else {
                high=mid_per-1;
            }
        }
        //System.out.println("mid_per = " + mid_per);
        fastWriter.println(ans*k);

        fastReader.close();
        fastWriter.close();
    }
    private static boolean checkIsValid(long[]heights,long k,long perLine) {
        long[]height_counts=heights.clone();
        long counts=0;
        //System.out.println("perLine = " + perLine);
        for (int i = 0; i < height_counts.length;) {
            //System.out.println("i:"+i+",Arrays.toString(height_counts) = " + Arrays.toString(height_counts));
            if(i!= height_counts.length-1){
                if(height_counts[i]+height_counts[i+1]>=perLine){
                    if(height_counts[i]>=perLine){
                        counts+=height_counts[i]/perLine;
                        height_counts[i]%=perLine;
                    }else {
                        counts+=1;
                        height_counts[i+1]-=perLine-height_counts[i];
                        height_counts[i]=0;
                        i++;
                    }
                }else {
                    i++;
                }
            }else {
                counts+=height_counts[i]/perLine;
                height_counts[i]%=perLine;
                i++;
            }
        }
        //System.out.println("(counts>=k) = " + (counts>=k));
        return counts>=k;
    }

    private static class FastReader implements Closeable{
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in), 16384);
            eat("");
        }

        private void eat(String s) {
            st = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while(!st.hasMoreTokens()) {
                String s = nextLine();
                if(s==null) return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean(){
            return Boolean.parseBoolean(next());
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat(){
            return Float.parseFloat(next());
        }

        public double nextDouble(){
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger(){
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal(){
            return new BigDecimal(next());
        }

        public void close(){
            try{
                st=null;
                br.close();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    private static class FastWriter implements Closeable{
        private final PrintWriter writer;

        public FastWriter(OutputStream out){
            this.writer=new PrintWriter(out);
        }

        public void print(Object object){
            writer.write(object.toString());
        }

        public void printf(String format,Object... os){
            writer.write(String.format(format,os));
        }

        public void println(){
            writer.write(System.lineSeparator());
        }

        public void println(Object object){
            writer.write(object.toString());
            writer.write(System.lineSeparator());
        }

        @Override
        public void close() {
            writer.flush();
            writer.close();
        }
    }
}
