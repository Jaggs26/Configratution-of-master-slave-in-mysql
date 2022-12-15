# sl-1 BT20CSE071_JAGADESH 
# TASK-3


Configure MySql in master slave mode using student database.

Prerequisites : Two Ubuntu linux servers in VM or Dual Booted,And Mysql on                both the servers.
ram above 6 GB is prefered in case of Virtual Machine.

Note : Mysql commands Should be written in capitals.
STEP 1: Upating Both the systems.
```bash
sudo apt update
```
STEP 2: Installing MySql on both th servers
```bash
sudo apt install mysql-server mysql-client
```
STEP 3: Adjust The source server  Firewall 
```bash
sudo ufw allow from replica_server_ip to any port 3306
```
Here replica_server_ip is the ip address of slave and Mysql's default port number is 3306;
STEP 4: Configuring the Source Database
Open the mysql configuration file
```bash
sudo gedit /etc/mysql/mysql.conf.d/mysqld.cnf
```
In that file There are some lines we have to uncomment them and some we have to change 
first change 
bind-adress = master ip adress
and uncommet the server id and set it to 1;
or simply remove all lines after mysqlid and paste the below code
```
[mysqld]
user            = mysql
pid-file        = /var/run/mysqld/mysqld.pid
socket  = /var/run/mysqld/mysqld.sock
datadir = /var/lib/mysql
tmpdir          = /tmp
bind-address            = 192.168.64.4
key_buffer_size         = 16M
myisam-recover-options  = BACKUP
log_error = /var/log/mysql/error.log
slow_query_log          = 1
slow_query_log_file     = /var/log/mysql/mysql-slow.log
server-id               = 1
max_binlog_size   = 500M
log_bin = /var/log/mysql/mysql-bin.log
log_bin_index =/var/log/mysql/mysql-bin.log.index
relay_log = /var/log/mysql/mysql-relay-bin
relay_log_index = /var/log/mysql/mysql-relay-bin.index
sync_binlog = 1
binlog_format = ROW
```
Now restart mysql
```
sudo systemctl restart mysql
```
Step 4: Creating a replication User
login to root sql account and run the following commands 
```bash
CREATE USER 'replica_user'@'replica_server_ip' IDENTIFIED WITH mysql_native_password BY 'password';
```
here replica means slave so slave user and slave ip and a password
After that grant replicaation for that user
```bash
GRANT REPLICATION SLAVE ON *.* TO 'replica_user'@'replica_server_ip';
```
Then run FLUSH PRIVILEGES COMMAND This will free up any memory that the server cached as a result of the preceding CREATE USER and GRANT
Then Type ```bash
SHOW MASTER STATUS\g;
```
Note down the file and position 
STEP 5: Configuring the Replica Database
```bash
sudo gedit /etc/mysql/mysql.conf.d/mysqld.cnf
```
In that file There are some lines we have to uncomment them and some we have to change 
first change 
bind-adress = master ip adress
and uncommet the server id and set it to 1;
or simply remove all lines after mysqlid and paste the below code
```
[mysqld]
user            = mysql
pid-file        = /var/run/mysqld/mysqld.pid
socket  = /var/run/mysqld/mysqld.sock
datadir = /var/lib/mysql
tmpdir          = /tmp
bind-address            = 192.168.64.7
key_buffer_size         = 16M
myisam-recover-options  = BACKUP
log_error = /var/log/mysql/error.log
slow_query_log          = 1
slow_query_log_file     = /var/log/mysql/mysql-slow.log
server-id               = 2
max_binlog_size   = 500M
log_bin = /var/log/mysql/mysql-bin.log
log_bin_index =/var/log/mysql/mysql-bin.log.index
relay_log = /var/log/mysql/mysql-relay-bin
relay_log_index = /var/log/mysql/mysql-relay-bin.index
sync_binlog = 1
binlog_format = ROW
```
Now restart mysql
```
sudo systemctl restart mysql
```
Now login to Mysql in the slave 
```bash
CHANGE REPLICATION SOURCE TO
SOURCE_HOST='master ip adress',
SOURCE_USER='slave user which we have created',
SOURCE_PASSWORD='password',
SOURCE_LOG_FILE='mysql-bin.xxxxxx(one which we noted eariler in logfile)',
SOURCE_LOG_POS=XXX(one which we noted eariler in logfile);
```
Then start the replica by
```bash
START REPLICA;
```
then see the status
```bash
SHOW REPLICA STATUS\G;
```
You should get slave_io_thread as yes and slave_io_running as yes 

STEP 6: testing the replication 
Login into mysql on master and create a database;
Create database studentlist;
Login into mysql on slave and run 
Show databases;
You will se studentlist database in the databases list















