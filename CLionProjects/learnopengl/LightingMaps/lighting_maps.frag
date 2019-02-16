#version 330 core
struct Material {
    // 把纹理以sample2D类型储存在Material结构体中
    // 使用diffuse贴图代替早期定义的vec3类型的diffuse颜色
    // 要记住的是sampler2D也叫做模糊类型，这意味着我们不能以某种类型对它实例化，只能用uniform定义它们。
    // 如果我们用结构体而不是uniform实例化（就像函数的参数那样），GLSL会抛出奇怪的错误；这同样也适用于其他模糊类型。
    // 移除amibient材质颜色向量，因为ambient颜色绝大多数情况等于diffuse颜色，所以不需要分别去储存它
    sampler2D diffuse;
    sampler2D specular;
    float     shininess;
};

struct Light {
    vec3 position;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

in vec3 FragPos;
in vec3 Normal;
in vec2 TexCoords;  // 纹理坐标

out vec4 color;

uniform vec3 viewPos;
uniform Material material;
uniform Light light;

void main()
{
    // Ambient
    // 把ambient材料的颜色设置为diffuse材质的颜色
    vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));

    // Diffuse
    vec3 norm = normalize(Normal);
    vec3 lightDir = normalize(light.position - FragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    // 从纹理采样，获得原始像素的diffuse颜色值
    vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, TexCoords));

    // Specular
    // 通过使用一个specular贴图我们可以定义极为精细的细节，物体的这个部分会获得闪亮的属性，我们可以设置它们相应的亮度。
    // specular贴图给我们一个附加的高于diffuse贴图的控制权限。
    vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, norm);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // 采用这个specular贴图来获取原始像素相应的specular亮度
    vec3 specular = light.specular * spec * vec3(texture(material.specular, TexCoords));

    color = vec4(ambient + diffuse + specular, 1.0f);
}