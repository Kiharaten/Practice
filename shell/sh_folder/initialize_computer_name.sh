echo input your new computer name

sudo scutil --set ComputerName $str
sudo scutil --set HostName $str
sudo scutil --set LocalHostName $str

echo initialized your computer name
echo computer name is
sudo scutil --get ComputerName
echo host name is
sudo scutil --get HostName
echo local host name is
sudo scutil --get LocalHostName