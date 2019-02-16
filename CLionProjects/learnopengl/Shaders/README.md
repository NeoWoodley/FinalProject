# 着色器类
## 使用着色器类
我们把顶点、片段着色器储存为两个叫作`shader.vs`和`shader.frag`的文件。
```C++
Shader ourShader("paht/to/shaders/shader.vs", "path/to/shaders/shader.frag");
...
while(...) {
    ourShader.Use();
    glUniform1f(glGetUniformLocation(ourShader.Program, "someUniform"), 1.0f);
    DrawStuff();
```