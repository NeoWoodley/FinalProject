# 确定cmake最低版本需求
cmake_minimum_required(VERSION 3.13)

cmake_policy(VERSION 2.8)

# 指定工程名称，将会引入两个变量
# 1、XXX_BINARY_DIR(二进制文件保存路径)
# 2、XXX_SOURCE_DIR(源代码保存路径)
project(OpenGLFormalTry)

# 确定编译语言
# 可以通过set来设定，也可以通过add_definitions来设定：add_definitions(-std=c++14)
set(CMAKE_CXX_STANDARD 14)

# 设置Build Type
IF(NOT CMAKE_BUILD_TYPE)
	SET(CMAKE_BUILD_TYPE Debug CACHE STRING "Choose the type of build (Debug or Release)" FORCE)
ENDIF(NOT CMAKE_BUILD_TYPE)

# 定义自己的cmake模块所在的路径
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_SOURCE_DIR}/cmake/modules/")

# WIN32：在所有的win32平台为TRUE，包括cygwin
# 设置runtime目标的输出路径
if(WIN32)
	set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/bin)
endif(WIN32)

# 添加非标准的共享库搜索路径
# 添加库路径
link_directories(${CMAKE_SOURCE_DIR}/lib)

list(APPEND CMAKE_CXX_FLAGS "-std=c++11")

# find the required packages：添加需要的库
# find_package会令CMake搜索所有名为Find.cmake的文件，"数字 REQUIRED"给出需要的具体版本，以避免一台计算机中安装了多个版本的packages而造成不必要的错误。
# 通常情况下，通过设置CMAKE_PREFIX_PATH来设置CMake搜索路径，通常情况下不加也可以，但考虑到代码的可移植性，最好还是对搜索路径进行对应设置
find_package(GLM REQUIRED)
# 向终端输出用户定义的信息或变量的值
message(STATUS "GLM included at ${GLM_INCLUDE_DIR}")
find_package(GLFW3 REQUIRED)
message(STATUS "Found GLFW3 in ${GLFW3_INCLUDE_DIR}")
find_package(ASSIMP REQUIRED)
message(STATUS "Found ASSIMP in ${ASSIMP_INCLUDE_DIR}")
# find_package(SOIL REQUIRED)
# message(STATUS "Found SOIL in ${SOIL_INCLUDE_DIR}")
# find_package(GLEW REQUIRED)
# message(STATUS "Found GLEW in ${GLEW_INCLUDE_DIR}")
# 我自己写的
#find_package(GLUT REQUIRED)
#message(STATUS "Found GLUT in ${GLUT_INCLUDE_DIR}")
find_package(GLTools REQUIRED)
message(STATUS "Found GLTools in ${GLTools_INCLUDE_DIR}")
find_package(GLEW REQUIRED)
message(STATUS "Found GLEW in ${GLEW_INCLUDE_DIR}")
find_package(FREEGLUT REQUIRED)
message(STATUS "Found FREEGLUT in ${FREEGLUT_INCLUDE_DIR}")
# 怎么回事，感觉freeglut、glut完全不用配置啊？？？！！！
# 感觉应该是只要配置两者中的一个，两个就都可以使用；如果两个都不配置的话，就没法用了
# 在这里，使用freeglut代替glut

# 下面的"glut"、"GLUT_LIBRARY"、"GLTools"、"GLEW"、"freeglut"都是我自己加的
if(WIN32)
	# 定义变量LIBS
	set(LIBS glfw3 opengl32 assimp
#			glut
			gltools glew
			freeglut
			)
elseif(UNIX AND NOT APPLE)
	set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -Wall")
	find_package(OpenGL REQUIRED)
		# 设定变量
	add_definitions(${OPENGL_DEFINITIONS})
	find_package(X11 REQUIRED)
	# note that the order is important for setting the libs
	# use pkg-config --libs $(pkg-config --print-requires --print-requires-private glfw3) in a terminal to confirm
	set(LIBS ${GLFW3_LIBRARY} X11 Xrandr Xinerama Xi Xxf86vm Xcursor GL dl pthread ${ASSIMP_LIBRARY} ${GLUT_LIBRARY}
			${GLTools_LIBRARY} ${GLEW_LIBRARY}
			${FREEGLUT_LIBRARY}
			)
	set (CMAKE_CXX_LINK_EXECUTABLE "${CMAKE_CXX_LINK_EXECUTABLE} -ldl")
elseif(APPLE)
	# 向工程添加多个特定的头文件搜索路径
	INCLUDE_DIRECTORIES(/System/Library/Frameworks)
	# 找到库的全路径，包括库文件名
	FIND_LIBRARY(COCOA_LIBRARY Cocoa)
	FIND_LIBRARY(OpenGL_LIBRARY OpenGL)
	FIND_LIBRARY(IOKit_LIBRARY IOKit)
	FIND_LIBRARY(CoreVideo_LIBRARY CoreVideo)
	MARK_AS_ADVANCED(COCOA_LIBRARY OpenGL_LIBRARY)
	SET(APPLE_LIBS ${COCOA_LIBRARY} ${IOKit_LIBRARY} ${OpenGL_LIBRARY} ${CoreVideo_LIBRARY})
	SET(APPLE_LIBS ${APPLE_LIBS} ${GLFW3_LIBRARY} ${ASSIMP_LIBRARY} ${GLUT_LIBRARY} ${GLTools_LIBRARY}
			${GLEW_LIBRARY}
			${FREEGLUT_LIBRARY}
			)
	set(LIBS ${LIBS} ${APPLE_LIBS})
