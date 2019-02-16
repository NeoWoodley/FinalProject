#version 330 core
out vec4 color;

in vec3 FragPos;
// 所有光照的计算需要在片段着色器里进行，需要把法向量由顶点着色器传递到片段着色器
in vec3 Normal;

uniform vec3 lightPos;
// 把相应的摄像机位置传给片段着色器：使用摄像机对象的位置坐标作为观察者，得到观察者的世界空间坐标
uniform vec3 viewPos;
uniform vec3 lightColor;
uniform vec3 objectColor;

void main()
{
    // Ambient：环境光
    // 常量环境因子
    float ambientStrength = 0.1f;
    vec3 ambient = ambientStrength * lightColor;

    // Diffuse：漫反射（散射）光
    vec3 norm = normalize(Normal);
    // 从片段位置指向光源的向量
    vec3 lightDir = normalize(lightPos - FragPos);
    // 对norm和lightDir向量进行点乘，来计算光对当前片段的实际的散射影响
    float diff = max(dot(norm, lightDir), 0.0);
    // 结果值再乘以光的颜色，得到散射因子。两个向量之间的角度越大，散射因子就会越小
    vec3 diffuse = diff * lightColor;

    // Specular：镜面光照
    // 镜面强度变量
    float specularStrength = 0.5f;
    // 视线方向坐标：从片段位置指向观察点位置
    vec3 viewDir = normalize(viewPos - FragPos);
    // reflect函数：第一个参数——从光源指向片段位置的向量；第二个参数——已标准化的norm法向量
    vec3 reflectDir = reflect(-lightDir, norm);
    // 先计算视线方向与反射方向的点乘（确保它不是负值），然后得到它的32次幂
    // 一个物体的发光值越高，反射光的能力越强，散射得越少，高光电越小
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32);
    vec3 specular = specularStrength * spec * lightColor;

    vec3 result = (ambient + diffuse + specular) * objectColor;
    color = vec4(result, 1.0f);
}