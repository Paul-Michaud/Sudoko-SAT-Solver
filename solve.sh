#!/bin/bash
printf "Creating rules...\n"
java -jar Sudoku-SAT-Solver.jar grid1.txt | sed '/^\s*$/d'
printf "Rules created.\n"
printf "Converting to CNF...\n"
./logic2cnf -c out.txt > out.cnf
printf "CNF file created\n"
printf "Solving using minisat...\n"
./minisat out.cnf out.sat
printf "Displaying result...\n"

conv=$(grep l1c1v1 -A 729 out.cnf | sed 's/  */ /g' | cut -d' ' -f3,5 )

declare -A CONVERSIONMAP=();

while read -r line; do
     CONVERSIONMAP[$(echo $line | cut -d' ' -f1)]=$(echo $line | cut -d' ' -f2)
done <<< "$conv"


CTR=1
while read -r l; do
     if [[ ${l} != *"-"* ]];then
         if test "${CONVERSIONMAP[$l]+isset}";then
                printf "|"${CONVERSIONMAP[$l]:5:5}
                if ! ((CTR % 9)); then
                  printf "|\n"
                fi
                CTR=$((CTR+1))

         fi
     fi
done <<< "$(cat out.sat | tr " " "\n")"

printf "Done\n"


