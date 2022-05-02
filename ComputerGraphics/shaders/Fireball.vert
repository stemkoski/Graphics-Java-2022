in vec3 position;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

uniform vec2 repeatUV;
in vec2 vertexUV;
out vec2 UV;

// use time for animated effects
uniform float time;

// create psuedo random numbers in range [0 ... 1]
float random(vec2 UV)
{
    return fract( 73.1024 * sin( 9.2468 * UV.x + 5.42 * UV.y ) );	
}

// average four pixel colors at corners of square containing this point
float smoothRandom(vec2 UV, float scale)
{
    vec2 iUV0 = floor(UV * scale);
    vec2 iUV1 = iUV0 + vec2(1.0, 0.0);
    vec2 iUV2 = iUV0 + vec2(0.0, 1.0);
    vec2 iUV3 = iUV0 + vec2(1.0, 1.0);

    // values at each point:
    float R0 = random(iUV0);
    float R1 = random(iUV1);
    float R2 = random(iUV2);
    float R3 = random(iUV3);

    // calculate averages of random values:
    vec2 p = fract(UV * scale); // calculate weights in the weighted average

    // replace staight lines from 0 to 1 with a smooth curve from 0 to 1.
    p = smoothstep(0.0, 1.0, p);

    float R01 = mix( R0, R1, p.x );
    float R23 = mix( R2, R3, p.x );
    return mix( R01, R23, p.y );
}

float fractalRandom(vec2 UV, float baseScale)
{
    float r = smoothRandom(UV, 1.0 * baseScale) / 2.0;
    r = r +   smoothRandom(UV, 2.0 * baseScale) / 4.0;
    r = r +   smoothRandom(UV, 4.0 * baseScale) / 8.0;
    r = r +   smoothRandom(UV, 8.0 * baseScale) / 16.0;
    return r;
}

vec3 fractalRandom3(vec2 UV, float baseScale)
{
    float r = fractalRandom(UV, baseScale);
    float g = fractalRandom(UV + vec2(1.337, 4.268), baseScale);
    float b = fractalRandom(UV + vec2(9.123, 2.007), baseScale);
    return vec3(r, g, b);
}



void main()
{ 
    /*
    // FIRST APPROACH: VERY SPIKEY FIREBALL
    // range: [-1 ... +1]
    vec2 randomSeed = vec2( position.x + position.y, position.x + position.z );
    vec3 random = 2.0 * fractalRandom3( randomSeed, 50.0 ) - 1.0;

    float t = time * 2.0;
    vec3 randomTime = vec3( random.x * sin(t) + random.y * cos(t),
                            random.y * sin(t) + random.z * cos(t),
                            random.z * sin(t) + random.x * cos(t) );

    vec3 pos = position + 0.05 * randomTime;
    */

    // SECOND APPROACH: force displacement to be perpendicular to surface.
    vec3 normal = position;

    float t = time / 4.0;

    normal = normal / length(normal); // force normal/perp vector to have length 1.

    vec2 randomSeed = vec2( (position.x + position.y) * sin(t) + (position.y + position.z) * cos(t), 
                            (position.z + position.x) * sin(t) + (position.y + position.x) * cos(t) );

    float random = 2.0 * fractalRandom( randomSeed, 4.0 ) - 1.0;

    vec3 pos = position + 0.1 * random * normal;

    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(pos, 1);
    // pass along data to the fragment shader; get interpolated along the way
    UV = repeatUV * vertexUV;
}



/*
SAVE ME

vec3 normal = position;
    normal = normal / length(normal); // force normal/perp vector to have length 1.
    vec2 randomSeed = vec2( position.x + position.y + time, position.z + position.y + time );
    float random = 2.0 * fractalRandom( randomSeed, 4.0 ) - 1.0;
    vec3 pos = position + 0.20 * random * normal;

*/

