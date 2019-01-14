### Anubis CC tool
Extract anubis CC from twitter accounts.

`keys.dat` and `twitter_acc.dat` contain known keys and twitter bots.
```bash
java -classpath ".:sqlite-jdbc-3.23.1.jar:" Anubis_DB_Update
```
will run throught the list of known bots and update the database if it find new CCs. The database management relies on the [SQLite JDBC Driver](https://github.com/xerial/sqlite-jdbc).


```bash
`java Anubis_Dec "<CHINESE CHARS>"
```
 will print the CC and the key if it finds one.