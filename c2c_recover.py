import urllib.request
import re
import base64
key_file = "keys.dat"
twitter_file = "twitter_acc.dat"

translation_dict ={"需":"Q","要":"W","意":"E","在":"R","中":"T","并":"Y","没":"U","有":"I","个":"O","概":"P","念":"A","小":"S","语":"D","拼":"F","亡":"G","及":"H","注":"J","鲜":"K","新":"L","死":"Z","之":"X","类":"C","阿":"V","努":"B","比":"N","拉":"M","丁":"q","化":"w","体":"e","系":"r","都":"y","只":"u","斯":"i","一":"o","套":"p","用":"a","恶":"s","件":"d","来":"f","标":"g","音":"h","的":"j","符":"k","号":"l","而":"z","不":"x","是":"c","字":"v","母":"b","寂":"n","寞":"m","肏":"=","你":"0","妈":"1","屄":"2","引":"3","脚":"4","吸":"5","员":"6","会":"7","膏":"8","药":"9"}

"""
Decrypter class
"""
class decrypter():
    def __init__(self, key):
        self.key_arr = self.initialize_key_arr(key)
    
    def initialize_key_arr(key):
        key_arr = []
        for i in range(0,255):
            key_arr.append(i)
        index_1 = 0
        for index2 in range(0,255):
            index_1 = (((index_1 + key_arr[index_2]) + key[index_2 % len(key)]) + 256) % 256
            key_arr = self._swap(index_2, index_1, key_arr)
        return key_arr

    def _swap(i2, i1, arr):
        i3 = arr[i1]
        arr[i1] = arr[i2]
        arr[i2] = i3
        return arr
    
    def decrypt(encoded_text):



# replace(" ", "") used because sometimes tweets use spaces as well
def get_text(twitterCC):
    contents = urllib.request.urlopen(twitterCC).read()
    contents = contents.decode("utf-8").replace(" ","") 
    encryptedCC = re.search('苏尔的开始(.*)苏尔苏尔完', contents).group(1)
    
    return encryptedCC

def get_b64(enc_text):
    dec_text = []
    for i in range(0, len(enc_text)):
        dec_text.append(translation_dict[enc_text[i]])
    return ''.join(dec_text)

def get_from_file(file_name):
    item_list = []
    with open(file_name) as f:
        for item in f.readlines():
            item_list.append(item.rstrip())
        f.close()
        print(item_list)
    return item_list


def get_rc4(enc_text):
    arr = [None]*(len(enc_text)/2)
    for i in range(0, len(enc_text), 2):
        arr[i/2] = 


if __name__=="__main__":
    key_list = get_from_file(key_file)
    twitter_acc_list = get_from_file(twitter_file)
    for acc in twitter_acc_list:
        enc_text = get_text("https://twitter.com/" + acc)
        b64_text = get_b64(enc_text)
        rc4_text = base64.b64decode(b64_text).decode("utf-8")
    
        for key in key_list:
            decrypter = decrypter(key)
