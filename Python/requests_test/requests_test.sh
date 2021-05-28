filename=$1

# if [ ! -e ./html_file/$filename.html ]; then
    touch ./html_file/$filename.html
    python3 requests_test.py $filename > ./html_file/$filename.html
# fi
open ./html_file/$filename.html
# say -v kyoko -f ./html_file/$filename.html

# source > target だと上書き保存
# source >> target だと追記して保存
