# 辅助输出信息
message("now using FindGLEW.cmake to find GLEW")

# 将GLEW.h文件路径复制给GLEW_INCLUDE_DIR
FIND_PATH(GLEW_INCLUDE_DIR glew.h
		/usr/local/Cellar/glew/2.1.0/include/GL/
		)
message("./h dir: ${GLEW_INCLUDE_DIR}")

# 将libGLEW.dylib文件路径赋值给
FIND_LIBRARY(GLEW_LIBRARY libGLEW.dylib
		/usr/local/Cellar/glew/2.1.0/lib/)
message("./dylib dir: ${GLEW_LIBRARY}")

if (GLEW_INCLUDE_DIR AND GLEW_LIBRARY)
	# 设置变量结果
	set(GLEW_FOUND TRUE)
endif (GLEW_INCLUDE_DIR AND GLEW_LIBRARY)