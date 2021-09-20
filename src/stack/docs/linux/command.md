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