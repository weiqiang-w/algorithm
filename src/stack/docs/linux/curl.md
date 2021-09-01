https://www.thegeekstuff.com/2012/04/curl-examples/
https://curl.se/docs/httpscripting.html

CURL

-o/-O
# save file as 123.txt
curl -o 123.txt http://www.abc.com/abc.txt
# save file as abc.txt
curl -O http://www.abc.com/abc.txt

# Fetch Multiple Files at a time
curl -O URL1 -O URL2


# Follow HTTP Location Headers with -L option
curl -L http://www.google.com

# Continue/Resume a Previous Download
$ curl -O http://www.gnu.org/software/gettext/manual/gettext.html
##############             20.1%
curl -C - -O http://www.gnu.org/software/gettext/manual/gettext.html
###############            21.1%


# Limit the Rate of Data Transfer
$ curl --limit-rate 1000B -O http://www.gnu.org/software/gettext/manual/gettext.html

# Pass HTTP Authentication in cURL
$ curl -u username:password URL

# Download Files from FTP server
$ curl -u ftpuser:ftppass -O ftp://ftp_server/public_html/xss.php

# Upload Files to FTP Server
$ curl -u ftpuser:ftppass -T myfile.txt ftp://ftp.testserver.com
$ curl -u ftpuser:ftppass -T "{file1,file2}" ftp://ftp.testserver.com

# More Information using Verbose and Trace Option
curl -v http://google.co.in

---

# 将网站的cookies信息保存到sugarcookies文件中
curl -D sugarcookies http://localhost/sugarcrm/index.php

# 使用上次保存的cookie信息
curl -b sugarcookies http://localhost/sugarcrm/index.php

# GET
curl -u username https://api.github.com/user?access_token=XXXXXXXXXX

# POST
curl -u username --data "param1=value1&param2=value" https://api.github.com

# 也可以指定一个文件，将该文件中的内容当作数据传递给服务器端
curl --data @filename https://github.api.com/authorizations

# 默认情况下，通过POST方式传递过去的数据中若有特殊字符，首先需要将特殊字符转义在传递给服务器端，如value值中包含有空格，则需要先将空格转换成%20，如：
curl -d "value%201" http://hostname.com

# 在新版本的CURL中，提供了新的选项 --data-urlencode，通过该选项提供的参数会自动转义特殊字符。
curl --data-urlencode "value 1" http://hostname.com

# 除了使用GET和POST协议外，还可以通过 -X 选项指定其它协议，如：
curl -I -X DELETE https://api.github.cim

# 上传文件
curl --form "fileupload=@filename.txt" http://hostname/resource















