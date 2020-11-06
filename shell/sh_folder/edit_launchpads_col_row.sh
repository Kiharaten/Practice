echo input your favorite numbers
echo rows number is
read num1
echo rows number is $num1
echo columns number is
read num2
echo columns number is $num2
defaults write com.apple.dock springboard-columns -int $num1
defaults write com.apple.dock springboard-rows -int $num2
echo changing your launchpads settings...
killall Dock