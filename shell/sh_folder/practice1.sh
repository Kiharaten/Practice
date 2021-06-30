echo "Hello world"
mdfind "Thumbs.db" | grep "Thumbs.db" | xargs rm
echo "hoge" | xargs -I {} echo {}"sample"