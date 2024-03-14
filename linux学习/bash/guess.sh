#!/bin/bash
#****************************************************************#
# ScriptName: guess.sh
# Author: huangdu.hd@alibaba-inc.com
# Create Date: 2023-07-29 21:00
# Modify Author: $SHTERM_REAL_USER@alibaba-inc.com
# Modify Date: 2023-07-30 22:24
# Function: This is a small game of guessing numbers.
#***************************************************************#
function selectLevel {
    read -p "Please select your level(1-6):" level
}
function generateAns {
    local value=""
    for (( i=0; i<level; i++ ))
    do
        value="$value $[ $RANDOM % 10 ]"
    done
    ans=($value)
}
function dealInput {
    local input=""
    local output=""
    local value=""
    read -p "Please input $level digits(0-9):" input
    for (( i=1; i<=level; i++ ))
    do
        value="$value $(expr substr $input $i 1)"
    done
    echo $value
}
function checkInput {
    count_a=0
    count_b=$level
    local input=($1 $2 $3 $4)
    local count=(0 0 0 0 0 0 0 0 0 0)
    for (( i=0; i<level; i++ ))
    do
        if [ ${ans[$i]} -eq ${input[$i]} ]
        then
            count_a=$[ ${count_a} + 1 ]
        fi
        count[${ans[$i]}]=$[ ${count[${ans[$i]}]} + 1 ]
        count[${input[$i]}]=$[ ${count[${input[$i]}]} - 1 ]
    done
    for num in ${count[*]}
    do
        if [ $num -gt 0 ]            
		then
        	count_b=$[ $count_b - $num ]
        fi
    done
}
echo "Welcome to guessing numbers small game."
selectLevel
generateAns
count_a=0
time=1
until [ $count_a -eq $level ]
do
    checkInput $(dealInput)
    time=$[ $time + 1 ]
    echo "$count_a a $count_b b"
done
echo "Congratulations you win! level: $level, times: $time"