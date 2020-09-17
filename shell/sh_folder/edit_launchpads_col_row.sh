echo input your favorite numbers
echo columns number is
read num1
echo columns number is $num1
echo row number is
read num2
echo row number is $num2
defaults write com.apple.dock springboard-columns -int $num1
defaults write com.apple.dock springboard-rows -int $num2
echo changing your launchpads settings...
killall Dock