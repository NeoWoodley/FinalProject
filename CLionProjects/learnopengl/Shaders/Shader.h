//#ifndef SHADER_H
//#define SHADER_H
//
//#include <string>
//#include <fstream>
//#include <sstream>
//#include <iostream>
//
//// 包含glew来获取所有的必须OpenGL头文件
//#include <GL/glew.h>
//
////
//// Created by Neo Woodley on 2019-02-05.
////
//
///**
// * 编写、编译、管理着色器是件麻烦。
// * 我们可以写一个类来让我们的生活轻松一点，它可以从硬盘读取着色器，然后编译并链接它们，并对它们进行错误检测。
// *
// * 我们应当封装目前所学的知识到一个抽象对象中。
// * 我们会把着色器类全部放在头文件类里，方便移植。
// */
//
//
//// 添加必要的include，并定义类结构
////#include "Shader.h"
//
//class Shader {
//public:
//	// 程序ID
//	GLuint Program;
//
//	// 构造器读取并构建着色器
//	Shader(const GLchar *vertexPath, const GLchar *fragmentPath){
//		// 从文件路径中获取顶点/片段着色器
//		// 我们使用C++文件流读取着色器内容，储存到几个string对象里
//		std::string vertexCode;
//		std::string fragmentCode;
//		std::ifstream vShaderFile;
//		std::ifstream fShaderFile;
//		// 保证ifstream对象可以抛出异常
//		vShaderFile.exceptions(std::ifstream::badbit);
//		fShaderFile.exceptions(std::ifstream::badbit);
//		try {
//			// 打开文件
//			vShaderFile.open(vertexPath);
//			fShaderFile.open(fragmentPath);
//			std::stringstream vShaderStream, fShaderStream;
//			// 读取文件的缓冲内容到流中
//			vShaderStream << vShaderFile.rdbuf();
//			fShaderStream << fShaderFile.rdbuf();
//			// 关闭文件
//			vShaderFile.close();
//			fShaderFile.close();
//			// 转换流至GLchar数组
//			vertexCode = vShaderStream.str();
//			fragmentCode = fShaderStream.str();
//		} catch (std::ifstream::failure e) {
//			std::cout << "ERROR:SHADER::FILE_NOT_SUCCESSFULLY_READ" << std::endl;
//		}
//
//		const GLchar *vShaderCode = vertexCode.c_str();
//		const GLchar *fShaderCode = fragmentCode.c_str();
//
//		// 编译着色器
//		GLuint vertex, fragment;
//		GLint success;
//		GLchar infoLog[512];
//		// 顶点着色器
//		vertex = glCreateShader(GL_VERTEX_SHADER);
//		glShaderSource(vertex, 1, &vShaderCode, NULL);
//		glCompileShader(vertex);
//		// 打印编译错误（如果有的话）
//		glGetShaderiv(vertex, GL_COMPILE_STATUS, &success);
//		if (!success) {
//			glGetShaderInfoLog(vertex, 512, NULL, infoLog);
//			std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
//		};
//
//		// 片段着色器也类似
//		fragment = glCreateShader(GL_FRAGMENT_SHADER);
//		glShaderSource(fragment, 1, &fShaderCode, NULL);
//		glCompileShader(fragment);
//		// 打印编译错误（如果有的话）
//		glGetShaderiv(fragment, GL_COMPILE_STATUS, &success);
//		if (!success) {
//			glGetShaderInfoLog(fragment, 512, NULL, infoLog);
//			std::cout << "ERROR::SHADER::FRAGMENT::COMPILATION_FAILED\n" << infoLog << std::endl;
//		}
//
//		// 着色器程序
//		this->Program = glCreateProgram();
//		glAttachShader(this->Program, vertex);
//		glAttachShader(this->Program, fragment);
//		glLinkProgram(this->Program);
//		// 打印链接错误（如果有的话）
//		glGetProgramiv(this->Program, GL_LINK_STATUS, &success);
//		if (!success) {
//			glGetProgramInfoLog(this->Program, 512, NULL, infoLog);
//			std::cout << "ERROR::SHADER::PROGRAM::LINKING_FAILED\n" << std::endl;
//			std::cout << infoLog << std::endl;
//			std::cout << infoLog << std::endl;
//		}
//
//		// 删除着色器，因为它们已经链接到我们的程序中了
//		glDeleteShader(vertex);
//		glDeleteShader(fragment);
//
//	}
//
//	// 使用程序
//	void Use(){
//		glUseProgram(this->Program);
//	}
//};
//
///**
// * 在上面，我们在头文件顶部使用了几个预处理指令，这些预处理指令会告知你的编译器只在它没被包含过的情况下才包含和编译这个头文件，即使多个文件都包含了这个着色器头文件。它是用来方式连接冲突的。
// */
//
///**
// * 现在我们就写完了一个完整的着色器类。
// * 使用这个着色器类：只要创建一个着色器对象，从那一点开始我们就可以开始使用了。
// * 详见README.md
// */
//
//#endif
//
////#ifndef SHADER_H
////#define SHADER_H
////
//////#include <glad/glad.h>
////
////#include <string>
////#include <fstream>
////#include <sstream>
////#include <iostream>
////
////class Shader
////{
////public:
////	unsigned int ID;
////	// constructor generates the shader on the fly
////	// ------------------------------------------------------------------------
////	Shader(const char* vertexPath, const char* fragmentPath)
////	{
////		// 1. retrieve the vertex/fragment source code from filePath
////		std::string vertexCode;
////		std::string fragmentCode;
////		std::ifstream vShaderFile;
////		std::ifstream fShaderFile;
////		// ensure ifstream objects can throw exceptions:
////		vShaderFile.exceptions (std::ifstream::failbit | std::ifstream::badbit);
////		fShaderFile.exceptions (std::ifstream::failbit | std::ifstream::badbit);
////		try
////		{
////			// open files
////			vShaderFile.open(vertexPath);
////			fShaderFile.open(fragmentPath);
////			std::stringstream vShaderStream, fShaderStream;
////			// read file's buffer contents into streams
////			vShaderStream << vShaderFile.rdbuf();
////			fShaderStream << fShaderFile.rdbuf();
////			// close file handlers
////			vShaderFile.close();
////			fShaderFile.close();
////			// convert stream into string
////			vertexCode   = vShaderStream.str();
////			fragmentCode = fShaderStream.str();
////		}
////		catch (std::ifstream::failure e)
////		{
////			std::cout << "ERROR::SHADER::FILE_NOT_SUCCESFULLY_READ" << std::endl;
////		}
////		const char* vShaderCode = vertexCode.c_str();
////		const char * fShaderCode = fragmentCode.c_str();
////		// 2. compile shaders
////		unsigned int vertex, fragment;
////		// vertex shader
////		vertex = glCreateShader(GL_VERTEX_SHADER);
////		glShaderSource(vertex, 1, &vShaderCode, NULL);
////		glCompileShader(vertex);
////		checkCompileErrors(vertex, "VERTEX");
////		// fragment Shader
////		fragment = glCreateShader(GL_FRAGMENT_SHADER);
////		glShaderSource(fragment, 1, &fShaderCode, NULL);
////		glCompileShader(fragment);
////		checkCompileErrors(fragment, "FRAGMENT");
////		// shader Program
////		ID = glCreateProgram();
////		glAttachShader(ID, vertex);
////		glAttachShader(ID, fragment);
////		glLinkProgram(ID);
////		checkCompileErrors(ID, "PROGRAM");
////		// delete the shaders as they're linked into our program now and no longer necessary
////		glDeleteShader(vertex);
////		glDeleteShader(fragment);
////	}
////	// activate the shader
////	// ------------------------------------------------------------------------
////	void Use()
////	{
////		glUseProgram(ID);
////	}
////	// utility uniform functions
////	// ------------------------------------------------------------------------
////	void setBool(const std::string &name, bool value) const
////	{
////		glUniform1i(glGetUniformLocation(ID, name.c_str()), (int)value);
////	}
////	// ------------------------------------------------------------------------
////	void setInt(const std::string &name, int value) const
////	{
////		glUniform1i(glGetUniformLocation(ID, name.c_str()), value);
////	}
////	// ------------------------------------------------------------------------
////	void setFloat(const std::string &name, float value) const
////	{
////		glUniform1f(glGetUniformLocation(ID, name.c_str()), value);
////	}
////
////private:
////	// utility function for checking shader compilation/linking errors.
////	// ------------------------------------------------------------------------
////	void checkCompileErrors(unsigned int shader, std::string type)
////	{
////		int success;
////		char infoLog[1024];
////		if (type != "PROGRAM")
////		{
////			glGetShaderiv(shader, GL_COMPILE_STATUS, &success);
////			if (!success)
////			{
////				glGetShaderInfoLog(shader, 1024, NULL, infoLog);
////				std::cout << "ERROR::SHADER_COMPILATION_ERROR of type: " << type << "\n" << infoLog << "\n -- --------------------------------------------------- -- " << std::endl;
////			}
////		}
////		else
////		{
////			glGetProgramiv(shader, GL_LINK_STATUS, &success);
////			if (!success)
////			{
////				glGetProgramInfoLog(shader, 1024, NULL, infoLog);
////				std::cout << "ERROR::PROGRAM_LINKING_ERROR of type: " << type << "\n" << infoLog << "\n -- --------------------------------------------------- -- " << std::endl;
////			}
////		}
////	}
////};
////#endif



