#!/bin/bash 

if [ "a" = "b" ]
then 
	echo "kek"
else 
	echo "pep"

fi

if mkdir test
then 
echo "ZheZh"
fi
echo "rer"

a=5

echo $a

export A=12

CONTENT=$(cat test1.txt) 
if [ $CONTENT = "TEST" ]
then 
echo "ok"
fi


for I in {1..1}
do
 echo $I 
	echo "Zhep"
done


echo $#
echo $0
echo $1
echo $2
echo $(( $1 + $2 ))

