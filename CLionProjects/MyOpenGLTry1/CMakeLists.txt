cmake_minimum_required(VERSION 3.13)

cmake_policy(VERSION 2.8)

# 指定工程名称
project(MyOpenGLTry1)

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

# find the required packages
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

if(WIN32)
	# 定义变量LIBS
	set(LIBS glfw3 opengl32 assimp)
elseif(UNIX AND NOT APPLE)
	set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -Wall")
	find_package(OpenGL REQUIRED)
	add_definitions(${OPENGL_DEFINITIONS})
	find_package(X11 REQUIRED)
	# note that the order is important for setting the libs
	# use pkg-config --libs $(pkg-config --print-requires --print-requires-private glfw3) in a terminal to confirm
	set(LIBS ${GLFW3_LIBRARY} X11 Xrandr Xinerama Xi Xxf86vm Xcursor GL dl pthread ${ASSIMP_LIBRARY})
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
	SET(APPLE_LIBS ${APPLE_LIBS} ${GLFW3_LIBRARY} ${ASSIMP_LIBRARY})
	set(LIBS ${LIBS} ${APPLE_LIBS})
else()
	set(LIBS )
endif(WIN32)

set(CHAPTERS
		try1
		try2
		)

set(try1

		)

set(try2

		)

configure_file(configuration/root_directory.h.in configuration/root_directory.h)
include_directories(${CMAKE_BINARY_DIR}/configuration)

# first create relevant static libraries requried for other projects
# 生成动态库或静态库
add_library(STB_IMAGE "src/stb_image.cpp")
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
		add_executable(${NAME} ${SOURCE} src/try1/try1.1.cpp)
		# 目标文件与库文件进行链接，为每个src中的文件夹中的子文件夹中的内容进行链接
		target_link_libraries(${NAME} ${LIBS})
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


#add_executable(MyOpenGLTry1 main.cpp)