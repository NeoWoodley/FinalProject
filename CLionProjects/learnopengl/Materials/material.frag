#version 330 core
// 我们可以使用3种光照元素：
// 环境光照Ambient Lighting、漫反射光照Diffuse Lighting、镜面光照Specular Lighting定义一个材质颜色。
// 通过为每个元素指定一个颜色，我们已经对物体的颜色输出有了精密的控制
// 在片段着色器中，创建一个结构体来储存物体的材质属性，
// 我们也可以把它们储存为独立的uniform值，但是作为一个结构体来储存可以更有条理


// ambient材质向量定义了在环境光照下这个物体反射的是什么颜色；通常这是和物体颜色相同的颜色。
// diffuse材质向量定义了在漫反射光照下物体的颜色。
// 漫反射颜色被设置为(和环境光照一样)我们需要的物体颜色。
// specular材质向量设置的是物体受到的镜面光照的影响的颜色(或者可能是反射一个物体特定的镜面高光颜色)。
// 最后，shininess影响镜面高光的散射/半径。
// 这四个元素定义了一个物体的材质，通过它们我们能够模拟很多真实世界的材质。

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float shininess;  // 镜面高光元素
};

// 一个光源的ambient、diffuse和specular光都有不同的亮度。
// 环境光通常设置为一个比较低的亮度，因为我们不希望环境色太过显眼。
// 光源的diffuse元素通常设置为我们希望光所具有的颜色；经常是一个明亮的白色。
// specular元素通常被设置为vec3(1.0f)类型的全强度发光。
// 要记住的是我们同样把光的位置添加到结构体中。

struct Light {
    vec3 position;  // 光的位置

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

in vec3 FragPos;
in vec3 Normal;

out vec4 color;

uniform vec3 viewPos;
// 声明一个uniform变量，以新创建的结构体作为它的类型
// 通过设置适当的uniform，我们可以在应用中设置物体的材质。
uniform Material material;
uniform Light light;


// 所有材质元素都储存在结构体中，可以通过uniform变量取得它们。
// 物体的每个材质属性都乘以它们对应的光照元素。
void main()
{
    // Ambient：环境光
    vec3 ambient = light.ambient * material.ambient;

    // Diffuse：漫反射光
    vec3 norm = normalize(Normal);
    vec3 lightDir = normalize(light.position - FragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = light.diffuse * (diff * material.diffuse);

    // Specular：镜面高光
    vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    vec3 specular = light.specular * (spec * material.specular);

    vec3 result = ambient + diffuse + specular;
    color = vec4(result, 1.0f);
}