else()
	set(LIBS )
endif(WIN32)

set(CHAPTERS
		formal_try
		)

set(formal_try
		CHO)

configure_file(configuration/root_directory.h.in configuration/root_directory.h)
include_directories(${CMAKE_BINARY_DIR}/configuration)

# first create relevant static libraries requried for other projects
# 生成动态库或静态库（生成库文件）
add_library(STB_IMAGE "src/stb_image.cpp")  # STB_IMAGE为生成的文件名，"src/stb_image.cpp"为源文件路径
set(LIBS ${LIBS} STB_IMAGE)

# 添加GLAD
add_library(GLAD "src/glad.c")
set(LIBS ${LIBS} GLAD)

macro(makeLink src dest target)
	add_custom_command(TARGET ${target} POST_BUILD COMMAND ${CMAKE_COMMAND} -E create_symlink ${src} ${dest}  DEPENDS  ${dest} COMMENT "mklink ${src} -> ${dest}")
endmacro()

# then create a project file per tutorial
# 为每个directory创建project file
foreach(CHAPTER ${CHAPTERS})
	foreach(DEMO ${${CHAPTER}})
		file(GLOB SOURCE
				"src/${CHAPTER}/${DEMO}/*.h"
				"src/${CHAPTER}/${DEMO}/*.cpp"
				"src/${CHAPTER}/${DEMO}/*.vs"
				"src/${CHAPTER}/${DEMO}/*.fs"
				"src/${CHAPTER}/${DEMO}/*.gs"
				)
		set(NAME "${CHAPTER}__${DEMO}")
		# 生成可执行文件，${NAME}为生成的可执行文件名，${SOURCE}为源文件路径
		add_executable(${NAME} ${SOURCE})
		# 目标文件与库文件进行链接，为每个src中的文件夹中的子文件夹中的内容进行链接
		# 如果需要链接其他的动态库，-l后接去除lib前缀和.so后缀的名称，以链接liblianghao.so为例，-llianghao
		target_link_libraries(${NAME} ${LIBS})  # ${NAME}为需要进行链接的文件名，${LIBS}为需要链接的库
		if(WIN32)
			set_target_properties(${NAME} PROPERTIES RUNTIME_OUTPUT_DIRECTORY "${CMAKE_SOURCE_DIR}/bin/${CHAPTER}")
		elseif(UNIX AND NOT APPLE)
			set_target_properties(${NAME} PROPERTIES RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/bin/${CHAPTER}")
		elseif(APPLE)
			set_target_properties(${NAME} PROPERTIES RUNTIME_OUTPUT_DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/bin/${CHAPTER}")
			set_target_properties(${NAME} PROPERTIES RUNTIME_OUTPUT_DIRECTORY_DEBUG "${CMAKE_CURRENT_BINARY_DIR}/bin/${CHAPTER}")
			set_target_properties(${NAME} PROPERTIES RUNTIME_OUTPUT_DIRECTORY_RELEASE "${CMAKE_CURRENT_BINARY_DIR}/bin/${CHAPTER}")
		endif(WIN32)
		# copy shader files to build directory. 将着色器文件复制到build directory
		file(GLOB SHADERS
				"src/${CHAPTER}/${DEMO}/*.vs"
				 "src/${CHAPTER}/${DEMO}/*.frag"  # 这一行在原来的代码中注释掉了
				"src/${CHAPTER}/${DEMO}/*.fs"
				"src/${CHAPTER}/${DEMO}/*.gs"
				)
		foreach(SHADER ${SHADERS})
			if(WIN32)
				# configure_file(${SHADER} "test")
				add_custom_command(TARGET ${NAME} PRE_BUILD COMMAND ${CMAKE_COMMAND} -E copy ${SHADER} $<TARGET_FILE_DIR:${NAME}>)
			elseif(UNIX AND NOT APPLE)
				file(COPY ${SHADER} DESTINATION ${CMAKE_CURRENT_BINARY_DIR}/bin/${CHAPTER})
			elseif(APPLE)
				# create symbolic link for *.vs *.fs *.gs
				get_filename_component(SHADERNAME ${SHADER} NAME)
				makeLink(${SHADER} ${CMAKE_CURRENT_BINARY_DIR}/bin/${CHAPTER}/${SHADERNAME} ${NAME})
			endif(WIN32)
		endforeach(SHADER)
		# if compiling for visual studio, also use configure file for each project (specifically to set up working directory)
		if(MSVC)
			configure_file(${CMAKE_SOURCE_DIR}/configuration/visualstudio.vcxproj.user.in ${CMAKE_CURRENT_BINARY_DIR}/${NAME}.vcxproj.user @ONLY)
		endif(MSVC)
	endforeach(DEMO)
endforeach(CHAPTER)

# 添加头文件路径
include_directories(${CMAKE_SOURCE_DIR}/includes)