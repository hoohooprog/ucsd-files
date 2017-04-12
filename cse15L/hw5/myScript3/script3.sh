#!/bin/bash
# due 2/13/17 @ 11:59PM
# Name: Jingyi Tay
# PID: A99014740
#
# usage example: ./script3 target_directory

MONTHS=(Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec) 
directory=`pwd`
AddSSH=cs15xabl@ieng6.ucsd.edu:/home/linux/ieng6/cs15x/public/slides

if [ ! -d Jan ]; then
  mkdir Jan
fi 

if [ ! -d Feb ]; then
  mkdir Feb
fi

if [ ! -d Mar ]; then
  mkdir Mar
fi

if [ ! -d Apr ]; then
  mkdir Apr
fi

if [ ! -d May ]; then
  mkdir May
fi

if [ ! -d Jun ]; then
  mkdir Jun
fi

if [ ! -d Jul ]; then
  mkdir Jul
fi

if [ ! -d Aug ]; then
  mkdir Aug
fi

if [ ! -d Sep ]; then
  mkdir Sep
fi

if [ ! -d Oct ]; then
  mkdir Oct
fi

if [ ! -d Nov ]; then
  mkdir Nov
fi

if [ ! -d Dec ]; then
  mkdir Dec
fi

scp -r $AddSSH $directory

cd slides
