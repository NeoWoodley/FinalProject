# MacOS系统
如果是APPLE的话，需要添加下面的代码：
```C++
// ----------------------------------------
#ifdef __APPLE__
	glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // uncomment this statement to fix compilation on OS X
#endif

```