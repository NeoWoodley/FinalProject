# 辅助输出信息
message("now using FindFREEGLUT.cmake to find FREEGLUT")

# 将freeglut.h文件路径复制给FREEGLUT_INCLUDE_DIR
FIND_PATH(FREEGLUT_INCLUDE_DIR freeglut.h
		/usr/local/Cellar/freeglut/3.0.0/include/GL/
		)
message("./h dir: ${FREEGLUT_INCLUDE_DIR}")

# 将libglut.3.10.0.dylib文件路径赋值给
FIND_LIBRARY(FREEGLUT_LIBRARY libglut.3.10.0.dylib
		/usr/local/Cellar/freeglut/3.0.0/lib/
		)
message("dylib dir: ${FREEGLUT_LIBRARY}")

if (FREEGLUT_INCLUDE_DIR AND FREEGLUT_LIBRARY)
	# 设置变量结果
	set(FREEGLUT_FOUND TRUE)
endif (FREEGLUT_INCLUDE_DIR AND FREEGLUT_LIBRARY)