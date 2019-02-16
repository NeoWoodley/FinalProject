#!/bin/sh
bindir=$(pwd)
cd /Users/neowoodley/Projects/CLionProjects/learnopengl/Colors/
export 

if test "x$1" = "x--debugger"; then
	shift
	if test "x" = "xYES"; then
		echo "r  " > $bindir/gdbscript
		echo "bt" >> $bindir/gdbscript
		GDB_COMMAND-NOTFOUND -batch -command=$bindir/gdbscript  /Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/Colors 
	else
		"/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/Colors"  
	fi
else
	"/Users/neowoodley/Projects/CLionProjects/learnopengl/cmake-build-debug/Colors"  
fi
