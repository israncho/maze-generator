#!/bin/bash	
# ACTUALIZA cambios de TODOS los archivo a rama master
# para utilizar:
# $ bash updateAll.sh "esto es un commit"

git checkout master
git add -A
git commit -m "$1"
git push
git status
