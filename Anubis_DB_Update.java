
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import anubis.AnubisDB;

/* Code to decrypt twitter CC communication for Anubis */

public class Anubis_DB_Update{
    public static final String[] s = new String[]{"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M", "q", "w", "e", "r", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "=", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String[] t = new String[]{"\u9700", "\u8981", "\u610f", "\u5728", "\u4e2d", "\u5e76", "\u6ca1", "\u6709", "\u4e2a", "\u6982", "\u5ff5", "\u5c0f", "\u8bed", "\u62fc", "\u4ea1", "\u53ca", "\u6ce8", "\u9c9c", "\u65b0", "\u6b7b", "\u4e4b", "\u7c7b", "\u963f", "\u52aa", "\u6bd4", "\u62c9", "\u4e01", "\u5316", "\u4f53", "\u7cfb", "\u90fd", "\u53ea", "\u65af", "\u4e00", "\u5957", "\u7528", "\u6076", "\u4ef6", "\u6765", "\u6807", "\u97f3", "\u7684", "\u7b26", "\u53f7", "\u800c", "\u4e0d", "\u662f", "\u5b57", "\u6bcd", "\u5bc2", "\u5bde", "\u808f", "\u4f60", "\u5988", "\u5c44", "\u5f15", "\u811a", "\u5438", "\u5458", "\u4f1a", "\u818f", "\u836f"};
    public static final String keyFile = "data/keys.dat";
    public static final String twitterFile = "data/twitter_acc.dat";

    public static void main(String argv[]){
        try{
            HttpURLConnection a;
            BufferedReader b;
            String c;
            String[] keys = readLines(keyFile);
            String[] twitterCC = readLines(twitterFile);
            
            AnubisDB.createDB();

            for (int j=0; j < twitterCC.length; j++){
                a = (HttpURLConnection) new URL(twitterCC[j]).openConnection();
                a.setRequestMethod("GET");
                a.connect();
                InputStream inputStream = a.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                b = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    String readLine = b.readLine();
                    if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    }
                    //System.out.println(stringBuffer.toString());
                    c = stringBuffer.toString().replace(" ", "");
                    
                    c = f(c, "苏尔的开始", "苏尔苏尔完");
                    int i = 0;
                    while (true) {
                        
                        if (i >= s.length) {
                            break;
                        }
                        String str = c;
                        CharSequence charSequence = t[i];
                        c = str.replace(charSequence, s[i]);
                        i++;
                    }
                
                    // String str = new String(argv[0]);
                    String[] result = decode(c, keys);
                    //System.out.println(twitterCC[j] + " -> " + result[0] + " (" + result[1] + ")");
                    if (result[0] != null){
                        System.out.println(twitterCC[j] + " -> " + result[0] + " (" + result[1] + ")");
                        AnubisDB.insertEntry(result[0], result[1], twitterCC[j]);
                    }
                }
            }catch(Exception e ){
                System.out.println(e);
            }
        
        } 
    

        public static String[] readLines(String filename) throws IOException{
            FileReader fileReader = new FileReader(filename);      
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<String>();
            String line = null;
             
            while ((line = bufferedReader.readLine()) != null){
                lines.add(line);
            }
            bufferedReader.close();
            return lines.toArray(new String[lines.size()]);
        }  

    public static String[] decode(String str, String[] keys){
        try{
            Anubis_DB_Update decinstance = new Anubis_DB_Update();
            byte[] byteStr = str.getBytes();
            String[] result = new String[3];
            for (int i=0;i<keys.length;i++){    
               String ccAddress = new String(decinstance.new inner(keys[i].getBytes()).a(b(new String(Base64.getDecoder().decode(byteStr), "UTF-8"))));
               if(ccAddress.contains("http")){     
                    result[0] = ccAddress;
                    result[1] = keys[i];
                    break;
               }
            }
            return result;
        }catch(Exception e){
            String[] result = new String[2];
            result[0] = "error";
            result[1] = e.getMessage();
            return result;
        }
    }
    public static String f(String str, String str2, String str3) {
        try {
            return str.substring(str.indexOf(str2) + str2.length(), str.indexOf(str3));
        } catch (Exception unused) {
            return "";
        }
    }

    
    public static byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public class inner{
        private int[] a;
        private int b = 0;
        private int c = 0;

    public inner(byte[] bArr) {
        this.a = c(bArr);
    }

    private void a(int i, int i2, int[] iArr) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    private int[] c(byte[] bArr) {
        int i;
        int[] iArr = new int[256];
        for (i = 0; i < 256; i++) {
            iArr[i] = i;
        }
        i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            i = (((i + iArr[i2]) + bArr[i2 % bArr.length]) + 256) % 256;
            a(i2, i, iArr);
        }
        return iArr;
    }

    public byte[] a(byte[] bArr) {
        return b(bArr);
    }

    public byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            this.b = (this.b + 1) % 256;
            this.c = (this.c + this.a[this.b]) % 256;
            a(this.b, this.c, this.a);
            bArr2[i] = (byte) (this.a[(this.a[this.b] + this.a[this.c]) % 256] ^ bArr[i]);
        }
        return bArr2;
    }
    }
}