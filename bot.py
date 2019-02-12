import tweepy
import time

from creds import *

# anubis known strings
key_string_beginning = '"苏尔的开"' 
key_string_end = '"苏尔苏尔完"'
key_string_beginning_spaced = '"苏 尔 的 开"' 
key_string_end_spaced = '"苏 尔 苏 尔 完"'
TWITTER_ACC_LIST = "data/twitter_acc.dat"


def main():
    print("[*] Authenticating into Twitter API")
    api = authenticate()

    print("[*] Starting Twitter bot")
    while True:
        bot_list = get_twitter_bots(api)        
        all_bots = check_if_new(bot_list)        
        update_bot_list(all_bots)
        time.sleep(60)    

def authenticate():
    auth = tweepy.OAuthHandler(API_KEY, API_SECRET)
    auth.set_access_token(ACCESS_TOKEN, ACCESS_SECRET)
    api = tweepy.API(auth)
    return api


def read_file(filename):
    with open(filename) as f:
        bot_list = f.read().splitlines() 
    return bot_list

def update_bot_list(bot_list):
    with open(TWITTER_ACC_LIST, "w") as f:
        for bot in bot_list:
            f.write(bot + '\n')


def check_if_new(bot_list):
    known_bots = read_file(TWITTER_ACC_LIST)
    for bot in bot_list:
        if bot not in known_bots:
            known_bots.append(bot)
            print("found new bot: " + bot)   
    return known_bots


def get_twitter_bots(api):
    users = []
    public_tweets1 = api.search(key_string_beginning)
    public_tweets2 = api.search(key_string_end)
    public_tweets3 = api.search(key_string_beginning_spaced)
    public_tweets4 = api.search(key_string_end_spaced)
    for tweet in public_tweets1:
        users.append("https://twitter.com/" + tweet.user.screen_name)
    for tweet in public_tweets2:
        users.append("https://twitter.com/" + tweet.user.screen_name)
    for tweet in public_tweets3:
        users.append("https://twitter.com/" + tweet.user.screen_name)
    for tweet in public_tweets4:
        users.append("https://twitter.com/" + tweet.user.screen_name)

    users = sorted(set(users))
    return users        

if __name__=="__main__":
    main()
