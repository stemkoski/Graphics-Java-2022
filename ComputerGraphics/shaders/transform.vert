in vec3 pos;
uniform mat4 transform;

void main()
{
   vec4 v = vec4(pos.x, pos.y, pos.z, 1);

   gl_Position = transform * v;
}