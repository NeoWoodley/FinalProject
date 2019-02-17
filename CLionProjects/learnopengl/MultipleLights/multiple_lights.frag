#version 330 core
// 如果我们对每个光源都去写一遍光照计算的代码，将会是一件令人恶心的事情，所以我们将一些操作封装为函数。
// GLSL中的函数：需要一个函数名、一个返回值类型，在调用前必须提前声明

// 接下来我们将为下面的每一种光照来写一个函数
struct Material {
    sampler2D diffuse;
    sampler2D specular;
    float shininess;
};

// 平行光的结构体
// 用于设置一系列用于表示平行光的变量
struct DirLight {
    vec3 direction;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

// 定义一个包含点光源所需属性的结构体
struct PointLight {
    vec3 position;

    float constant;
    float linear;
    float quadratic;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

#define NR_POINT_LIGHTS 4  // 使用预处理指令来定义点光源的数量：4个点光源结构体对象

in vec3 FragPos;
in vec3 Normal;
in vec2 TexCoords;

out vec4 color;

uniform vec3 viewPos;
uniform DirLight dirLight;
uniform PointLight pointLights[NR_POINT_LIGHTS];
uniform Material material;

// Function prototypes，函数声明
vec3 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir);
vec3 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir);

// 当我们在场景中使用多个光源时，一般使用以下途径：
// 1、创建一个代表输出颜色的向量
// 2、每个光源都对输出颜色贡献一些颜色，因此，场景中的每个光源将进行独立运算，并且运算结果都对最终的输出颜色有一定影响
// 下面是使用这种方式进行多光源运算的一般结构：
// 1、定义输出颜色
// 2、将平行光的运算结果颜色添加到输出颜色
// 3、将顶点光的运算结果添加到输出颜色
// 4、添加其他光源的计算结果颜色（如投射光）

// 我们将定义几个用于计算各个光源的函数，并将这些函数的运算结果（返回颜色）添加到输出颜色向量中
void main()
{
    // Properties：一些属性
    vec3 norm = normalize(Normal);  // 将法向量规范化：单位化
    vec3 viewDir = normalize(viewPos - FragPos);

    // == ======================================
    // Our lighting is set up in 3 phases: directional, point lights and an optional flashlight
    // For each phase, a calculate function is defined that calculates the corresponding color
    // per lamp. In the main() function we take all the calculated colors and sum them up for
    // this fragment's final color.
    // == ======================================
    // Phase 1: Directional lighting：计算平行光照
    vec3 result = CalcDirLight(dirLight, norm, viewDir);
    // Phase 2: Point lights：计算定点光照，共有4个定点光
    for(int i = 0; i < NR_POINT_LIGHTS; i++)
        result += CalcPointLight(pointLights[i], norm, FragPos, viewDir);
    // Phase 3: Spot light：计算手电筒光照
    // result += CalcSpotLight(spotLight, norm, FragPos, viewDir);

	// 每一个光源的运算结果都添加到了输出颜色上，输出颜色包含了此场景中的所有光源的影响
    color = vec4(result, 1.0);
}

// 平行光（定向光）
// Calculates the color when using a directional light.
vec3 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir)
{
    vec3 lightDir = normalize(-light.direction);
    // Diffuse shading：计算漫反射强度
    float diff = max(dot(normal, lightDir), 0.0);
    // Specular shading：计算镜面反射强度
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // Combine results：合并各个光照分量
    vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));
    vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, TexCoords));
    vec3 specular = light.specular * spec * vec3(texture(material.specular, TexCoords));
    // 该结果是一个由该平行光的环境反射、漫反射、镜面反射的各个分量组成的一个向量
    return (ambient + diffuse + specular);
}

// 点光源
// 计算定点光在确定位置的光照颜色
// Calculates the color when using a point light.
vec3 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir)
{
    vec3 lightDir = normalize(light.position - fragPos);
    // Diffuse shading：计算漫反射强度
    float diff = max(dot(normal, lightDir), 0.0);
    // Specular shading：计算镜面反射强度
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // Attenuation：计算衰减
    float distance = length(light.position - fragPos);
    float attenuation = 1.0f / (light.constant + light.linear * distance + light.quadratic * (distance * distance));
    // Combine results：将各个分量合并
    vec3 ambient = light.ambient * vec3(texture(material.diffuse, TexCoords));
    vec3 diffuse = light.diffuse * diff * vec3(texture(material.diffuse, TexCoords));
    vec3 specular = light.specular * spec * vec3(texture(material.specular, TexCoords));
    ambient *= attenuation;
    diffuse *= attenuation;
    specular *= attenuation;
    return (ambient + diffuse + specular);
}