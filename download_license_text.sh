rm gpl.txt.1
rm lgpl.txt.1

wget https://www.gnu.org/licenses/gpl.txt
wget https://www.gnu.org/licenses/lgpl.txt

mv gpl.txt COPYING
mv lgpl.txt COPYING.LESSER

