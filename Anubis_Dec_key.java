
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



/* Code to decrypt Chinese tweets for Anubis CC communication*/

public class Anubis_Dec_key{
    
    public static void main(String argv[]){
        try{
            
            // String str = new String(argv[0]);
            String EncryptedText = argv[0];
            String Key = argv[1];
            
            String[] Result = decode(EncryptedText, Key);
            
            System.out.println(Result[0]);
            
            }catch(Exception e ){
                System.out.println(e);
            }
        
        } 
    

    public static String[] decode(String str, String key){
        try{
            decrypt decinstance = new decrypt();
            byte[] byteStr = str.getBytes();
            String[] result = new String[3];
            String ccAddress = new String(decinstance.new inner(key.getBytes()).a(b(new String(Base64.getDecoder().decode(byteStr), "UTF-8"))));
            result[0] = ccAddress;
            result[1] = key;
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