#ifndef SHADER_H
#define SHADER_H

#include <string>
#include <fstream>
#include <sstream>
#include <iostream>

#include <GL/glew.h>

class Shader
{
public:
	GLuint Program;
	// Constructor generates the shader on the fly
	Shader(const GLchar* vertexPath, const GLchar* fragmentPath)
	{
		// 1. Retrieve the vertex/fragment source code from filePath
		std::string vertexCode;
		std::string fragmentCode;
		std::ifstream vShaderFile;
		std::ifstream fShaderFile;

		std::string testCode;
		std::ifstream testFile;
		std::stringstream testStream;

		// ensures ifstream objects can throw exceptions:
		vShaderFile.exceptions (std::ifstream::failbit | std::ifstream::badbit);
		fShaderFile.exceptions (std::ifstream::failbit | std::ifstream::badbit);
		try
		{
			// Open files
			vShaderFile.open(vertexPath);
			assert(vShaderFile.is_open());
			fShaderFile.open(fragmentPath);
			assert(fShaderFile.is_open());

			// test
//			testFile.open("test.txt");
//			assert(testFile.is_open());
//			testStream << testFile.rdbuf();
//			testFile.close();
//			testCode = testStream.str();
//			std::cout << testCode << std::endl;

			std::stringstream vShaderStream, fShaderStream;
			// Read file's buffer contents into streams
			vShaderStream << vShaderFile.rdbuf();
			fShaderStream << fShaderFile.rdbuf();
			// close file handlers
			vShaderFile.close();
			fShaderFile.close();
			// Convert stream into string
			vertexCode = vShaderStream.str();
			fragmentCode = fShaderStream.str();
		}
		catch (std::ifstream::failure e)
		{
			std::cout << "ERROR::SHADER::FILE_NOT_SUCCESFULLY_READ" << std::endl;
		}
		const GLchar* vShaderCode = vertexCode.c_str();
		const GLchar * fShaderCode = fragmentCode.c_str();
		// 2. Compile shaders
		GLuint vertex, fragment;
		GLint success;
		GLchar infoLog[512];
		// Vertex Shader
		vertex = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vertex, 1, &vShaderCode, NULL);
		glCompileShader(vertex);
		// Print compile errors if any
		glGetShaderiv(vertex, GL_COMPILE_STATUS, &success);
		if (!success)
		{
			glGetShaderInfoLog(vertex, 512, NULL, infoLog);
			std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
		}
		// Fragment Shader
		fragment = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragment, 1, &fShaderCode, NULL);
		glCompileShader(fragment);
		// Print compile errors if any
		glGetShaderiv(fragment, GL_COMPILE_STATUS, &success);
		if (!success)
		{
			glGetShaderInfoLog(fragment, 512, NULL, infoLog);
			std::cout << "ERROR::SHADER::FRAGMENT::COMPILATION_FAILED\n" << infoLog << std::endl;
		}
		// Shader Program
		this->Program = glCreateProgram();
		glAttachShader(this->Program, vertex);
		glAttachShader(this->Program, fragment);
		glLinkProgram(this->Program);
		// Print linking errors if any
		glGetProgramiv(this->Program, GL_LINK_STATUS, &success);
		if (!success)
		{
			glGetProgramInfoLog(this->Program, 512, NULL, infoLog);
			std::cout << "ERROR::SHADER::PROGRAM::LINKING_FAILED\n" << infoLog << std::endl;
		}
		// Delete the shaders as they're linked into our program now and no longer necessery
		glDeleteShader(vertex);
		glDeleteShader(fragment);

	}
	// Uses the current shader
	void Use()
	{
		glUseProgram(this->Program);
	}
};

#endif