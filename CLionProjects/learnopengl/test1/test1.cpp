//
// Created by Neo Woodley on 2019-02-01.
//

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

void readTxt(std::string file);

int main() {
	std::cout << "test1" << std::endl;
	readTxt("/Users/neowoodley/Projects/CLionProjects/learnopengl/test1/test1.txt");
}

void readTxt(std::string file) {
	std::ifstream infile;
	infile.open(file.data());  // 将文件流对象与文件连接起来
	assert(infile.is_open());

	std::string s;
	while (getline(infile, s)) {
		std::cout << s << std::endl;
	}
	infile.close();  // 关闭文件输入流
}

