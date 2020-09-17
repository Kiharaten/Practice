echo are you sure clean ssh known host data ? [Y/n]
read command 
if test $command = y ; then 
    echo cleaning ssh host data...
    rm ~/.ssh/known_hosts
    touch ~/.ssh/known_hosts
    chmod 644 ~/.ssh/known_hosts
elif test $command = n ; then 
    echo oups!
else 
    echo cleaning ssh host data...
    rm ~/.ssh/known_hosts
    touch ~/.ssh/known_hosts
    chmod 644 ~/.ssh/known_hosts
fi
    echo bye 

# if elif else fi の利用