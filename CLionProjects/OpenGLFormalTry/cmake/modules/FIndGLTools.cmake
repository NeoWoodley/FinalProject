## 这是我自己写的，应该会有问题
#SET(_gltools_HEADER_SEARCH_DIRS
#		"/usr/include"
#		"/usr/local/include"
#		"${CMAKE_SOURCE_DIR}/includes"
#		"C:/Program Files (x86)/GLTools/include")
#SET(_gltools_LIB_SEARCH_DIRS
#		"/usr/lib"
#		"/usr/local/lib"
#		"${CMAKE_SOURCE_DIR}/lib"
#		#		"C:/Program Files (x86)/GLTools/lib-msvc110"
#		# 上面这一行不确定对不对
#		)
#
## Check environment for root search directory
#set(_gltools_ENV_ROOT $ENV{GLTools_ROOT})
#if (NOT GLTools_ROOT AND _gltools_ENV_ROOT)
#	set(GLTools_ROOT ${_gltools_ENV_ROOT})
#endif ()
#
## Put user specified location at beginning of search
#if (GLTools_ROOT)
#	list(INSERT _gltools_HEAD_SEARCH_DIRS 0 "${GLTools_ROOT}/include")
#	list(INSERT _gltools_LIB_SEARCH_DIRS 0 "${GLTools_ROOT}/lib")
#endif ()
#
## Search for the head
#FIND_PATH(GLTools_INCLUDE_DIR "GLTools/GLTools.h"
#		PATHS ${_gltools_HEADER_SEARCH_DIRS})
#
## Search for the library
#FIND_PATH(GLTools_LIBRARY NAMES GLTools
#		PATHS ${_gltoos_LIB_SEARCH_DIRS})
#INCLUDE(FindPackageHandleStandardArgs)
#FIND_PACKAGE_HANDLE_STANDARD_ARGS(GLTools DEFAULT_MSG
#		GLTools_LIBRARY GLTools_INCLUDE_DIR)

# 以上我自己的尝试并没有成功

# 辅助输出信息
message("now using FindGLTools.cmake to find GLTools")

# 将GLTools.h文件路径复制给GLTools_INCLUDE_DIR
FIND_PATH(GLTools_INCLUDE_DIR GLTools.h
		/usr/local/Cellar/GLTools/include/
		/usr/local/include/)
message("./h dir: ${GLTools_INCLUDE_DIR}")

# 将libgltools.a文件路径赋值给
FIND_LIBRARY(GLTools_LIBRARY libgltools.a
		/usr/local/lib/
		/usr/local/Cellar/GLTools/build/)
message("lib dir: ${GLTools_LIBRARY}")

if (GLTools_INCLUDE_DIR AND GLTools_LIBRARY)
	# 设置变量结果
	set(GLTools_FOUND TRUE)
endif (GLTools_INCLUDE_DIR AND GLTools_LIBRARY)