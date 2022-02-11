in vec3 pos;

// translate all vertices by some offset amount
uniform vec3 offset;

void main()
{
   gl_Position = vec4(pos.x + offset.x, 
                      pos.y + offset.y, 
                      pos.z + offset.z, 1.0);
}