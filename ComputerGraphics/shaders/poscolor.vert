in vec3 pos;

in vec3 vertexColor;
out vec3 color;

void main()
{
    gl_Position = vec4(pos.x, pos.y, pos.z, 1);
    color = vertexColor;
}