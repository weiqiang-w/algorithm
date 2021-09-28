ref:
    https://mp.weixin.qq.com/s/yVWs9BHt29gX-sLbmNQkjA
---

cat -n nohup.out|grep -w 26
cat nohup.out|sed -n '26p'
cat nohup.out|awk 'NR==26'
cat nohup.out|head -n 26|tail -n1tree


# less查看文件，其中-N表示显示行号，-S表示不换行显示
# ctrl+f向前翻页，ctrl+b向后翻页，输入/再输入xxx搜索xxx，接着按n搜索下一个，N搜索上一下
$ less -N -S data.txt
# 在当前目录递归的查找包含ERROR文件的行(非常实用)
$ grep -rn 'ERROR' .
# 输出查找到的行，以及其后面的2行，常用于查找异常栈(非常实用)
$ cat data.txt|grep -A 2 '[2]'
# 查找日志文件中最后产生的10条错误日志，-i用于忽略大小写，tac用于倒序输出行(非常实用)
tac app.log|grep -i -m10 'ERROR'|tac

--------

ref:
    https://mp.weixin.qq.com/s?__biz=MzA4MTc4NTUxNQ==&mid=2650519865&idx=1&sn=e6c15ef16b8ee81f71924d9757a8c608&scene=21#wechat_redirect
    - sed: https://mp.weixin.qq.com/s?__biz=MzA4MTc4NTUxNQ==&mid=2650519751&idx=1&sn=adef39cb108277731608069960692c77&scene=21#wechat_redirect
    - swk: https://mp.weixin.qq.com/s?__biz=MzA4MTc4NTUxNQ==&mid=2650519843&idx=1&sn=fe4a5c405a35b42a850054eb4283ff40&scene=21#wechat_redirect
    - vim: https://mp.weixin.qq.com/s?__biz=MzA4MTc4NTUxNQ==&mid=2650518612&idx=1&sn=125c2cb9ee6d76a6817fb0ebc5a3c5e4&scene=21#wechat_redirect
--- 
# 查看文件大小
du -h file
# 查看某个进程中的线程状态。
top -H -p pid
# df命令用来查看系统中磁盘的使用量，用来查看磁盘是否已经到达上限。参数h可以以友好的方式进行展示。
df -h
# 查看当前的所有tcp连接。
netstat -ant


